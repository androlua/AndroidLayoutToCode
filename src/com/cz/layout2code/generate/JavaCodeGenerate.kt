package com.cz.layout2code.generate

import com.cz.layout2code.inflate.expression.value.DefineClassExpression
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.intellij.openapi.project.Project
import com.intellij.util.containers.isNullOrEmpty

/**
 * Created by cz on 2018/3/1.
 */
class JavaCodeGenerate : BaseCodeGenerate() {
    companion object{
        const val LAYOUT_PARAMS="ViewGroup.LayoutParams"
    }
    private val out=StringBuilder()
    //控件名称记录
    private val widgetNameItems= mutableMapOf<String,Int>()
    /**
     * 转换代码为java输入
     */
    override fun generate(project: Project, baseMatcher: BaseContext, rootNode: ViewNode, layoutParams: ViewGroup.LayoutParams?) {
        val out=convert(project,baseMatcher,rootNode,layoutParams,null)

    }

    private fun convert(project: Project, baseMatcher: BaseContext, node: ViewNode, layoutParams: ViewGroup.LayoutParams?, parentName:String?): String {
        var parentViewName=parentName
        //当前为根节点
        var layoutParams=layoutParams
        val viewName=getViewName(node)
        //从节点获取view
        val view = getViewFromNode(project, node)
        if(null!=view){
            //装载属性
            view.inflateAttributes(node)
            //控件声明
            val viewDefineItem= DefineClassExpression(view,node.name,viewName)
            out.append("${viewDefineItem.getJavaExpression(baseMatcher)}\n")
            //添加到父控件
            if(null!=parentViewName){
                out.append("$parentViewName.addView($viewName);\n")
            }
            //父容器
            var layoutDimension = layoutParams?.inflateLayoutDimension(node)
            var layoutAttributes =layoutParams?.inflateLayoutAttributes(node)
            if(view is ViewGroup){
                layoutParams = view.getLayoutParams()
            }
            //开始正常属性组合
            view.expressions.forEach {
                out.append("$viewName.${it.getJavaExpression(baseMatcher)};\n")
            }
            //未知的属性引用集
            val unknownAttributes = node.getUnknownAttributeExpressions()
            unknownAttributes.forEach {
                out.append("${it.getJavaExpression(baseMatcher)}\n")
            }
            //layout属性设定
            val count = widgetNameItems.getOrPut(LAYOUT_PARAMS) { 0 }
            //记录控件个数
            widgetNameItems[LAYOUT_PARAMS]=count+1
            val layoutParamsName="layoutParams$count"
            if(null!=layoutDimension){
                if(layoutAttributes.isNullOrEmpty()){
                    out.append("$viewName.setLayoutParams(${layoutDimension.getJavaExpression(baseMatcher)};\n")
                } else {
                    out.append("ViewGroup.LayoutParams $layoutParamsName = ${layoutDimension.getJavaExpression(baseMatcher)};\n")
                    out.append("$viewName.setLayoutParams($layoutParamsName);\n")
                }
            }
            //添加layout属性
            layoutAttributes?.forEach {
                out.append("$layoutParamsName.${it.getJavaExpression(baseMatcher)};\n")
            }
            //记录节点名称
            parentViewName=viewName
            //结束换行
            out.append("\n")
        }
        //遍历子孩子节点
        node.children.forEach {
            //遍历子节点
            convert(project,baseMatcher,it,layoutParams,parentViewName)
        }
        if(null==parentName){
            out.append("return $viewName;\n")
        }
        return out.toString()
    }

    /**
     * 获得控件节点声明名称
     */
    private fun getViewName(node:ViewNode):String{
        //控件名称
        val viewName:String
        //查找id节点
        val attributeNode = node.attributes.find { "android" == it.nameSpace && "id" == it.name }
        if(null!=attributeNode){
            viewName=idName(attributeNode.value)
        } else {
            //记录控件名称
            val count = widgetNameItems.getOrPut(node.name) { 0 }
            //记录控件个数
            widgetNameItems[node.name]=count+1
            viewName=firstLowerCharacter(node.simpleName)+count
        }
        return viewName
    }

}