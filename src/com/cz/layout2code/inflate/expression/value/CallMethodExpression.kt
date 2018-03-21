package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 调用方法表达式
 */
class CallMethodExpression(val value: String) : ElementExpression() {
    private val methodName:String
    private val methodParamItems = mutableListOf<ElementExpression>()
    init {
        val matcher="(?<methodName>\\w+)\\((?<params>.*)\\)".toPattern().matcher(value)
        if(!matcher.find()){
            throw IllegalArgumentException("Can't generate $value to method expression!")
        }
        //调用方法名称
        methodName=matcher.group("methodName")
        //方法参数表达式
        val params=matcher.group("params")
        params?.split("\\s*,\\s*".toRegex())?.forEach {
            if(it.first().isUpperCase()){
                //类成员信息
                methodParamItems.add(ClassFieldExpression(it))
            } else {
                //成员字段
                methodParamItems.add(FieldExpression(it))
            }
        }
    }
    /**
     * 获取类引入信息
     */
    override fun getImportList(): MutableList<ImportItem> {
        val items= mutableListOf<ImportItem>()
        methodParamItems.forEach { items+=it.getImportList() }
        return items
    }

    override fun getJavaExpression(context: BaseContext):String{
        val methodName=context.getJavaMethod(methodName)
        return "$methodName(${methodParamItems.joinToString(", "){ it.getJavaExpression(context)} })"
    }

    override fun getKotlinExpression(context: BaseContext):String{
        val methodName=context.getJavaMethod(methodName)
        return "$methodName(${methodParamItems.joinToString(", "){ it.getJavaExpression(context)} })"
    }



}