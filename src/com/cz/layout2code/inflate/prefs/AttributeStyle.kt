package com.cz.layout2code.inflate.prefs

/**
 * Created by cz on 2017/12/25.
 */
class AttributeStyle {
    //属性字段名称
    lateinit var field:String
    //需要导入包
    var importList:Array<String>?=null
    //属性分类
    var attrType=arrayOf(AttrType.REFERENCE)
    //最低版本
    var sdk=0
    //是否要以被转换,有些属性是完全内部封装,无法转换
    var convert=true
    //取值转换
    internal var kotlinCallback:((String)->String)?=null
    internal var javaCallback:((String)->String)?=null
    /**
     * 转换配置取值
     */
    fun kotlinMethod(callback:(String)->String){
        this.kotlinCallback=callback
    }
    /**
     * 转换配置取值
     */
    fun javaMethod(callback:(String)->String){
        this.javaCallback=callback
    }
}