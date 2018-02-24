package com.cz.layout2code.inflate.item

import java.util.*

/**
 * Created by cz on 2018/2/1.
 */
class DefineViewNode(var packageName:String?,val name:String){
    val attributes= mutableListOf<DefineAttributeNode>()
    val qualifiedName=packageName+"."+name
    override fun toString()=name
}
/**
 * 预定义节点
 */
class DefineAttributeNode(var name:String, var format:String?):Cloneable{
    //备选method,让排序因子从大到小排,乘以10000上的为让不大于1的浮点型数据可比较
    val methods= TreeMap<Float,String>({ v1, v2-> ((v2-v1)*10000).toInt() })
    //定义的method
    var defineMethod:String?=null
    //属性子集如:flag/enum
    var items= mutableMapOf<String,Int>()
    //选中位置,并动态设置预声明方法
    var selectIndex=0
        set(value) {
            defineMethod=methods.values.toTypedArray()[value]
        }
    override fun toString()=name

    override public fun clone(): DefineAttributeNode {
        val item = super.clone() as DefineAttributeNode
        item.name=name
        item.format=format
        item.methods+=methods
        item.defineMethod=defineMethod
        item.selectIndex=selectIndex
        item.items.putAll(items)
        return item
    }
}
