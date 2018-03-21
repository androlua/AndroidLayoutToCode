package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 类字段表达式
 */
class FieldCallMethodExpression(val value: String) : ElementExpression() {
    //调用成员
    private val classField:FieldExpression
    private val callMethodExpression:CallMethodExpression
    init {
        val fieldName=value.substringBefore(".")
        //类表达式
        classField= FieldExpression(fieldName)
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
        //调用方法对象
        val classField = classField.getJavaExpression(context)
        //重新组织方法调用语句
        return "$classField.${callMethodExpression.getJavaExpression(context)}"
    }

    override fun getKotlinExpression(context: BaseContext):String{
        val classField = classField.getJavaExpression(context)
        return "$classField.${callMethodExpression.getKotlinExpression(context)}"
    }



}