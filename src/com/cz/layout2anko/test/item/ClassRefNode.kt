package item

import cz.sample.kotlin.parse.ClassRefItem


/**
 * Created by cz on 2017/12/18.
 * 类引用节点
 */
class ClassRefNode(val classRefItem: ClassRefItem){
    //类父节点
    var parent:ClassRefNode?=null
    //类子节点
    val children= mutableListOf<ClassRefNode>()

    override fun toString(): String {
        return classRefItem.className
    }
}