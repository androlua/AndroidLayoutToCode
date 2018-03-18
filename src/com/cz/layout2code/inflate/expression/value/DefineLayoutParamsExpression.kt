package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 声明LayoutParams表达式
 */
class DefineLayoutParamsExpression(val widthDimenExpression:ElementExpression,
                                   val heightDimenExpression: ElementExpression): ElementExpression() {
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf()
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher): String {
        return "new ViewGroup.LayoutParams(" +
                "${widthDimenExpression.getJavaExpression(classMatcher)},"+
                "${heightDimenExpression.getJavaExpression(classMatcher)});"
    }

    override fun getKotlinExpression(classMatcher: BaseClassMatcher): String {
        return "lparams()"
    }

}