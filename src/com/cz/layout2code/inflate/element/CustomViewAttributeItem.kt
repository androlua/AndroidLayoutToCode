package com.cz.layout2code.inflate.element


/**
 * Created by cz on 2017/12/21.
 * 自定义应用属性,暂不做全局的自定义控件汇总,以及属性统计
 */
class CustomViewAttributeItem(val name:String, val value:String): ElementConvert {
    override fun toKotlinString():String?{
        return "//Custom attribute app:\"$name\"=\"$value\""
    }
    override fun toJavaString():String?{
        return "//Custom attribute app:\"$name\"=\"$value\""
    }
}