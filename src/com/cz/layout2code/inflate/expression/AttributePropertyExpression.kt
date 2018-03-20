package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.VERSIONS

/**
 * 属性表达式
 * kotlin property表达式
 * java 调为java bean方法调用
 */
class AttributePropertyExpression(private val java: String,private val kotlin:String,private val callback:(String)->ElementExpression) : AttributeExpression() {
    lateinit var expression: ElementExpression
    private val importList= mutableListOf<ImportItem>()

    constructor(property:String,callback:(String)->ElementExpression):this("set"+property[0].toUpperCase()+property.substring(1),property,callback)

    override fun callback(value:String):AttributeExpression{
        val item=AttributePropertyExpression(java,kotlin,callback)
        item.expression=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        return expression.getImportList()
    }

    override fun getJavaExpression(context: BaseContext): String {
        return if(0==sdk){
            "$java(${expression.getKotlinExpression(context)})"
        } else {
            //附加版本class导入
            importList.add(ImportItem("android.os.Build"))
            "if(Build.VERSION_CODES.${VERSIONS[sdk]}<Build.VERSION.SDK_INT){\n"+
                    "\t$java(${expression.getKotlinExpression(context)})\n"+
                    "}"
        }
    }

    override fun getKotlinExpression(context: BaseContext): String {
        return if(0==sdk){
            "$kotlin = ${expression.getKotlinExpression(context)}"
        } else {
            //附加版本class导入
            importList.add(ImportItem("org.jetbrains.anko.doFromSdk"))
            "doFromSdk(Build.VERSION_CODES.${VERSIONS[sdk]}){\n"+
                    "\t$kotlin = ${expression.getKotlinExpression(context)}\n"+
                    "}"
        }
    }

}