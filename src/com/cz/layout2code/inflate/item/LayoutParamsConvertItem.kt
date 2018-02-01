package com.cz.layout2code.inflate.item

/**
 * Created by cz on 2017/12/21.
 */
class LayoutParamsConvertItem(val width:String,val height:String): ElementConvert {

    override fun toKotlinString():String{
        return "width=$width, height=$height"
    }
    override fun toJavaString():String{
        val out=StringBuilder()
        out.append("ViewGroup.LayoutParams($width,$height)\n")
        return out.toString()
    }
}