package com.cz.layout2code.inflate.item

import java.util.*

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
class AttributeDefineNode(val name:String,var format:String?){
    //备选method
    val methods=TreeMap<Float,String>()
    //定义的method
    var defineMethod:String?=null
    //属性子集如:flag/enum
    var items= mutableMapOf<String,Int>()
    override fun toString()=name
}
