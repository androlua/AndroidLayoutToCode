package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * float字段表达式
 */
class FloatFieldExpression(val value: String) : ElementExpression() {
    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher)="${value}f"

    override fun getKotlinExpression(classMatcher: BaseClassMatcher)="$value.toFloat()"



}