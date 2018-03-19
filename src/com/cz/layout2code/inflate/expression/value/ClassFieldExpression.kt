package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.ClassReferences
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 类字段表达式
 */
class ClassFieldExpression(val value: String) : ElementExpression() {
    //调用成员
    private val classField=value.substringBefore(".")
    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        val classItem=ClassReferences.getClassItem(classField)
        val items=mutableListOf<ImportItem>()
        if(null!=classItem){
            items.add(classItem.importItem)
        }
        return items
    }

    override fun getJavaExpression(baseMatcher: BaseContext)=value

    override fun getKotlinExpression(baseMatcher: BaseContext)=value



}