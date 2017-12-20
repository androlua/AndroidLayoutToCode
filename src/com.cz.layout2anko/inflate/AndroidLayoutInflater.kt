package com.cz.layout2anko.inflate

import com.cz.layout2anko.inflate.impl.View
import com.cz.layout2anko.inflate.impl.ViewGroup
import org.jdom.Element
import org.jdom.input.SAXBuilder
import java.io.File

/**
 * Created by cz on 2017/12/13.
 * android xml布局解析对象
 */
object AndroidLayoutInflater {
    val PACKAGE_NAME="com.cz.layout2anko.inflateAttributes.impl"
    fun inflater(file: File){
//        val file= File("src/com/cz/layout2anko/test/activity_test.xml")
        val builder = SAXBuilder()//实例JDOM解析器
        val document = builder.build(file)//读取xml文件

        //打印属性
        val out=StringBuilder()
        val rootElement=document.rootElement
        inflateElement(rootElement,out, ViewGroup.LayoutParams(),0)
        println(out)
    }

    private fun inflateElement(element: Element,out:StringBuilder,layoutParams:ViewGroup.LayoutParams,level:Int) {
        val name = element.name
        if (-1 < name.lastIndexOf(".")) {
            //TODO 当前为一个绝对路径,一般为自定义控件,取自定义控件模板
        } else {
            try {
                val clazz = Class.forName(PACKAGE_NAME + "." + name)
                val view = clazz.newInstance() as View
                val tab="".padEnd(level,'\t')
                out.append("$tab$name{\n")
                val value = view.convert(element, level)
                out.append(value)
                element.children.forEach{
                    val layoutParams=(view as ViewGroup).getLayoutParams()
                    inflateElement(it,out,layoutParams,level+1)
                }
                out.append("$tab}")
                //收集layoutParams属性
                layoutParams.inflateAttributes(element)
                if(2==layoutParams.attributes.size){
                    out.append(".lparams(")
                    out.append(layoutParams.attributes.joinToString(", "){it})
                    out.append(")")
                } else {
                    out.append(".lparams{\n")
                    layoutParams.attributes.forEach {
                        out.append("${"".padEnd(level+1,'\t')}$it\n")
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