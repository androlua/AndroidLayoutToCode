package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.VERSIONS

/**
 * 属性表达式
 * kotlin property表达式
 * java 也为赋值表达式
 */
class AttributeAllPropertyExpression(private val property:String,private val callback:(String)->ElementExpression) : AttributeExpression() {
    lateinit var expression: ElementExpression
    override fun callback(value:String):AttributeExpression{
        val item=AttributeAllPropertyExpression(property,callback)
        item.expression=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        importItems+=expression.getImportList()
        return importItems
    }

    override fun getJavaAttributeExpression(context:BaseContext): String {
        return "$property = ${expression.getJavaExpression(context)}"
    }

    override fun getKotlinAttributeExpression(context:BaseContext): String {
        return "$property = ${expression.getKotlinExpression(context)}"
    }

}