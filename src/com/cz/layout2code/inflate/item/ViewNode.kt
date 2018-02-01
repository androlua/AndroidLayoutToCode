package com.cz.layout2code.inflate.item

/**
 * Created by cz on 2018/2/1.
 * xml节点信息
 */
class ViewNode(val name:String){
    var parent:ViewNode?=null
    var children= mutableListOf<ViewNode>()
    val attributes= mutableListOf<AttributeNode>()
    val isCompatView:Boolean
    val isSystemView:Boolean
    val isCustomView:Boolean

    init {
        val index=name.lastIndexOf(".")
        if(-1==index){
            isCompatView=false
            isCustomView=false
            isSystemView=true
        } else {
            isSystemView=false
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
class AttributeNode(val nameSpace:String,val name:String,value:String?){
    override fun toString()=name
}