package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 属性表达式
 * kotlin java 均为方法调用,且方法参数有多个
 */
class AttributeMethodMultiParamsExpression(private val methodName: String,private val callback:(String)->MutableList<ElementExpression>) : AttributeExpression() {
    private lateinit var expressions:MutableList<ElementExpression>

    override fun callback(value:String):AttributeExpression{
        val item=AttributeMethodMultiParamsExpression(methodName,callback)
        item.expressions=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        val importItems= mutableListOf<ImportItem>()
        expressions.forEach {
            importItems+=it.getImportList()
        }
        return importItems
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher): String {
        val params=expressions.joinToString(", "){ it.getJavaExpression(classMatcher) }
        return "$methodName($params);"
    }

    override fun getKotlinExpression(classMatcher: BaseClassMatcher): String {
        val params=expressions.joinToString(", "){ it.getKotlinExpression(classMatcher) }
        return "$methodName($params)"
    }

}