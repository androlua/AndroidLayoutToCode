package com.cz.layout2code.generate

import com.cz.layout2code.inflate.expression.value.DefineViewClassExpression
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.expression.value.CustomAttributeExpression
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementFactory
import com.intellij.util.containers.isNullOrEmpty
import org.jetbrains.kotlin.idea.core.replaced
import org.jetbrains.kotlin.j2k.getContainingMethod
import java.io.File

/**
 * Created by cz on 2018/3/1.
 */
class JavaCodeGenerate(project: Project, baseMatcher: BaseContext, clazz: PsiClass) : BaseCodeGenerate(project, baseMatcher, clazz) {
    companion object{
        const val LAYOUT_PARAMS="ViewGroup.LayoutParams"
    }
    private val out=StringBuilder()

    //控件名称记录
    private val widgetNameItems= mutableMapOf<String,Int>()

    /**
     * 转换代码为java输入
     */
    override fun generate(containingElement:PsiElement?,layoutFile: File, rootNode: ViewNode, layoutParams: ViewGroup.LayoutParams?) {
        WriteCommandAction.runWriteCommandAction(project){
            //组织布局方法
            val out=StringBuilder()
            val content=generateCode(rootNode,layoutParams,null)
            val layoutName = layoutName(layoutFile.name.substringBefore("."))
            out.append("private View get$layoutName(){\n")
            out.append("$content")
            out.append("}")

            //插入方法
            val factory = JavaPsiFacade.getElementFactory(project)
            //替换当前语句
            var containingMethod = containingElement?.getContainingMethod()
            if(null==containingMethod){
                containingMethod=clazz.methods.last()
            }
            if(null!=containingMethod){
                val generateMethod = factory.createMethodFromText(out.toString(), null)
                clazz?.addAfter(generateMethod, containingMethod)
            }
            if(null!=containingElement){
                val referenceElement=containingElement.firstChild.lastChild
                //装载布局方式
                val methodName=referenceElement?.text
                var statement:PsiElement?=null
                if("setContentView"==methodName){
                    statement = factory.createStatementFromText("$methodName(this.get$layoutName())", clazz)
                } else if("inflate"==methodName){
                    statement = factory.createStatementFromText("this.get$layoutName();", clazz)
                    //删除最后一个;号,这个;号好像因为缺少换行,会导致莫名的报错
                    containingElement?.parent?.children?.last()?.delete()
                }
                //替换表达式
                if(null!=statement){
                    containingElement.replaced(statement)
                }
            }
        }
    }

    private fun generateCode(node: ViewNode, layoutParams: ViewGroup.LayoutParams?, parentName:String?): String {
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
            val viewDefineItem= DefineViewClassExpression(view,node.name,viewName)
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
                if(it is CustomAttributeExpression){
                    //这里提示自定义控件暂不支持
                    out.append("${it.getJavaExpression(baseMatcher)};\n")
                } else {
                    out.append("$viewName.${it.getJavaExpression(baseMatcher)};\n")
                }
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
                    out.append("$viewName.setLayoutParams(${layoutDimension.getJavaExpression(baseMatcher)});\n")
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
            generateCode(it,layoutParams,parentViewName)
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