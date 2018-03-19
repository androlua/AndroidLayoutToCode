package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

class FlagFieldExpression(paramsArray: List<String>) : ElementExpression() {
    private val expressionItems= mutableListOf<ElementExpression>()
    init {
        paramsArray.forEach { expressionItems.add(ClassFieldExpression(it)) }
    }

    override fun getImportList(): MutableList<ImportItem> {
        val items=mutableListOf<ImportItem>()
        expressionItems.forEach { items+=it.getImportList() }
        return items
    }

    override fun getJavaExpression(baseContext: BaseContext): String {
        return expressionItems.joinToString(" | ") { it.getJavaExpression(baseContext) }
    }

    override fun getKotlinExpression(baseContext: BaseContext): String {
        return expressionItems.joinToString(" or ") { it.getKotlinExpression(baseContext) }
    }

}