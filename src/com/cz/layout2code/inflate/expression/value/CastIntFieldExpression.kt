package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 转换Int字段表达式
 */
class CastIntFieldExpression(val value: ElementExpression) : ElementExpression() {
    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher)="(int)${value.getJavaExpression(classMatcher)}"

    override fun getKotlinExpression(classMatcher: BaseClassMatcher)="${value.getKotlinExpression(classMatcher)}.toInt()"



}