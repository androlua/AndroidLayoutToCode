package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.VERSIONS

/**
 * 属性表达式
 * kotlin java 均为方法调用,且方法参数有多个
 */
class AttributeMethodMultiParamsExpression(private val methodName: String,private val callback:(String)->MutableList<ElementExpression>) : AttributeExpression() {
    private lateinit var expressions:MutableList<ElementExpression>
    private val importList= mutableListOf<ImportItem>()

    override fun callback(value:String):AttributeExpression{
        val item=AttributeMethodMultiParamsExpression(methodName,callback)
        item.expressions=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        expressions.forEach {
            importList+=it.getImportList()
        }
        return importList
    }

    override fun getJavaExpression(context: BaseContext): String {
        val params=expressions.joinToString(", "){ it.getJavaExpression(context) }
        return if(0==sdk){
            "$methodName($params)"
        } else {
            //附加版本class导入
            importList.add(ImportItem("android.os.Build"))
            "if(Build.VERSION_CODES.${VERSIONS[sdk]}<Build.VERSION.SDK_INT){\n"+
                    "\t$methodName($params)\n"+
                    "}"
        }
    }

    override fun getKotlinExpression(context: BaseContext): String {
        val params=expressions.joinToString(", "){ it.getKotlinExpression(context) }
        return if(0==sdk){
            "$methodName($params)"
        } else {
            //附加版本class导入
            importList.add(ImportItem("org.jetbrains.anko.doFromSdk"))
            "doFromSdk(Build.VERSION_CODES.${VERSIONS[sdk]}){\n"+
                    "\t$methodName($params)\n"+
                    "}"
        }
    }

}