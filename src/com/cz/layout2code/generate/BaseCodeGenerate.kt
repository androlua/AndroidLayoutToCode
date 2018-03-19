package com.cz.layout2code.generate

import com.cz.layout2code.inflate.RESOURCE_PATTERN
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.impl.custom.CustomViewWrapper
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.search.GlobalSearchScope
import java.io.File

/**
 * Created by cz on 2018/3/1.
 */
abstract class BaseCodeGenerate(val project: Project,val baseMatcher: BaseContext,val clazz:PsiClass) {
     private val PACKAGE_NAME="com.cz.layout2code.inflate.impl"
     /**
      * 转换对应语言文本
      */
     abstract fun generate(containingElement: PsiElement?, layoutFile: File, node: ViewNode, layoutParams: ViewGroup.LayoutParams?=null)
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
               view=getParentView(project, node)
               view?.isCompatView = true
               return view
          } else {
               //系统控件
               try {
                    //com.cz.layout2code.inflate.impl.LinearLayout
                    val clazz = Class.forName(PACKAGE_NAME+"." + node.name)
                    view = clazz.newInstance() as View
               } catch (e: Exception) {
                    print("$PACKAGE_NAME.${node.name} not found!")
               }
          }
          return view
     }

     /**
      * 查找到系统基类view
      */
     private fun getParentView(project: Project, node: ViewNode):View? {
          var findClass = JavaPsiFacade.getInstance(project).findClass(node.name, GlobalSearchScope.everythingScope(project))
          while (null != findClass) {
               val qualifiedName = findClass.qualifiedName
               if (null != qualifiedName) {
                    val index = qualifiedName.lastIndexOf(".")
                    val packageName = qualifiedName.substring(0, index)
                    val className = qualifiedName.substring(index + 1)
                    //代表为系统控件
                    if (CustomViewWrapper.androidPackages.any{it==packageName}) {
                         val classNode = CustomViewWrapper.viewNodes.find { className == it.className }
                         return if(null!=classNode){
                              try{
                                   Class.forName(PACKAGE_NAME +"."+classNode.className).newInstance() as? View
                              } catch (e:Exception){
                                   null
                              }
                         } else null
                    }
               }
               findClass = findClass.superClass
          }
          return null
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
}