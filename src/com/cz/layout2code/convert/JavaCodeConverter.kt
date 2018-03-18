package com.cz.layout2code.convert

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.DefineClassExpression
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.matcher.BaseClassMatcher
import com.intellij.openapi.project.Project
import com.intellij.util.containers.isNullOrEmpty

/**
 * Created by cz on 2018/3/1.
 */
class JavaCodeConverter : BaseCodeConverter() {
    companion object{
        const val LAYOUT_PARAMS="ViewGroup.LayoutParams"
    }
    private val out=StringBuilder()
    //导包列
    private val importItems= mutableListOf<ImportItem>()
    //控件名称记录
    private val widgetNameItems= mutableMapOf<String,Int>()
    /**
     * 转换代码为java输入
     */
    override fun convert(project: Project,classMatcher: BaseClassMatcher, node: ViewNode,layoutParams: ViewGroup.LayoutParams?): String {
        return convertToJava(project,classMatcher,node,layoutParams,null)
    }

    private fun convertToJava(project: Project,classMatcher: BaseClassMatcher, node: ViewNode,layoutParams: ViewGroup.LayoutParams?,parentName:String?): String {
        var parentName=parentName
        //当前为根节点
        val isRoot=null==layoutParams
        var layoutParams=layoutParams
        //查找id节点
        val attributeNode = node.attributes.find { "android" == it.nameSpace && "id" == it.name }
        //控件名称
        val widgetName:String
        if(null!=attributeNode){
            widgetName=idName(attributeNode.value)
        } else {
            //记录控件名称
            val count = widgetNameItems.getOrPut(node.name) { 0 }
            //记录控件个数
            widgetNameItems[node.name]=count+1
            widgetName=firstLowerCharacter(node.simpleName)+count
        }
        //从节点获取view
        val view = getViewFromNode(project, node)
        if(null!=view){
            val tab="".padEnd(node.level,'\t')
            //装载属性
            view.inflateAttributes(node)
            //控件声明
            val viewDefineItem= DefineClassExpression(view,widgetName)
            out.append("$tab${viewDefineItem.getJavaExpression(classMatcher)}\n")
            //添加到父控件
            if(null!=parentName){
                out.append("$tab$parentName.addView($widgetName);\n")
            }
            //父容器
            var layoutDimension = layoutParams?.inflateLayoutDimension(node)
            var layoutAttributes =layoutParams?.inflateLayoutAttributes(node)
            if(view is ViewGroup){
                layoutParams = view.getLayoutParams()
            }
            //开始正常属性组合
            view.expressions.forEach {
                out.append("$tab$widgetName.${it.getJavaExpression(classMatcher)};\n")
            }
            //未知的属性引用集
            val unknownAttributes = node.getUnknownAttributeExpressions()
            unknownAttributes.forEach {
                out.append("$tab${it.getJavaExpression(classMatcher)}\n")
            }
            //layout属性设定
            val count = widgetNameItems.getOrPut(LAYOUT_PARAMS) { 0 }
            //记录控件个数
            widgetNameItems[LAYOUT_PARAMS]=count+1
            val layoutParamsName="layoutParams$count"
            if(null!=layoutDimension){
                if(layoutAttributes.isNullOrEmpty()){
                    out.append("$tab$widgetName.setLayoutParams(new ${layoutDimension.getJavaExpression(classMatcher)});\n")
                } else {
                    out.append("${tab}ViewGroup.LayoutParams $layoutParamsName = ${layoutDimension.getJavaExpression(classMatcher)};\n")
                    out.append("$tab$widgetName.setLayoutParams($layoutParamsName);\n")
                }
            }
            //添加layout属性
            layoutAttributes?.forEach {
                out.append("$tab$layoutParamsName.${it.getJavaExpression(classMatcher)};\n")
            }
            //记录节点名称
            parentName=widgetName
            //结束换行
            out.append("\n")
        }
        //遍历子孩子节点
        node.children.forEach {
            //遍历子节点
            convertToJava(project,classMatcher,it,layoutParams,parentName)
        }
        if(isRoot){
            out.append("return $widgetName;\n")
        }
        return out.toString()
    }

}