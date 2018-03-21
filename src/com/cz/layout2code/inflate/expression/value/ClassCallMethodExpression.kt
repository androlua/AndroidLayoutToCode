package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 类调用方法表达式
 */
class ClassCallMethodExpression(val value: String) : ElementExpression() {
    //调用成员
    private val classField: ClassFieldExpression
    private val callMethodExpression:CallMethodExpression
    init {
        val className=value.substringBefore(".")
        //类表达式
        classField= ClassFieldExpression(className)
        val callMethodValue=value.substringAfter(".")
        callMethodExpression=CallMethodExpression(callMethodValue)
    }
    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        val items=classField.getImportList()
        items+=callMethodExpression.getImportList()
        return items
    }

    override fun getJavaExpression(context: BaseContext):String{
        val classField = classField.getJavaExpression(context)
        return "$classField.${callMethodExpression.getJavaExpression(context)}"
    }

    override fun getKotlinExpression(context: BaseContext):String{
        val classField = classField.getJavaExpression(context)
        return "$classField.${callMethodExpression.getKotlinExpression(context)}"
    }



}