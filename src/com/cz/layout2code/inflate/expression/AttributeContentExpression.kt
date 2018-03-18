package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 自定义内容表达式对象
 */
class AttributeContentExpression: AttributeExpression() {

    private var importCallback:(()->MutableList<ImportItem>)?=null
    private lateinit var javaCallback:(BaseClassMatcher,String)->String
    private lateinit var kotlinCallback:(BaseClassMatcher,String)->String
    private lateinit var methodName:String

    override fun callback(value: String):AttributeExpression {
        val item=AttributeContentExpression()
        item.importCallback=importCallback
        item.javaCallback=javaCallback
        item.kotlinCallback=kotlinCallback
        //记录自这义方法名
        item.methodName=value
        return item
    }

    fun import(callback:()->MutableList<ImportItem>){
        this.importCallback=callback
    }

    fun javaExpression(callback:(BaseClassMatcher,String)->String){
        this.javaCallback=callback
    }

    fun kotlinExpression(callback:(BaseClassMatcher,String)->String){
        this.javaCallback=callback
    }

    override fun getImportList(): MutableList<ImportItem> {
        return importCallback?.invoke()?: mutableListOf()
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher): String {
        return javaCallback(classMatcher,methodName)
    }

    override fun getKotlinExpression(classMatcher: BaseClassMatcher): String {
        return kotlinCallback(classMatcher,methodName)
    }

}