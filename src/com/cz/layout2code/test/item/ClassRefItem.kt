package cz.sample.kotlin.parse

/**
 * Created by cz on 2017/12/16.
 */
class ClassRefItem{
    //类名
    var className=String()
    //基类定义
    var superClass:String?=null
    //所有引用声明
    val refItems= mutableListOf<String>()
    //引用信息
    val attributes= mutableListOf<String>()
    //内部类引用
    val innerClassRefItems= mutableListOf<ClassRefItem>()

    override fun toString(): String {
        return className
    }
}