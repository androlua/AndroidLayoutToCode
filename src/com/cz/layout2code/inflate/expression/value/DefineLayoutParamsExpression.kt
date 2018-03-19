package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 声明LayoutParams表达式
 */
class DefineLayoutParamsExpression(val widthDimenExpression:ElementExpression,
                                   val heightDimenExpression: ElementExpression): ElementExpression() {
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(baseMatcher: BaseContext): String {
        return "new ViewGroup.LayoutParams(" +
                "${widthDimenExpression.getJavaExpression(baseMatcher)},"+
                "${heightDimenExpression.getJavaExpression(baseMatcher)})"
    }

    override fun getKotlinExpression(baseMatcher: BaseContext): String {
        return "lparams()"
    }

}