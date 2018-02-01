package com.cz.layout2code.action

import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.inflate.item.AttributeDefineNode
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.util.Utils
import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiUtilBase
import org.jdom.Element
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.psi.KtFile
import java.io.File


/**
 * Created by cz on 2017/12/14.
 * 布局转换anko 的dsl的事件执行体
 */
class LayoutConvertAction : BaseGenerateAction {
    //自定义控件模板
    val widget= mutableListOf<DefineViewNode>()

    constructor() : super(null)

    constructor(handler: CodeInsightActionHandler) : super(handler)

    init {
        println("LayoutConvertAction")
    }

    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
        return super.isValidForFile(project, editor, file) &&null!=Utils.getLayoutFileFromCaret(editor, file)
    }


    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor){
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            if(file is KtFile){
                val layout = Utils.getLayoutFileFromCaret(editor, file)
                if (layout == null) {
                    MessageDelegate.showMessage("No layout found")
                } else {
                    MessageDelegate.logEventMessage("Layout found:${file.packageFqName}")
                    //解析xml
                    parseXmlLayout(project,layout)
                }
            } else if(file is PsiJavaFile){
                val layout = Utils.getLayoutFileFromCaret(editor, file)
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
    private fun parseXmlLayout(project: Project,file:PsiFile){
        val virtualFile=file.virtualFile
        if(virtualFile.exists()){
            val layoutFile= File(virtualFile.path)
            val builder = SAXBuilder()//实例JDOM解析器
            val document = builder.build(layoutFile)//读取xml文件
            //1:递归解析所有节点,收集所有自定义控件
            val parent=ViewNode("root")
            val customNodes= mutableListOf<ViewNode>()
            parseElement(parent,document.rootElement,customNodes)
            //2:检测自定义控件模板是否记录此控件属性.不存在则检索项目内values.xml
            if(customNodes.isNotEmpty()){
                //采集所有自定义控件定义
                val valueFiles = FilenameIndex.getVirtualFilesByName(project, "values.xml", GlobalSearchScope.everythingScope(project))
                valueFiles.forEach{
                    val builder = SAXBuilder()//实例JDOM解析器
                    val document = builder.build(File(it.path))//读取xml文件
                    parseValueStyleElement(document.rootElement,widget)
                }
                println(widget)
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
     * 解析values.xml文件
     */
    private fun parseValueStyleElement(element: Element,widgets:MutableList<DefineViewNode>){
        val children=element.getChildren("declare-styleable")
        children.forEach {
            //如果不存在此控件添加到声明定义中
            val name=it.getAttributeValue("name")
            if(widgets.none { it.name==name }){
                val viewNode=DefineViewNode(name)
                //添加节点
                widgets.add(viewNode)
                it.children.forEach {
                    val name=it.getAttributeValue("name")
                    val format=it.getAttributeValue("format")
                    val attributeNode=AttributeDefineNode(name,format)
                    //添加属性节点
                    viewNode.attributes.add(attributeNode)
                    if(null==format||"enum"==format){
                        //采集预定义属性
                        it.children.forEach {
                            val itemName=it.getAttributeValue("name")
                            val itemValue=it.getAttributeValue("value")
                            val intValue=if(itemValue.startsWith("0x")||itemValue.startsWith("0X")){
                                itemValue.substring(2).toInt(16)
                            } else {
                                itemValue.toInt()
                            }
                            attributeNode.items.put(itemName,intValue)
                        }
                    }
                }
            }
        }
    }


}
