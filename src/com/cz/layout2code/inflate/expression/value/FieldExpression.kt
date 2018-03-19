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

    override fun getJavaExpression(baseContext: BaseContext):String{
        return baseContext.getJavaField(field)
    }

    override fun getKotlinExpression(baseContext: BaseContext):String{
        return baseContext.getKotlinField(field)
    }

}