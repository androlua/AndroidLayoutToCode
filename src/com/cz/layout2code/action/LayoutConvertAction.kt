package com.cz.layout2code.action

import com.cz.convert.isActivity
import com.cz.convert.isDialog
import com.cz.convert.isFragment
import com.cz.convert.isView
import com.cz.layout2code.generate.JavaCodeGenerate
import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.*
import com.cz.layout2code.util.Utils
import com.cz.layout2code.util.getKtClassForElement
import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.util.PsiUtilBase
import org.jdom.Element
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.psi.*
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.j2k.getContainingClass
import org.jetbrains.kotlin.j2k.getContainingMethod
import org.jetbrains.kotlin.psi.KtCallExpression
import java.io.File


/**
 * Created by cz on 2017/12/14.
 * 布局转换代码的事件执行体
 *
 *
 * 以下问题暂无法解决.kotlin转anko的模块暂停,但原型己出,只是缺少kotlin plugin的api支持
 *
 * 以下为kotlin plugin版本异常问题.暂时没找到解决方案
 * Assertion failed: Duplicate bundled template Gradle Kotlin DSL Build Script.gradle [jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/Kotlin/lib/kotlin-plugin.jar!/fileTemplates/Gradle Kotlin DSL Build Script.gradle.ft, jar:file:/Applications/IntelliJ%20IDEA.app/Contents/lib/kotlin-plugin.jar!/fileTemplates/Gradle Kotlin DSL Build Script.gradle.ft]
 * java.lang.Throwable: Assertion failed: Duplicate bundled template Gradle Kotlin DSL Build Script.gradle [jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/Kotlin/lib/kotlin-plugin.jar!/fileTemplates/Gradle Kotlin DSL Build Script.gradle.ft, jar:file:/Applications/IntelliJ%20IDEA.app/Contents/lib/kotlin-plugin.jar!/fileTemplates/Gradle Kotlin DSL Build Script.gradle.ft]
 *
 *
 * idea 在开启两个不同的类加载器,导致同样的对象,无法作类型判断,无法强转.
 * java.lang.ClassCastException: org.jetbrains.kotlin.psi.KtFile cannot be cast to org.jetbrains.kotlin.psi.KtFile
 */
class LayoutConvertAction : BaseGenerateAction {
    val defineWidgetAttrs= mutableListOf<DefineViewNode>()

    constructor() : super(null)
    constructor(handler: CodeInsightActionHandler?) : super(handler)

    override fun update(event: AnActionEvent) {
        super.update(event)
        val dataContext = event.dataContext
        val project = DataKeys.PROJECT.getData(dataContext)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor) {
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            //屏蔽掉非java文件与查找不到layout文件场景,kotlin的支持,因为官方插件因素,待完善
            event.presentation.isEnabled=file is PsiJavaFile&&
                    null!=Utils.getLayoutFileFromCaret(editor, file)
        }
    }

    override fun actionPerformed(event: AnActionEvent) {
        val dataContext = event.dataContext
        val project = DataKeys.PROJECT.getData(dataContext)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor) {
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            val layout = Utils.getLayoutFileFromCaret(editor, file)
            val virtualFile=layout?.virtualFile
            val clazz = getTargetClass(editor, file)
            if(file !is PsiJavaFile) {
                //不是kt/java源码文件,转换无意义
                MessageDelegate.showMessage("Not a source file!")
            } else if(null==clazz){
                MessageDelegate.showMessage("Class is invalid!")
            } else if(null!=virtualFile){
                //开始解析
                val layoutFile = File(virtualFile.path)
                val builder = SAXBuilder()//实例JDOM解析器
                val document = builder.build(layoutFile)//读取xml文件
                //递归解析所有节点,收集所有自定义控件
                val rootNode = ViewNode("root", 0)
                val customNodes = mutableListOf<ViewNode>()
                parseElement(rootNode, document.rootElement, customNodes)
                //确定场景匹配器,负责上下文,以及其他字段的特例化
                val baseMatcher:BaseContext
                if(clazz.isActivity()){
                    baseMatcher=ActivityContext(project)
                } else if(clazz.isFragment()){
                    baseMatcher= FragmentContext(project)
                } else if(clazz.isDialog()){
                    baseMatcher= DialogContext(project)
                } else if(clazz.isView()){
                    baseMatcher= ViewContext(project)
                } else {
                    //其他
                    baseMatcher= OtherContext(project)
                }
                //布局执行语句
                val containingElement = getContainingElement(file, editor)
                //生成方法
                val converter=JavaCodeGenerate(project,baseMatcher,clazz)
                converter.generate(file,containingElement,layoutFile,rootNode.children.first(),ViewGroup.LayoutParams())
            }
        }
    }

    /**
     * 获得执行调用方法处
     */
    private fun getContainingElement(file:PsiFile,editor:Editor):PsiElement?{
        val candidate = file.findElementAt(editor.caretModel.offset - 1)
        var parent: PsiElement? = candidate?.parent
        //当当前指针表达式,为一个调用方法时,中断
        while(null!=parent&&parent !is PsiMethodCallExpression){
//                &&parent !is KtCallExpression){
            parent=parent?.parent
        }
        return parent
    }
