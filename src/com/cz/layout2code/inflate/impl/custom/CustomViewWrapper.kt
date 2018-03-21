package com.cz.layout2code.inflate.impl.custom

import com.cz.layout2code.inflate.expression.value.CommentExpression
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project

/**
 * Created by cz on 2018/2/27.
 * 标记自定义控件包装器对象,用于动态化的控件解析
 * 自定义控件,在包装系统控件时,还要形成一个自定义控件继承自定义控件的继承层级解析过程.所以,还会有一个调用链,一直链到系统控件
 *
 * 因自定义控件有一整套庞大设计,包括配置,检索引入包下控件,对应控件方法,还有自定义控件的属性调用链.导致时间太长,初版暂时屏蔽
 */
class CustomViewWrapper(val parentView:View, val node:ViewNode) :ViewWrapper(){

    companion object {
        /**
         * 通过包装扩展自定义控件列
         */
        fun wrapper(project: Project, node:ViewNode): ViewWrapper? {
            //查找此类是否存在,并一直追查到类的继承到父级
            var result: CustomViewWrapper?=null
            //设定代理view
            val parentView = getParentView(project, node)
            if(null!=parentView) {
                //父级对象,增加自定义控件属性
                result=CustomViewWrapper(parentView,node)
            }
            return result
        }
    }

    override fun getViewName(): String=node.name

    override fun getClassPath(): String =node.name

    override fun inflateAttributes(viewNode: ViewNode) {
        //先装载被包装父属性
        parentView.inflateAttributes(viewNode)
        //自定义属性
        val customAttributes = node.attributes.filter { "android" != it.nameSpace }
        //包装自定义属性
        customAttributes.forEach {
            applyAttributes(it)
            expressions.add(CommentExpression("\t//Custom attribute ${it.name}=${it.value}"))
        }
    }

    override fun getWrapperView(): View =parentView
}