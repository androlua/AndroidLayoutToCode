package com.cz.layout2code.generate

import com.cz.layout2code.inflate.RESOURCE_PATTERN
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.impl.custom.CustomViewWrapper
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.inflate.impl.WIDGET_PACKAGE
import com.cz.layout2code.inflate.impl.custom.CompatViewWrapper
import com.cz.layout2code.inflate.item.ImportItem
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope
import java.io.File

/**
 * Created by cz on 2018/3/1.
 */
abstract class BaseCodeGenerate(val project: Project, val context: BaseContext, val clazz:PsiClass) {
    //导入包
    protected val importList= mutableSetOf<ImportItem>()
     /**
      * 转换对应语言文本
      */
     abstract fun generate(file:PsiFile,containingElement: PsiElement?, layoutFile: File, node: ViewNode, layoutParams: ViewGroup.LayoutParams)
     /**
      * 从节点获取到view体
      */
     protected fun getViewFromNode(project: Project, node: ViewNode): View? {
          var view: View?=null
          if (node.isCustomView) {
               //进行自定义控件包装
               view= CustomViewWrapper.wrapper(project, node)
          } else if(node.isCompatView){
               //v7
              view= CompatViewWrapper.wrapper(project, node)
          } else {
               //系统控件
               try {
                    //com.cz.layout2code.inflate.impl.LinearLayout
                    val clazz = Class.forName(WIDGET_PACKAGE+"." + node.name)
                    view = clazz.newInstance() as View
               } catch (e: Exception) {
                    print("$WIDGET_PACKAGE.${node.name} not found!")
               }
          }
          return view
     }

     inline fun idName(id:String):String{
          return id(id).split("_").mapIndexed { index, s -> if(0==index) s else firstUpperCharacter(s) }.reduce { acc, s -> acc+s }
     }

     inline fun layoutName(name:String):String{
          return name.split("_").map { s -> firstUpperCharacter(s) }.reduce { acc, s -> acc+s }
     }

     inline fun firstUpperCharacter(value:String)=value[0].toUpperCase()+value.substring(1)

     inline fun firstLowerCharacter(value:String)=value[0].toLowerCase()+value.substring(1)

     fun id(value:String):String{
          //资源引用
          var result=value
          val matcher= RESOURCE_PATTERN.matcher(value)
          if(matcher.find()) {
               result = matcher.group("ref")
          }
          return result
     }


     /**
      * 查找指定方法
      */
     protected fun findMethodItem(methodName: String): PsiMethod? {
          val methodItems = clazz.findMethodsByName(methodName, false)
          return if (methodItems.isNotEmpty()) methodItems.first() else null
     }

     /**
      * 检测方法是否存在,存在则直接删除
      */
     protected fun ensureMethodItem(methodName: String) {
          val findMethod = findMethodItem(methodName)
          if (null != findMethod) {
               findMethod.delete()
               MessageDelegate.logEventMessage("Generate:$methodName but it existed, Deleted it!")
          }
     }

    protected fun callJavaExpression(expression:ElementExpression,context:BaseContext):String{
        importList+=expression.getImportList()
        return expression.getJavaExpression(context)
    }

    protected fun callKotlinExpression(expression:ElementExpression,context:BaseContext):String{
        importList+=expression.getImportList()
        return expression.getKotlinExpression(context)
    }
}