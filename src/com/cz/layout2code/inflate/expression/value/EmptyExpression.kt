package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 一个空的表达式对象
 */
class EmptyExpression:ElementExpression(){
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(baseMatcher: BaseContext)=String()

    override fun getKotlinExpression(baseMatcher: BaseContext)=String()
}
