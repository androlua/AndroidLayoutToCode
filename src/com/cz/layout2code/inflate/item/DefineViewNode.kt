package com.cz.layout2code.inflate.item

/**
 * Created by cz on 2018/2/1.
 */
class DefineViewNode(val name:String){
    val attributes= mutableListOf<AttributeDefineNode>()
    override fun toString()=name
}
/**
 * 预定义节点
 */
class AttributeDefineNode(val name:String,val format:String?){
    var items= mutableMapOf<String,Int>()
    override fun toString()=name
}
