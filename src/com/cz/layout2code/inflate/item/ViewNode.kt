package com.cz.layout2code.inflate.item

import com.cz.layout2code.inflate.expression.value.UnknownAttributeExpression
import com.cz.layout2code.inflate.expression.value.ElementExpression

/**
 * Created by cz on 2018/2/1.
 * xml节点信息
 */
class ViewNode(val name:String,val level:Int){
    //类简称
    var simpleName:String=name.substringAfterLast(".")
    var parent:ViewNode?=null
    var children= mutableListOf<ViewNode>()
    val attributes= mutableListOf<AttributeNode>()
    val isCompatView:Boolean
    val isSystemView:Boolean
    val isCustomView:Boolean
    //预声明的控件节点信息
    var widgetAttr:DefineViewNode?=null

    init {
        val index=name.lastIndexOf(".")
        if(-1==index){
            isCompatView=false
            isCustomView=false
            isSystemView=true
        } else {
            isSystemView=false
            //记录类简称
            val packageName=name.substring(0,index)
            if("android.support.v7.widget"==packageName){
                isCompatView=true
                isCustomView=false
            } else {
                isCompatView=false
                isCustomView=true
            }
        }
    }

    /**
     * 获取控件节点内,未使用的属性
     */
    fun getUnknownAttributeExpressions():MutableList<ElementExpression>{
        //未应用属性
        val unknownExpressions = mutableListOf<ElementExpression>()
        val uselessAttributes = attributes.filter { !it.isApply }
        uselessAttributes.forEach {
            //添加未知属性转换器
            unknownExpressions.add(UnknownAttributeExpression(it.name, it.value))
        }
        return unknownExpressions
    }

    override fun toString()=name
}

/**
 * 属性节点
 */
class AttributeNode(val nameSpace:String,val name:String,val value:String){
    //属性是否应用
    var isApply=false
    override fun toString()=name
}