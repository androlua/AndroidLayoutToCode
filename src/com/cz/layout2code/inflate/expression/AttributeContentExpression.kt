package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 自定义内容表达式对象
 */
class AttributeContentExpression: AttributeExpression() {

    private var importCallback:(()->MutableList<ImportItem>)?=null
    private lateinit var javaCallback:(BaseContext, String)->String
    private lateinit var kotlinCallback:(BaseContext, String)->String
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

    fun javaExpression(callback:(BaseContext, String)->String){
        this.javaCallback=callback
    }

    fun kotlinExpression(callback:(BaseContext, String)->String){
        this.javaCallback=callback
    }

    override fun getImportList(): MutableList<ImportItem> {
        return importCallback?.invoke()?: mutableListOf()
    }

    override fun getJavaExpression(baseContext: BaseContext): String {
        return javaCallback(baseContext,methodName)
    }

    override fun getKotlinExpression(baseContext: BaseContext): String {
        return kotlinCallback(baseContext,methodName)
    }

}