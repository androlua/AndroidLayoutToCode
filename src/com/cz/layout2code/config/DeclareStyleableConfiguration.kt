package com.cz.layout2code.config

import com.cz.layout2code.inflate.item.DefineAttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.intellij.openapi.vfs.VirtualFile
import org.jdom.input.SAXBuilder
import java.io.File

/**
 * Created by cz on 2018/2/22.
 */
class DeclareStyleableConfiguration(file: VirtualFile) : XmlConfiguration<MutableList<DefineViewNode>>(file) {

    override fun parse(): MutableList<DefineViewNode> {
        val widgets = mutableListOf<DefineViewNode>()
        val builder = SAXBuilder()//实例JDOM解析器
        val document = builder.build(File(file.path))//读取xml文件
        val children=document.rootElement.getChildren("declare-styleable")
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
                    val attributeNode= DefineAttributeNode(name,format)
                    //添加属性节点
                    viewNode.attributes.add(attributeNode)
                    if(null==format||"enum"==format){
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

    override fun createOrUpdate(item: MutableList<DefineViewNode>)=Unit
}