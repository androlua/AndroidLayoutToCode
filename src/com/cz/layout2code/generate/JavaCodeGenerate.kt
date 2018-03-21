package com.cz.layout2code.generate

import com.cz.layout2code.inflate.expression.value.DefineViewClassExpression
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.inflate.ClassReferences
import com.cz.layout2code.inflate.expression.value.CommentExpression
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.impl.custom.ViewWrapper
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
    //控件名称记录
    private val widgetNameItems= mutableMapOf<String,Int>()

    /**
     * 转换代码为java输入
     */
    override fun generate(file:PsiFile,containingElement:PsiElement?,layoutFile: File, rootNode: ViewNode, layoutParams: ViewGroup.LayoutParams) {
        WriteCommandAction.runWriteCommandAction(project){
            //组织布局方法
            val out=StringBuilder()
            val content=generateCode(rootNode,layoutParams,null)
            val layoutName = layoutName(layoutFile.name.substringBefore("."))
            val methodName="get$layoutName"
            //导入View包
            importList.add(ImportItem("android.view.View"))
            out.append("private View $methodName(){\n")
            //添加场景前置表达式
            val preExpressions = context.getPreExpressions()
            preExpressions.forEach { _,value->
                out.append("${callJavaExpression(value,context)}\n")
            }
            //添加内容体
            out.append("$content")
            out.append("}")

            //插入方法
            val factory = JavaPsiFacade.getElementFactory(project)
            var containingMethod = containingElement?.getContainingMethod()
            if(null==containingMethod){
                containingMethod=clazz.methods.last()
            }

            //插入生成方法体
            ensureMethodItem(methodName)
            val generateMethod = factory.createMethodFromText(out.toString(), null)
            clazz?.addAfter(generateMethod, containingMethod)

            //插入前置方法
            val methodExpressions = context.getPreMethodExpressions()
            methodExpressions.forEach { _, expression ->
                //创建前置方法
                ensureMethodItem(expression.methodName)
                val generateMethod = factory.createMethodFromText(callJavaExpression(expression,context), null)
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
            //弹出消息提示
            MessageDelegate.logEventMessage("Generate:${layoutFile.name} to method:$methodName complete!")
        }
    }

    /**
     * 导入所有涉及class对象
     */
    private fun importClassRef(file: PsiJavaFile, javaPsiFacade: JavaPsiFacade, globalSearchScope: GlobalSearchScope, factory: PsiElementFactory) {
        val allImportReference = file.importList?.allImportStatements?.mapNotNull { it.importReference?.text }
        importList.forEach {
            val classReference = it.getClassReference()
            if (null == allImportReference || allImportReference.none { it == classReference }) {
                val findClass = javaPsiFacade.findClass(classReference, globalSearchScope)
                //导入此class
                if (null != findClass) {
                    val importStatement = factory.createImportStatement(findClass)
                    if (null != importStatement) {
                        file.importList?.add(importStatement)
                    }
                }
            }
        }
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

    private fun generateCode(node: ViewNode, parentLayoutParams: ViewGroup.LayoutParams, parentName:String?): String {
        var parentViewName=parentName
        //当前为根节点
        var parentLayoutParams=parentLayoutParams
        val viewName=getViewName(node)
        //从节点获取view
        val view = getViewFromNode(project, node)
        if(null==view){
            //该控件查找不到模板
            out.append("\t//${node.name} is not found\n")
            node.attributes.forEach {
                out.append("\t//${it.nameSpace}:${it.name}=${it.value}\n")
            }
            out.append("\n")
        } else {
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
            out.append("${callJavaExpression(viewDefineItem,context)}\n")
            //添加到父控件
            if(null!=parentViewName){
                out.append("$parentViewName.addView($viewName);\n")
            }
            //装载layoutParams属性
            var layoutDimension = parentLayoutParams.inflateLayoutDimension(node)
            parentLayoutParams?.inflateLayoutAttributes(node)
            //开始正常属性组合
            view.expressions.forEach {
                //添加表达式信息
                if(it is CommentExpression){
                    //这里提示自定义控件暂不支持
                    out.append("${callJavaExpression(it,context)};\n")
                } else {
                    out.append("$viewName.${callJavaExpression(it,context)};\n")
                }
            }
            //未知的属性引用集
            val unknownAttributes = node.getUnknownAttributeExpressions()
            unknownAttributes.forEach {
                out.append("${callJavaExpression(it,context)}\n")
            }
            //layout属性设定
            val count = widgetNameItems.getOrPut(LAYOUT_PARAMS) { 0 }
            val layoutParamsName="layoutParams$count"
            val layoutExpressions = parentLayoutParams.expressions
            if(null!=layoutDimension){
                if(layoutExpressions.isNullOrEmpty()){
                    out.append("$viewName.setLayoutParams(${callJavaExpression(layoutDimension,context)});\n")
                } else {
                    //记录控件个数
                    widgetNameItems[LAYOUT_PARAMS]=count+1
                    val layoutParamsClassName=parentLayoutParams::class.java.name.substringAfterLast(".")
                    out.append("${layoutParamsClassName.replace("$",".")} $layoutParamsName = ${callJavaExpression(layoutDimension,context)};\n")
                    out.append("$viewName.setLayoutParams($layoutParamsName);\n")
                }
            }
            //添加layout属性
            layoutExpressions?.forEach {
                out.append("$layoutParamsName.${callJavaExpression(it,context)};\n")
            }
            //记录当前控件的LayoutParams
            val layoutParams = getLayoutParams(view)
            if(null!=layoutParams){
                parentLayoutParams = layoutParams
            }
            //记录节点名称
            parentViewName=viewName
            //结束换行
            out.append("\n")
        }
        //遍历子孩子节点
        node.children.forEach {
            //遍历子节点
            generateCode(it,parentLayoutParams,parentViewName)
        }
        if(null==parentName){
            out.append("return $viewName;\n")
        }
        return out.toString()
    }

    /**
     * 获得控件的LayoutParams对象
     */
    private fun getLayoutParams(view: View): ViewGroup.LayoutParams? {
        var view=view
        if (view is ViewWrapper) {
            //自定义控件取父控件节点
            view = view.getWrapperView()
        }
        var layoutParams: ViewGroup.LayoutParams?=null
        if (view is ViewGroup) {
            layoutParams = view.getLayoutParams()
        }
        return layoutParams
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