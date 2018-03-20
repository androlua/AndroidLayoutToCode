package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

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

    override fun getJavaExpression(context: BaseContext)="${value}f"

    override fun getKotlinExpression(context: BaseContext)="$value.toFloat()"



}