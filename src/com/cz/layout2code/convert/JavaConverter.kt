package com.cz.layout2code.convert

import com.cz.layout2code.inflate.element.ViewDefineConvertItem
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project
import com.intellij.util.containers.isNullOrEmpty

/**
 * Created by cz on 2018/3/1.
 */
class JavaConverter : ElementConverter() {
    val out=StringBuilder()

    /**
     * 转换代码为java输入
     */
    override fun convert(project: Project, node: ViewNode,layoutParams: ViewGroup.LayoutParams?): String {
        var layoutParams=layoutParams
        //从节点获取view
        val view = getViewFromNode(project, node)
        if(null==view){
            //遍历子孩子节点
            node.children.forEach {
                //遍历子节点
                convert(project,it,layoutParams)
            }
        } else {
            val tab="".padEnd(node.level,'\t')
            //装载属性
            view.inflateAttributes(node)
            //控件声明
            val viewDefineItem= ViewDefineConvertItem(view)
            out.append("$tab${viewDefineItem.toJavaString()}\n")
            //父容器
            var layoutDimension = layoutParams?.inflateLayoutDimension(node,false)
            var layoutAttributes =layoutParams?.inflateLayoutAttributes(node)
            if(view is ViewGroup){
                layoutParams = view.getLayoutParams()
            }
            //开始正常属性组合
            view.attributes.forEach {
                out.append("$tab${it.toJavaString()}\n")
            }
            //未知的属性引用集
            val unknownAttributes = view.getUnknownAttributes(node)
            unknownAttributes.forEach {
                out.append("$tab${it.toJavaString()}\n")
            }
            //遍历子孩子节点
            node.children.forEach {
                //遍历子节点
                convert(project,it,layoutParams)
            }
            //闭合节点
            out.append("$tab}")
            //layout属性设定
            val layoutAttributesEmpty=layoutAttributes.isNullOrEmpty()
            if(null!=layoutDimension){
                out.append(".${layoutDimension.toJavaString()}").
                        append(if(layoutAttributesEmpty)"\n" else "{\n")
            }
            //添加layout属性
            layoutAttributes?.forEach {
                out.append("$tab${it.toJavaString()}\n")
            }
            //闭合
            if(!layoutAttributesEmpty){
                out.append("$tab}\n")
            }
        }
        return out.toString()
    }

}