package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.context.BaseContext

/**
 * 类调用方法表达式
 */
class ClassCallMethodExpression(val value: String) : ElementExpression() {
    //调用成员
    private val classField: ClassFieldExpression
    private val methodName:String
    private val methodParamItems = mutableListOf<ElementExpression>()
    init {
        val matcher="(?<fieldName>[\\w\\.]+)+\\.(?<methodName>\\w+)\\((?<params>.*)\\)".toPattern().matcher(value)
        if(!matcher.find()){
            throw IllegalArgumentException("Can't generate $value to method expression!")
        }
        //类表达式
        classField= ClassFieldExpression(matcher.group("fieldName"))
        //调用方法名称
        methodName=matcher.group("methodName")
        //方法参数表达式
        val params=matcher.group("params")
        if(null!=params){
            val pattern="^\\p{Upper}([\\w\\.]+)".toRegex()
            params.split("\\s*,\\s*").forEach {
                if(pattern.matches(it)){
                    //类成员信息
                    methodParamItems.add(ClassFieldExpression(it))
                } else {
                    //成员字段
                    methodParamItems.add(FieldExpression(it))
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

    override fun getJavaExpression(baseContext: BaseContext)=value

    override fun getKotlinExpression(baseContext: BaseContext)=value



}