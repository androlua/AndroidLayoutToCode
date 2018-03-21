package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 声明LayoutParams表达式
 */
class DefineLayoutParamsExpression(className:String,
                                   private val widthDimenExpression:ElementExpression,
                                   private val heightDimenExpression: ElementExpression): ElementExpression() {
    private val classFieldExpression=ClassFieldExpression(className)
    override fun getImportList(): MutableList<ImportItem> {
        var importList = classFieldExpression.getImportList()
        importList.addAll(widthDimenExpression.getImportList())
        importList.addAll(heightDimenExpression.getImportList())
        return importList
    }

    override fun getJavaExpression(context: BaseContext): String {
        val className = classFieldExpression.getJavaExpression(context)
        return "new $className(" +
                "${widthDimenExpression.getJavaExpression(context)},"+
                "${heightDimenExpression.getJavaExpression(context)})"
    }

    override fun getKotlinExpression(context: BaseContext): String {
        return "lparams()"
    }

}