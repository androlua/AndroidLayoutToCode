package com.cz.layout2code.inflate.expression

import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.VERSIONS
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.inflate.item.ImportItem

abstract class AttributeExpression: ElementExpression(){
    //元素导包集
    val importItems= mutableListOf<ImportItem>()
    /**
     * 设置属性表达式调用等级
     */
    var sdk=0
    /**
     * 回调表达式取值
     */
    abstract fun callback(value:String):AttributeExpression

    abstract fun getJavaAttributeExpression(context:BaseContext):String

    abstract fun getKotlinAttributeExpression(context:BaseContext):String

    override fun getJavaExpression(context: BaseContext): String {
        val javaString = getJavaAttributeExpression(context)
        return if(0==sdk){
            return javaString
        } else {
            //附加版本class导入
            importItems.add(ImportItem("android.os.Build"))
            "if(Build.VERSION_CODES.${VERSIONS[sdk]}<Build.VERSION.SDK_INT){\n"+
                    "\t$javaString\n"+
                    "}"
        }
    }

    override fun getKotlinExpression(context: BaseContext): String {
        val attributeExpression = getKotlinAttributeExpression(context)
        return if(0==sdk){
            "$attributeExpression"
        } else {
            //附加版本class导入
            importItems.add(ImportItem("org.jetbrains.anko.doFromSdk"))
            "doFromSdk(Build.VERSION_CODES.${VERSIONS[sdk]}){\n"+
                    "\t$attributeExpression\n"+
                    "}"
        }
    }
}