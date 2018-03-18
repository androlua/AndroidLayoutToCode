package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 定义class表达式
 */
class DefineClassExpression(private val view: View, private val fieldName:String?=null): ElementExpression() {
    private val className=view.getClassName()
    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf(ImportItem(view.getClassPath()))
    }

    override fun getJavaExpression(classMatcher: BaseClassMatcher): String {
        return "$className $fieldName=new $className();"
    }

    override fun getKotlinExpression(classMatcher: BaseClassMatcher): String {
        return "${view.getThemeViewName()}{"
    }

}