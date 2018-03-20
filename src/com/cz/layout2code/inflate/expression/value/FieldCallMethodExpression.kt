package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 类字段表达式
 */
class FieldCallMethodExpression(val value: String) : ElementExpression() {
    //调用成员
    private val classField:FieldExpression
    //当前调用方法名称
    private val methodName:String
    //当前方法参数
    private val methodParamItems = mutableListOf<ElementExpression>()
    init {
        val matcher = "(?<fieldName>[\\w\\.]+)+\\.(?<methodName>\\w+)\\((?<params>.*)\\)".toPattern().matcher(value)
        if (!matcher.find()) {
            throw IllegalArgumentException("Can't generate $value to method expression!")
        }
        classField = FieldExpression(matcher.group("fieldName"))

        methodName = matcher.group("methodName")
        val params = matcher.group("params")
        if (null != params) {
            if (null != params) {
                val pattern = "^\\p{Upper}([\\w\\.]+)".toRegex()
                params.split("\\s*,\\s*".toRegex()).forEach {
                    if (pattern.matches(it)) {
                        //类成员信息
                        methodParamItems.add(ClassFieldExpression(it))
                    } else {
                        //成员字段
                        methodParamItems.add(FieldExpression(it))
                    }
                }

            }
        }
    }

    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        val items=classField.getImportList()
        methodParamItems.forEach { items+=it.getImportList() }
        return items
    }

    override fun getJavaExpression(context: BaseContext):String{
        //调用方法对象
        val classField = classField.getJavaExpression(context)
        //重新组织方法调用语句
        return "$classField.$methodName(${methodParamItems.joinToString(", ") {it.getJavaExpression(context)}})"
    }

    override fun getKotlinExpression(context: BaseContext):String{
        val classField = classField.getJavaExpression(context)
        return "$classField.$methodName(${methodParamItems.joinToString(", ") {it.getJavaExpression(context)}})"
    }



}