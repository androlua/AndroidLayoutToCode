package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.impl.custom.CustomViewWrapper
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 定义class表达式
 */
class DefineClassExpression(private val view:View,
                            private val referenceName:String,
                            private val fieldName:String?=null): ElementExpression() {

    override fun getImportList(): MutableList<ImportItem> {
        return mutableListOf(ImportItem(referenceName))
    }

    override fun getJavaExpression(baseMatcher: BaseContext): String {
        val className=referenceName.substringAfterLast(".")
        return "$className $fieldName=new $className();"
    }

    override fun getKotlinExpression(baseMatcher: BaseContext): String {
        if(view is CustomViewWrapper){
            //自定义控件
            return "${view.getViewName()}{"
        } else if(view.isCompatView){
            //v7
            return "${view.getThemeViewName()}{"
        } else {
            //系统控件
            return "${view.getViewName()}{"
        }
    }

}