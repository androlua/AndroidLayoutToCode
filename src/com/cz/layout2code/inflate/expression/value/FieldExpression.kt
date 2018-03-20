package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 类字段表达式
 */
class FieldExpression(private val field: String) : ElementExpression() {
    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(context: BaseContext):String{
        return context.getJavaField(field)
    }

    override fun getKotlinExpression(context: BaseContext):String{
        return context.getKotlinField(field)
    }

}