package com.cz.layout2code.config

import com.cz.layout2code.inflate.item.DefineAttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import org.jdom.Document
import org.jdom.Element
import org.jdom.input.SAXBuilder
import org.jdom.output.XMLOutputter
import java.io.*
import org.jdom.output.Format


/**
 * Created by cz on 2018/2/22.
 */
class WidgetConfiguration(file: File) : XmlConfiguration<MutableList<DefineViewNode>>(file) {

    override fun parse(): MutableList<DefineViewNode> {
        val widgets = mutableListOf<DefineViewNode>()
        var document:Document?=null
        try{
            val builder = SAXBuilder()//实例JDOM解析器
            document = builder.build(File(file.path))//读取xml文件
        } catch (e:Exception){
            //nothing to do
        }
        val children=document?.rootElement?.getChildren("view")
        children?.forEach {
            //如果不存在此控件添加到声明定义中
            val name=it.getAttributeValue("name")
            val packageName=it.getAttributeValue("package")
            if(widgets.none { it.name==name }){
                val viewNode=DefineViewNode(packageName,name)
                //添加节点
                widgets.add(viewNode)
                it.children.forEach {
                    val name=it.getAttributeValue("name")
                    val format=it.getAttributeValue("format")
                    val attributeNode= DefineAttributeNode(name,format)
                    //记录预定义名称
                    attributeNode.defineMethod=it.getAttributeValue("method")
                    //添加属性节点
                    viewNode.attributes.add(attributeNode)
                    if("flag"==format||"enum"==format){
                        //采集预定义属性
                        it.children.forEach {
                            //如果没有format格式,记录格式
                            if(null==attributeNode.format){
                                attributeNode.format=it.name
                            }
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
        return widgets
    }

    override fun createOrUpdate(project: Project,nodes:MutableList<DefineViewNode>){
        //自己封装xml文档对象
        val rootElement = Element("widget")
        val document = Document(rootElement)
        nodes.forEach {
            val viewElement = Element("view")
            viewElement.setAttribute("package",it.packageName)
            viewElement.setAttribute("name",it.name)
            rootElement.addContent(viewElement)
            //子属性
            it.attributes.forEach {
                val itemElement = Element("attr")
                viewElement.addContent(itemElement)
                itemElement.setAttribute("name",it.name)
                itemElement.setAttribute("format",it.format?:"")
                itemElement.setAttribute("method",it.defineMethod?:"")
                //判断枚举与位集
                if("enum"==it.format||"flag"==it.format){
                    it.items.forEach { name, value ->
                        val element = Element("item")
                        itemElement.addContent(element)
                        element.setAttribute("name",name)
                        element.setAttribute("value",value.toString())
                    }
                }
            }
        }
        //写入xml
        try {
            WriteCommandAction.runWriteCommandAction(project){
                if(!file.exists()) {
                    //这里不在意其是否显示
                    file.createNewFile()
                }
                val format = Format.getCompactFormat()
                format.encoding = "utf-8"
                format.indent = " "
                XMLOutputter(format).output(document,FileOutputStream(file.path))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}