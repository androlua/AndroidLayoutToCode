package com.cz.layout2code.action

import com.cz.layout2code.config.DeclareStyleableConfiguration
import com.cz.layout2code.config.WidgetConfiguration
import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.form.UnknownWidgetForm
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.util.TextCalculation
import com.cz.layout2code.util.Utils
import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiUtilBase
import org.jdom.Element
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*


/**
 * Created by cz on 2017/12/14.
 * 布局转换代码的事件执行体
 */
class LayoutConvertAction : BaseGenerateAction {
    //自定义控件模板
    val widgetAttrs= mutableListOf<DefineViewNode>()

    constructor() : super(null)

    constructor(handler: CodeInsightActionHandler) : super(handler)

    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
        return super.isValidForFile(project, editor, file) &&null!=Utils.getLayoutFileFromCaret(editor, file)
    }


    override fun actionPerformed(event: AnActionEvent) {
        val dataContext = event.dataContext
        val project = DataKeys.PROJECT.getData(dataContext)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor){
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            val layout = Utils.getLayoutFileFromCaret(editor, file)
            if(file is KtFile){
                if (layout == null) {
                    MessageDelegate.showMessage("No layout found")
                } else {
                    MessageDelegate.logEventMessage("Layout found:${file.packageFqName}")
                    //解析xml
                    parseXmlLayout(project,layout)
                }
            } else if(file is PsiJavaFile){
                if (layout == null) {
                    MessageDelegate.showMessage("No layout found")
                } else {
                    MessageDelegate.logEventMessage("Layout found:${file.packageName}")
                    //解析xml
                    parseXmlLayout(project,layout)
                }
            } else {
                //不是kt/java源码文件,转换无意义
                MessageDelegate.showMessage("Not a Kotlin or Java source file!")
            }
        }
    }

    /**
     * 解析xml布局
     */
    private fun parseXmlLayout(project: Project,layout:PsiFile){
        val virtualFile=layout.virtualFile
        if(virtualFile.exists()){
            val layoutFile= File(virtualFile.path)
            val builder = SAXBuilder()//实例JDOM解析器
            val document = builder.build(layoutFile)//读取xml文件
            //1:递归解析所有节点,收集所有自定义控件
            val parent=ViewNode("root")
            val customNodes= mutableListOf<ViewNode>()
            parseElement(parent,document.rootElement,customNodes)
            //2:检测自定义控件模板是否记录此控件属性.不存在则检索项目内values.xml
            val defineWidgetAttrs= mutableListOf<DefineViewNode>()
            if(customNodes.isNotEmpty()){
                //2.1:解析出当前项目己有的自定义控件配置项
                ensuredWidgetAttrs(project,widgetAttrs)

                //2.2:采集所有第三方库的values.xml
                val valueFiles = FilenameIndex.getVirtualFilesByName(project, "values.xml", GlobalSearchScope.everythingScope(project))
                valueFiles.forEach{
                    val parseItems = DeclareStyleableConfiguration(it).parse()
                    defineWidgetAttrs+=parseItems.filter { item-> widgetAttrs.none { it.name==item.name } }
                }
                //2.3:解析项目attrs.xml定义
                //file:///Users/cz/IntelliJIDEAProjects/MyApplication/app/src/main/res/values/attrs.xml
                val attrsFiles = FilenameIndex.getVirtualFilesByName(project, "attrs.xml", GlobalSearchScope.projectScope(project))
                attrsFiles.forEach {
                    val parseItems = DeclareStyleableConfiguration(it).parse()
                    defineWidgetAttrs+=parseItems.filter { item-> widgetAttrs.none { it.name==item.name } }
                }
                val st=System.currentTimeMillis()
                customNodes.forEach {
                    val name=it.name
                    val findClass = JavaPsiFacade.getInstance(project).findClass(name, GlobalSearchScope.everythingScope(project))
                    if(null!=findClass){
                        val widgetAttrs = defineWidgetAttrs.filter { name.endsWith(it.name) }
                        widgetAttrs?.forEach { widget->
                            val methods=findClass.methods.filter { !it.isConstructor }
                            //2.4:检测每个属性,与所有方法的文字匹配度,取最高的前5个方法
                            val MAX_SIZE=5
                            widget.attributes.forEach { attribute->
                                //遍历源码所有方法,求得各方法文本匹配度
                                methods.forEach {
                                    //相似度
                                    val degree = TextCalculation.similarDegree(attribute.name, it.name)
                                    if(attribute.methods.size>=MAX_SIZE){
                                        //超出5个,移掉最小的,太多的选择无意义
                                        val keys=attribute.methods.keys
                                        keys.remove(keys.last())
                                    }
                                    attribute.methods.put(degree,it.name)
                                }
                            }
                        }
                    }
                }
                println("耗时:${System.currentTimeMillis()-st}")
            }
            //过滤出所有未配置的节点
            val filterNodes = defineWidgetAttrs.filter { v -> customNodes.any { v.name==it.simpleName } }
            if(filterNodes.isNotEmpty()){
                //记录首选的声明方法
                filterNodes.forEach {
                    it.attributes.forEach {
                        val keys = it.items.keys
                        if(keys.isNotEmpty()){
                            it.defineMethod=it.items.keys.first()
                        }
                    }
                }
                //3:弹出提示框,建议用户用做配置定义
                val unknownWidgetForm = UnknownWidgetForm(filterNodes)
                unknownWidgetForm.setActionListener {
                    //1:更新自定义控件信息
                    widgetAttrs+=unknownWidgetForm.treeWidgetAttributes
                    //2:写入配置文件
                    ensuredConfigurationFolder(project) { subDirectory ->
                        val findFile = subDirectory.findFile("widget.xml")
                        var virtualFile:VirtualFile
                        if(null!=findFile){
                            virtualFile=findFile.virtualFile
                        } else {
                            val createFile = subDirectory.createFile("widget.xml")
                            virtualFile=createFile.virtualFile
                        }
                        //更新节点
                        WidgetConfiguration(virtualFile).createOrUpdate(widgetAttrs)
                        MessageDelegate.logEventMessage("Update widget attributes complete!")
                    }
                }
            } else {
                //直接生成代码
            }
        }
    }

    /**
     * 解析子节点
     */
    private fun parseElement(parent:ViewNode,element: Element,customNodes:MutableList<ViewNode>){
        val node=ViewNode(element.name)
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

    /**
     * 确认所有自定义控件列
     */
    private fun ensuredWidgetAttrs(project: Project, widgetAttrs:MutableList<DefineViewNode>) {
        ensuredConfigurationFolder(project){ subDirectory->
            //读取文件
            val findFile = subDirectory.findFile("widget.xml")
            if(null!=findFile){
                val file = File(findFile.virtualFile.path)
                if(file.exists()){
                    //添加配置控件信息
                    widgetAttrs+=WidgetConfiguration(findFile.virtualFile).parse()
                }
            }
        }
    }

    /**
     * 确认配置文件夹
     */
    private fun ensuredConfigurationFolder(project: Project,callback:(PsiDirectory)->Unit) {
        val baseDir = project.baseDir
        val directory = PsiManager.getInstance(project).findDirectory(baseDir)
        if (null != directory) {
            //读取是否需要更新
            WriteCommandAction.runWriteCommandAction(project) {
                var subDirectory = directory.findSubdirectory(".cfg")
                if (null == subDirectory) {
                    //创建文件夹
                    subDirectory = directory.createSubdirectory(".cfg")
                }
                callback(subDirectory)
            }
        }
    }


}
