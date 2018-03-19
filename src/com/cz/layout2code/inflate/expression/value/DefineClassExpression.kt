package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.ClassReferences

/**
 * 定义class表达式
 */
class DefineClassExpression(private val className:String,
                            private val fieldName:String?=null): ElementExpression() {

    override fun getImportList(): MutableList<ImportItem> {
        val items = mutableListOf<ImportItem>()
        val classItem = ClassReferences.getClassItem(className)
        val importItem = classItem?.importItem
        if(null!=importItem){
            items+=importItem
        }
        return items
    }

    override fun getJavaExpression(baseContext: BaseContext): String {
        return "$className $fieldName = new $className();"
    }

    override fun getKotlinExpression(baseContext: BaseContext): String {
        return "val $fieldName = $className()"
    }

}