package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.context.BaseContext

/**
 * 属性表达式
 * kotlin property表达式
 * java 调为java bean方法调用
 */
class AttributePropertyExpression(private val java: String,private val kotlin:String,private val callback:(String)->ElementExpression) : AttributeExpression() {
    lateinit var expression: ElementExpression

    constructor(property:String,callback:(String)->ElementExpression):this("set"+property[0].toUpperCase()+property.substring(1),property,callback)

    override fun callback(value:String):AttributeExpression{
        val item=AttributePropertyExpression(java,kotlin,callback)
        item.expression=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        return expression.getImportList()
    }

    override fun getJavaExpression(baseMatcher: BaseContext): String {
        return "$java(${expression.getKotlinExpression(baseMatcher)})"
    }

    override fun getKotlinExpression(baseMatcher: BaseContext): String {
        return "$kotlin = ${expression.getKotlinExpression(baseMatcher)}"
    }

}