//    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
//        return super.isValidForFile(project, editor, file) &&null!=Utils.getLayoutFileFromCaret(editor, file)
//    }

//    override fun actionPerformed(event: AnActionEvent)  {
//        val dataContext = event.dataContext
//        val project = DataKeys.PROJECT.getData(dataContext)
//        val editor = event.getData(PlatformDataKeys.EDITOR)
//        if(null!=project&&null!=editor){
//            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
//            val offset = editor.caretModel.offset
//            val element = file?.findElementAt(offset)
//            val layout = Utils.getLayoutFileFromCaret(editor, file)
//            //采集所有第三方库,与当前项目内的控件属性声明
////            val defineWidgetAttrs=defineWidgetAttrs
////            if (defineWidgetAttrs.isEmpty()) {
////                //引用库
////                defineWidgetAttrs += ExplodedAarAnalyzer(file).analysis(project)
////                //当前项目内控件
////                defineWidgetAttrs += ModuleAnalyzer(file).analysis(project)
////            }
//            //不做文件字节码判断,因为判断比直接检索更耗时,己测试,性能相差近30倍,直接操作500毫秒,判断操作time:14842
//            val fileName=file?.name
//            if(null==fileName||(!fileName.endsWith("kt")&&!fileName.endsWith("java"))){
//                //不是kt/java源码文件,转换无意义
//                MessageDelegate.showMessage("Not a Kotlin or Java source file!")
//            } else {
//                if (layout == null) {
//                    MessageDelegate.showMessage("No layout found")
//                } else {
//                    val out=StringBuilder()
//                    MessageDelegate.logEventMessage("Layout found:${file?.name}")
//                    val virtualFile = layout.virtualFile
//                    val clazz=file.getContainingClass()
//                    if(null!=virtualFile&&null!=file){
//                        //开始解析xml信息
//                        if(virtualFile.exists()) {
//                            val layoutFile = File(virtualFile.path)
//                            val builder = SAXBuilder()//实例JDOM解析器
//                            val document = builder.build(layoutFile)//读取xml文件
//                            //1:递归解析所有节点,收集所有自定义控件
//                            val parent = ViewNode("root", 0)
//                            val customNodes = mutableListOf<ViewNode>()
//                            parseElement(parent, document.rootElement, customNodes)
//                            //2:生成方法体
//                            if(file is PsiJavaFile){
//                                out.append("private View getContentView(Context context){\n")
//                                out.append("\tResources resources = getResources();\n")
//                                val converter = JavaCodeGenerate()
//                                out.append(converter.generate(project,parent.children.first()))
//                                out.append("}\n")
//                            } else {
////                                out.append("private fun getContentView(context:Context):View{\n")
////                                out.append("\tval resources = getResources()\n")
//                                val converter = KotlinCodeGenerate()
////                                out.append(converter.generate(project,parent.children.first()))
////                                out.append("}\n")
//                                println(converter.generate(project,parent.children.first()))
//                            }
//                        }
//                    }
//
//                    if(null!=file){
//                        val candidate = file.findElementAt(editor.caretModel.offset - 1)
//                        val ktClassForElement = file.getKtClassForElement()
//                        val containingClass = file.getContainingClass();
//                        val method=candidate?.getContainingMethod()
//                        var parent=candidate?.parent
//                        //当当前指针表达式,为一个调用方法时,中断
//                        while(null!=parent&&parent !is PsiMethodCallExpression&&
//                                parent::class.java.name!=KtCallExpression::class.java.name){
//                            parent=parent?.parent
//                        }
//                        val text=parent?.text
//                        val containingMethod = parent?.getContainingMethod()
//                        if(parent is KtCallExpression){
//                        }
//                        if(null!=containingMethod){
//                            val factory = JavaPsiFacade.getElementFactory(project)
//                            val generateMethod = factory.createMethodFromText(out.toString(), null)
//                            WriteCommandAction.runWriteCommandAction(project){
//                                clazz?.addAfter(generateMethod, containingMethod)
//                            }
//                        }
//
//                        println(containingMethod?.name)
//                        if(null!=text&&null!=clazz){
//                            if(clazz.isActivity()){
//                                //将parent表达式替换
//                            } else if(clazz.isFragment()){
//
//                            } else if(clazz.isDialog()){
//
//                            } else if(clazz.isView()){
////                                inflate(context,R.layout.activity_main,this)
//                            } else {
//                                //其他
//                            }
////                            setContentView(R.layout.activity_main)
////                            val parentLayout = RelativeLayout(this)
////                            val inflate1 = View.inflate(this, R.layout.activity_main, parentLayout)
////                            val parentLayout1 = FrameLayout(this)
////                            val inflate = LayoutInflater.from(this).inflate(R.layout.activity_main, parentLayout1, false)
//                            val context = ("(?<contentLayout>setContentView.+)|" +
//                                    "(inflate\\(\\w+,\\s*[\\w\\.]+,\\s*(?<viewInflate>\\w+)\\))|" +
//                                    "(inflate\\([\\w\\.]+,\\s*(?<inflate>\\w+),\\s*\\w+\\))").toPattern().context(text)
//                            var parentLayout:String?=null
//                            if(context.find()){
//                                if(null!=context.group("contentLayout")){
//
//                                } else if(null!=context.group("viewInflate")||null!=context.group("inflate")){
//
//                                }
//                            }
//                            val findElement=parent?.children?.flatMap { it.children.toList() }?.find { parentLayout==it.text }
//                            if(null!=findElement){
//                                val reference = findElement.reference
//                                val search: Query<PsiReference> = ReferencesSearch.search(findElement)
//                                if(reference is PsiReferenceExpressionImpl){
//                                    println(reference?.qualifiedName)
//                                }
//                                if(findElement is PsiReferenceExpressionImpl){
//                                    println(findElement)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }

    /**
     * 解析子节点
     */
    private fun parseElement(parent: ViewNode, element: Element, customNodes:MutableList<ViewNode>){
        val node= ViewNode(element.name,parent.level+1)
        //记录父节点
        node.parent=parent
        //父节点记录子节点
        parent.children.add(node)
        //记录自定义控件
        if(node.isCustomView){
            customNodes.add(node)
        }
        //记录所有引用
        element.attributes.forEach {
            node.attributes.add(AttributeNode(it.namespacePrefix,it.name,it.value))
        }
        //记录所有子孩子
        element.children.forEach { parseElement(node,it,customNodes) }
    }

}
