package com.cz.layout2anko.inflate

import com.cz.layout2anko.inflate.impl.View
import com.cz.layout2anko.inflate.impl.ViewGroup
import com.cz.layout2anko.inflate.item.LayoutParamsConvertItem
import com.intellij.util.containers.isNullOrEmpty
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
    val PACKAGE_NAME="com.cz.layout2anko.inflate.impl"
    fun inflater(file: File,toAnko:Boolean=true){
//        val file= File("src/com/cz/layout2anko/test/activity_test.xml")
        val builder = SAXBuilder()//实例JDOM解析器
        val document = builder.build(file)//读取xml文件

        //打印属性
        val out=StringBuilder()
        val rootElement=document.rootElement
        inflateElement(rootElement,out, ViewGroup.LayoutParams(),0,toAnko)
        println(out)
    }

    private fun inflateElement(element: Element,out:StringBuilder,layoutParams:ViewGroup.LayoutParams,level:Int,toAnko:Boolean=true) {
        val name = element.name
        if (-1 < name.lastIndexOf(".")) {
            //TODO 当前为一个绝对路径,一般为自定义控件,取自定义控件模板
        } else {
            try {
                val clazz = Class.forName(PACKAGE_NAME + "." + name)
                val view = clazz.newInstance() as View
                val tab="".padEnd(level,'\t')
                out.append("$tab${view.getViewName()}{\n")
                val value = view.convert(element, level,toAnko)
                out.append(value)
                element.children.forEach{
                    val layoutParams=(view as ViewGroup).getLayoutParams()
                    inflateElement(it,out,layoutParams,level+1)
                }
                out.append("$tab}")
                //收集layoutParams属性
                layoutParams.inflateAttributes(element)
                val layoutParamsItem=layoutParams.attributes.find { it is LayoutParamsConvertItem }
                if(null!=layoutParamsItem){
                    layoutParams.attributes.remove(layoutParamsItem)
                    out.append(".lparams(")
                    if(toAnko){
                        out.append(layoutParamsItem.toAnkoString())
                    } else {
                        out.append(layoutParamsItem.toJavaString()+"\n")
                    }
                    out.append(")")
                }
                if(layoutParams.attributes.isNotEmpty()){
                    out.append("{\n")
                    val paramsTab="".padEnd(level+1,'\t')
                    layoutParams.attributes.forEach {
                        if(toAnko){
                            it.toAnkoString().lines().forEach {
                                out.append("$paramsTab$it\n")
                            }
                        } else {
                            out.append(it.toJavaString()+"\n")
                        }
                    }
                    out.append("$tab}")
                }
                out.append("\n")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}