package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.ClassReferences

/**
 * 定义class表达式
 */
class DefineClassExpression(private val className:String,
                            private val fieldName:String,
                            callback:(()->ElementExpression)?=null): ElementExpression() {
    private val defineExpression=callback?.invoke()

    override fun getImportList(): MutableList<ImportItem> {
        val items = mutableListOf<ImportItem>()
        val classItem = ClassReferences.getClassItem(className)
        val importItem = classItem?.importItem
        if(null!=importItem){
            items+=importItem
        }
        return items
    }

    override fun getJavaExpression(context: BaseContext): String {
        val defineExpression=defineExpression
        return if(null!=defineExpression){
            "$className $fieldName = ${defineExpression.getJavaExpression(context)};"
        } else {
            "$className $fieldName = new $className();"
        }
    }

    override fun getKotlinExpression(context: BaseContext): String {
        val defineExpression=defineExpression
        return if(null!=defineExpression){
            "val $fieldName = ${defineExpression.getJavaExpression(context)}"
        } else {
            "val $fieldName = $className()"
        }
    }

}