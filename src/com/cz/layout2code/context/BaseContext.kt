package com.cz.layout2code.context

import com.cz.layout2code.inflate.expression.value.DefineClassExpression
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.inflate.expression.value.MethodBlockExpression
import com.cz.layout2code.inflate.expression.value.StringValueExpression
import com.intellij.openapi.project.Project

/**
 * 基础类上下文
 * 如activity/fragment/dialog/view/等不同场下,元素的不同体现
 * 负责添加当前上下文环境下,方法前置表达式,以及附加方法等
 *
 * 此类在第一版功能偏文本输出太多,职责划分不太明确,后期会改进
 */
abstract class BaseContext(val project:Project) {
    //前置表达式
    private val expressions= mutableMapOf<String,ElementExpression>()
    /**
     * 前置方法
     */
    private val methodExpressions= mutableMapOf<String,MethodBlockExpression>()

    fun getPreExpressions()=expressions

    fun getPreMethodExpressions()=methodExpressions

    /**
     * 添加前置表达式
     */
    protected fun addPreExpression(field:String,expression: ElementExpression){
        expressions[field]=expression
    }

    /**
     * 添加前置方法表达式
     */
    private fun addPreMethodExpression(method:String,expression: MethodBlockExpression){
        methodExpressions[method]=expression
    }

    fun methodExpression(method:String,expression: MethodBlockExpression.()->Unit){
        val expression= MethodBlockExpression(method).apply(expression)
        addPreMethodExpression(method,expression)
    }

    fun defineClass(className:String,fieldName:String,callback:(()->ElementExpression)?=null){
        val expression = DefineClassExpression(className, fieldName,callback)
        addPreExpression(fieldName,expression)
    }

    /**
     * 获得java映射字段
     */
    abstract fun getJavaField(field:String):String

    /**
     * 获得java映射方法
     */
    abstract fun getJavaMethod(method:String):String

    /**
     * 获得kotlin的映射字段
     */
    abstract fun getKotlinField(field:String):String

    /**
     * 获得java映射方法
     */
    abstract fun getKotlinMethod(method:String):String
}