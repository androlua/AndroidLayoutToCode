package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.ClassReferences

/**
 * 声明LayoutParams表达式
 */
class DefineLayoutParamsExpression(private val widthDimenExpression:ElementExpression,
                                   private val heightDimenExpression: ElementExpression): ElementExpression() {

    override fun getImportList(): MutableList<ImportItem> {
        var importList = mutableListOf<ImportItem>()
        importList.addAll(widthDimenExpression.getImportList())
        importList.addAll(heightDimenExpression.getImportList())
        return importList
    }

    override fun getJavaExpression(context: BaseContext): String {
        return "new ViewGroup.LayoutParams(" +
                "${widthDimenExpression.getJavaExpression(context)},"+
                "${heightDimenExpression.getJavaExpression(context)})"
    }

    override fun getKotlinExpression(context: BaseContext): String {
        return "lparams()"
    }

}