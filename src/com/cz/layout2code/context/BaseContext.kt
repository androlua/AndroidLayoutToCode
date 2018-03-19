package com.cz.layout2code.context

import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.inflate.item.ClassItem
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

/**
 * 基础类上下文
 * 如activity/fragment/dialog/view/等不同场下,元素的不同体现
 */
abstract class BaseContext(val project:Project) {
    //前置表达式
    private val preExpressions= mutableListOf<ElementExpression>()

    fun getPreExpressions()=preExpressions

    /**
     * 添加前置表达式
     */
    fun addPreExpression(expression: ElementExpression){
        preExpressions.add(expression)
    }
    /**
     * 获得java映射字段
     */
    abstract fun getJavaField(field:String):String

    /**
     * 获得kotlin的映射字段
     */
    abstract fun getKotlinField(field:String):String
}