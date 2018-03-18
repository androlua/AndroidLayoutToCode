package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.matcher.BaseClassMatcher

/**
 * 元素引用表达式
 */
abstract class ElementExpression {
    companion object {
        fun create(value: String): ElementExpression {
            //类字段引用类型
            val pattern1="^\\p{Upper}([\\w\\.]+)".toRegex()
            //类调用方法类型
            val pattern2="^\\p{Upper}([\\w\\.]+)+\\.(\\w+)\\((.+)\\)".toRegex()
            //成员调用方法类型
            val pattern3="^\\p{Lower}([\\w\\.]+)+\\.(\\w+)\\((.+)\\)".toRegex()
            //类注释方法
            val pattern4="//.+".toRegex()
            //检测当前属于哪一种表达式类型
            return when {
                pattern1.matches(value) -> ClassFieldExpression(value)
                pattern2.matches(value) -> ClassCallMethodExpression(value)
                pattern3.matches(value) -> FieldCallMethodExpression(value)
                pattern4.matches(value) -> CommentExpression(value)
                else -> StringValueExpression(value)
            }
        }
    }
    /**
     * 获得元素涉及导入信息
     */
    abstract fun getImportList():MutableList<ImportItem>

    /**
     * 获得java取值的字符串形式
     */
    abstract fun getJavaExpression(classMatcher: BaseClassMatcher):String

    /**
     * 获得kotlin取值的字符串形式
     */
    abstract fun getKotlinExpression(classMatcher: BaseClassMatcher):String
}