package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.VERSIONS

/**
 * 属性表达式
 * kotlin 与java均为方法调用
 */
class AttributeMethodExpression(private val methodName: String,private val callback:((String)->ElementExpression)) : AttributeExpression() {
    lateinit var expression: ElementExpression
    private val importList= mutableListOf<ImportItem>()

    override fun callback(value:String):AttributeExpression{
        val item=AttributeMethodExpression(methodName,callback)
        item.expression=callback.invoke(value)
        return item
    }

    override fun getImportList(): MutableList<ImportItem> {
        importList+=expression.getImportList()
        return importList
    }

    override fun getJavaExpression(context: BaseContext): String {
        return if(0==sdk){
            "$methodName(${expression.getJavaExpression(context)});"
        } else {
            //附加版本class导入
            importList.add(ImportItem("android.os.Build"))
            "if(Build.VERSION_CODES.${VERSIONS[sdk]}<Build.VERSION.SDK_INT){\n"+
                    "\t$methodName(${expression.getJavaExpression(context)});\n"
                    "}"
        }
    }

    override fun getKotlinExpression(context: BaseContext): String {
        return if(0==sdk){
            "$methodName(${expression.getKotlinExpression(context)})"
        } else {
            //附加版本class导入
            importList.add(ImportItem("org.jetbrains.anko.doFromSdk"))
            "doFromSdk(Build.VERSION_CODES.${VERSIONS[sdk]}){\n"+
                    "\t$methodName(${expression.getKotlinExpression(context)})\n"+
                    "}"
        }
    }

}