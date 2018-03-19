package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.context.BaseContext

/**
 * 属性表达式
 * kotlin 与java均为方法调用
 */
class AttributeMethodExpression(private val methodName: String,private val callback:((String)->ElementExpression)) : AttributeExpression() {
    lateinit var expression: ElementExpression

    override fun callback(value:String):AttributeExpression{
        val item=AttributeMethodExpression(methodName,callback)
        item.expression=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        return expression.getImportList()
    }

    override fun getJavaExpression(baseMatcher: BaseContext): String {
        return "$methodName(${expression.getJavaExpression(baseMatcher)});"
    }

    override fun getKotlinExpression(baseMatcher: BaseContext): String {
        return "$methodName(${expression.getKotlinExpression(baseMatcher)})"
    }

}