package com.cz.layout2code.convert

import com.cz.layout2code.inflate.AndroidLayoutInflater
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.impl.custom.CustomViewWrapper
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project

/**
 * Created by cz on 2018/3/1.
 */
abstract class ElementConverter {
     /**
      * 转换对应语言文本
      */
     abstract fun convert(project: Project, node: ViewNode,layoutParams: ViewGroup.LayoutParams?=null):String
     /**
      * 从节点获取到view体
      */
     protected fun getViewFromNode(project: Project,viewNode: ViewNode): View? {
          var view: View?=null
          if (viewNode.isCustomView) {
               //进行自定义控件包装
               view= CustomViewWrapper.wrapper(project, viewNode)
          } else {
               //系统控件或v7
               try {
                    val clazz = Class.forName(AndroidLayoutInflater.packageName + "." + viewNode.name)
                    view = clazz.newInstance() as View
                    view.isCompatView = viewNode.isCompatView
               } catch (e: Exception) {
               }
          }
          return view
     }
}