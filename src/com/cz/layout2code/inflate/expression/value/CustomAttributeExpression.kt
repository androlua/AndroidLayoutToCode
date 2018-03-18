package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 自定义属性表达式
 * kotlin property表达式
 * java 调为方法调用
 */
class CustomAttributeExpression(private val name:String, private val value:String) : ElementExpression() {

    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher): String {
        return "//Custom attribute $name=$value"
    }

    override fun getKotlinExpression(classMatcher: BaseClassMatcher): String {
        return "//Custom attribute $name=$value"
    }

}