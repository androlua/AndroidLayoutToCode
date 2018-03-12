package com.cz.layout2code.inflate

import com.cz.layout2code.config.WidgetConfiguration
import com.cz.layout2code.convert.JavaConverter
import com.cz.layout2code.convert.KotlinConverter
import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.form.UnknownWidgetForm
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.util.TextCalculation
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.GlobalSearchScope
import org.jdom.Element
import org.jdom.input.SAXBuilder
import java.io.File

/**
 * Created by cz on 2017/12/13.
 * android xml布局解析对象
 * 解析分两个步骤
 * 1:解出 xml 内引用信息
 * 2:
 */
object AndroidLayoutInflater {
    val packageName ="com.cz.layout2code.inflate.impl"
    val systemPackages = mutableListOf("android.widget","android.view")
    //当前己声明的自定义控件属性列
    val allCustomWidgetAttrs = mutableListOf<DefineViewNode>()

    /**
     * 开始装载布局体
     * @param project 当前项目体
     * @param virtualFile 当前布局文件
     * @param isJava 是否为java文件
     */
    fun inflater(project: Project, file: PsiFile, virtualFile: VirtualFile, defineWidgetAttrs:MutableList<DefineViewNode>, isJava:Boolean){
        //开始解析xml信息
        if(virtualFile.exists()){
            val layoutFile= File(virtualFile.path)
            val builder = SAXBuilder()//实例JDOM解析器
            val document = builder.build(layoutFile)//读取xml文件
            //1:递归解析所有节点,收集所有自定义控件
            val parent= ViewNode("root",-1)
            val customNodes= mutableListOf<ViewNode>()
            parseElement(parent,document.rootElement,customNodes)
            //2:检测自定义控件模板是否记录此控件属性.不存在则检索项目内values.xml
//            val customWidgetAttrs = getCustomWidgetAttrNodeItems(project, customNodes, defineWidgetAttrs)
            //解析出当前项目己有的自定义控件配置项
//            val allCustomWidgetAttrs= allCustomWidgetAttrs
//            if(allCustomWidgetAttrs.isNotEmpty()){
//                ensuredWidgetAttrs(project, allCustomWidgetAttrs)
//            }
//            if(customWidgetAttrs.isNotEmpty()){
//                //将未声明的属性,添加到总集中
//                showUnKnowWidgetForm(allCustomWidgetAttrs,customWidgetAttrs, project){
//                    println("生成代码!")
//                    processLayoutWidget(project,customWidgetAttrs,parent,isJava)
//                }
//            } else {
                //直接生成代码
                processLayoutWidget(project,file,parent.children.first(),isJava)
//            }
        }
    }


    /**
     * 显示未知的控件表单
     */
    private fun showUnKnowWidgetForm(allWidgetAttrs: MutableList<DefineViewNode>,
                                     customWidgetAttrs:MutableList<DefineViewNode>,
                                     project: Project, callback:()->Unit) {
        //3:弹出提示框,建议用户用做配置定义
        val unknownWidgetForm = UnknownWidgetForm(customWidgetAttrs)
        unknownWidgetForm.setActionListener {
            //1:更新自定义控件信息
            allWidgetAttrs += unknownWidgetForm.treeWidgetAttributes
            //2:写入配置文件
            ensuredConfigurationFolder(project) { subDirectory ->
                val findFile = File(subDirectory.virtualFile.path, "widget.xml")
                //更新节点
                WidgetConfiguration(findFile).createOrUpdate(project, allWidgetAttrs)
                MessageDelegate.logEventMessage("Update widget attributes complete!")
                //回调事件
                callback.invoke()
            }
        }
    }

    /**
     * 处理自定义控件
     * @param project 当前项目对象
     * @param customNodes 当前自定义控件的xml节点信息
     * @param defineWidgetAttrs 当前项目所有引用声明的自定义控件引用信息列
     *
     */
    private fun getCustomWidgetAttrNodeItems(project: Project, customNodes: MutableList<ViewNode>,
                                             defineWidgetAttrs:MutableList<DefineViewNode>):MutableList<DefineViewNode> {
        val st = System.currentTimeMillis()
        val customAttrItems = mutableListOf<DefineViewNode>()
        customNodes.forEach {
            val name = it.name
            //自定义链
            var findClass = JavaPsiFacade.getInstance(project).findClass(name, GlobalSearchScope.everythingScope(project))
            while (null != findClass) {
                val qualifiedName = findClass.qualifiedName
                if(null!=qualifiedName){
                    //系统控件中止
                    if(systemPackages.any { qualifiedName.startsWith(it) }){
                        break
                    }
                    val widgetAttr = defineWidgetAttrs.find { qualifiedName == it.qualifiedName }
                    //记录属性
                    it.widgetAttr=widgetAttr
                    if(null!=widgetAttr){
                        //记录自定义控件属性集
                        if(customAttrItems.none { it.qualifiedName==qualifiedName }){
                            customAttrItems.add(widgetAttr)
                        }
                        //记录定义对象
                        val methods = findClass.methods.filter { !it.isConstructor }
                        //2.4:检测每个属性,与所有方法的文字匹配度,取最高的前5个方法
                        val MAX_SIZE = 5
                        widgetAttr.attributes.forEach { attribute ->
                            val defineMethod = attribute.defineMethod
                            if(null==defineMethod){
                                //遍历源码所有方法,求得各方法文本匹配度
                                methods.forEach {
                                    //相似度
                                    val degree = TextCalculation.similarDegree(attribute.name, it.name)
                                    if (attribute.methods.size >= MAX_SIZE) {
                                        //超出5个,移掉最小的,太多的选择无意义
                                        val keys = attribute.methods.keys
                                        keys.remove(keys.last())
                                    }
                                    attribute.methods.put(degree, it.name)
                                }
                                //预设属性对应方法
                                if(attribute.methods.isNotEmpty()){
                                    attribute.defineMethod=attribute.methods.values.first()
                                }
                            }
                        }
                    }
                }
                //往上层遍历
                findClass=findClass.superClass
            }
        }
        MessageDelegate.logEventMessage("Process custom widget:${System.currentTimeMillis() - st}")
        return customAttrItems
    }

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

    /**
     * 确认所有自定义控件列
     */
    private fun ensuredWidgetAttrs(project: Project, widgetAttrs:MutableList<DefineViewNode>) {
        ensuredConfigurationFolder(project){ subDirectory->
            //读取文件
            val findFile = File(subDirectory.virtualFile.path,"widget.xml")
            if(findFile.exists()){
                //添加配置控件信息
                widgetAttrs+= WidgetConfiguration(findFile).parse()
            }
        }
    }

    /**
     * 确认配置文件夹
     */
    private fun ensuredConfigurationFolder(project: Project, callback:(PsiDirectory)->Unit) {
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

    /**
     * 处理布局控件
     */
    private fun processLayoutWidget(project: Project,file: PsiFile, viewNode: ViewNode, convertToJava: Boolean) {
        if(convertToJava){
            //java源码输出
            val javaConverter = JavaConverter()
            println(javaConverter.convert(project,viewNode))
            javaConverter.importItems.forEach { println(it) }
        } else {
            //kotlin anko输出
            println(KotlinConverter().convert(project,viewNode))
        }
    }

}