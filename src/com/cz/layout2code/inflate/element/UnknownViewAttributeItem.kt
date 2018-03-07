package com.cz.layout2code.inflate.element


/**
 * Created by cz on 2017/12/21.
 * 未应用属性,暂不做全局的自定义控件汇总,以及属性统计
 */
class UnknownViewAttributeItem(val name:String, val value:String): ElementConvert {
    override fun toKotlinString():String?{
        return "//Unknown attribute app:\"$name\"=\"$value\""
    }
    override fun toJavaString():String?{
        return "//Unknown attribute app:\"$name\"=\"$value\""
    }
}