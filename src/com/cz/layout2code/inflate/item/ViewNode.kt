package com.cz.layout2code.inflate.item

/**
 * Created by cz on 2018/2/1.
 * xml节点信息
 */
class ViewNode(val name:String,val level:Int){
    //类简称
    var simpleName:String?=null
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
            simpleName=name.substring(index+1)
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


    override fun toString()=name
}

/**
 * 属性节点
 */
class AttributeNode(val nameSpace:String,val name:String,val value:String){
    override fun toString()=name
}