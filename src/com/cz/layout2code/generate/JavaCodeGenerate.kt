package com.cz.layout2code.generate

import com.cz.layout2code.inflate.expression.value.DefineViewClassExpression
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.ClassReferences
import com.cz.layout2code.inflate.expression.value.CustomAttributeExpression
import com.cz.layout2code.inflate.item.ImportItem
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.containers.isNullOrEmpty
import org.jetbrains.kotlin.idea.core.replaced
import org.jetbrains.kotlin.j2k.getContainingMethod
import java.io.File

/**
 * Created by cz on 2018/3/1.
 */
class JavaCodeGenerate(project: Project, context: BaseContext, clazz: PsiClass) : BaseCodeGenerate(project, context, clazz) {

    companion object{
        const val LAYOUT_PARAMS="ViewGroup.LayoutParams"
    }
    private val out=StringBuilder()
    //导入包
    private val importList= mutableSetOf<ImportItem>()
    //控件名称记录
    private val widgetNameItems= mutableMapOf<String,Int>()

    /**
     * 转换代码为java输入
     */
    override fun generate(file:PsiFile,containingElement:PsiElement?,layoutFile: File, rootNode: ViewNode, layoutParams: ViewGroup.LayoutParams?) {
        WriteCommandAction.runWriteCommandAction(project){
            //组织布局方法
            val out=StringBuilder()
            val content=generateCode(rootNode,layoutParams,null)
            val layoutName = layoutName(layoutFile.name.substringBefore("."))
            out.append("private View get$layoutName(){\n")
            //添加场景前置表达式
            val preExpressions = context.getPreExpressions()
            preExpressions.forEach { _,value->
                out.append("${value.getJavaExpression(context)}\n")
            }
            //添加内容体
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
            //导入表达式信息
            val file=file as PsiJavaFile
            val javaPsiFacade = JavaPsiFacade.getInstance(project)
            val globalSearchScope = GlobalSearchScope.everythingScope(project)

            //获取所有己导入信息
            importClassRef(file, javaPsiFacade, globalSearchScope, factory)
            //替换调用表达式
            replaceCallElement(containingElement, factory, layoutName)
        }
    }

    /**
     * 导入所有涉及class对象
     */
    private fun importClassRef(file: PsiJavaFile, javaPsiFacade: JavaPsiFacade, globalSearchScope: GlobalSearchScope, factory: PsiElementFactory) {
        val allImportReference = file.importList?.allImportStatements?.mapNotNull { it.importReference?.text }
        importList.forEach(::println)
//        importList.forEach {
//            val classReference = it.getClassReference()
//            if (null == allImportReference || allImportReference.none { it == classReference }) {
//                val findClass = javaPsiFacade.findClass(classReference, globalSearchScope)
//                //导入此class
//                if (null != findClass) {
//                    val importStatement = factory.createImportStatement(findClass)
//                    if (null != importStatement) {
//                        file.importList?.add(importStatement)
//                    }
//                }
//            }
//        }
    }

    /**
     * 替换调用表达式对象
     */
    private fun replaceCallElement(containingElement: PsiElement?, factory: PsiElementFactory, layoutName: String) {
        if (null != containingElement) {
            val referenceElement = containingElement.firstChild.lastChild
            //装载布局方式
            val methodName = referenceElement?.text
            var statement: PsiElement? = null
            if ("setContentView" == methodName) {
                statement = factory.createStatementFromText("$methodName(this.get$layoutName());", clazz)
            } else if ("inflate" == methodName) {
                statement = factory.createStatementFromText("this.get$layoutName();", clazz)
            }
            //删除最后一个;号,这个;号好像因为缺少换行,会导致莫名的报错
            containingElement?.parent?.children?.last()?.delete()
            //替换表达式
            if (null != statement) {
                containingElement.replaced(statement)
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
            //控件声
            var referenceName=node.name
            //系统控件,分为二个包下view/widgets,所以动态从引用库索引
            if(node.isSystemView){
                val classItem=ClassReferences.getClassItem(node.name)
                if(null!=classItem){
                    referenceName=classItem.referenceName
                }
            }
            val viewDefineItem=DefineViewClassExpression(view,referenceName,viewName)
            out.append("${viewDefineItem.getJavaExpression(context)}\n")
            importList+=viewDefineItem.getImportList()
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
                //加入表达式导包
                importList+=it.getImportList()
                //添加表达式信息
                if(it is CustomAttributeExpression){
                    //这里提示自定义控件暂不支持
                    out.append("${it.getJavaExpression(context)};\n")
                } else {
                    out.append("$viewName.${it.getJavaExpression(context)};\n")
                }
            }
            //未知的属性引用集
            val unknownAttributes = node.getUnknownAttributeExpressions()
            unknownAttributes.forEach {
                out.append("${it.getJavaExpression(context)}\n")
            }
            //layout属性设定
            val count = widgetNameItems.getOrPut(LAYOUT_PARAMS) { 0 }
            val layoutParamsName="layoutParams$count"
            if(null!=layoutDimension){
                //加入导入信息
                importList+=layoutDimension.getImportList()
                if(layoutAttributes.isNullOrEmpty()){
                    out.append("$viewName.setLayoutParams(${layoutDimension.getJavaExpression(context)});\n")
                } else {
                    //记录控件个数
                    widgetNameItems[LAYOUT_PARAMS]=count+1
                    out.append("ViewGroup.LayoutParams $layoutParamsName = ${layoutDimension.getJavaExpression(context)};\n")
                    out.append("$viewName.setLayoutParams($layoutParamsName);\n")
                }
            }
            //添加layout属性
            layoutAttributes?.forEach {
                out.append("$layoutParamsName.${it.getJavaExpression(context)};\n")
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