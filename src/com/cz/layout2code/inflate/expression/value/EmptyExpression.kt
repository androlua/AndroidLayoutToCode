package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 一个空的表达式对象
 */
class EmptyExpression:ElementExpression(){
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher)=String()

    override fun getKotlinExpression(classMatcher: BaseClassMatcher)=String()
}
