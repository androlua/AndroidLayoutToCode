package com.cz.layout2anko.inflate.item

import com.cz.layout2anko.inflate.VERSIONS

/**
 * Created by cz on 2017/12/21.
 */
class LayoutParamsConvertItem(val width:String,val height:String):AttributeConvert{

    override fun toAnkoString():String{
        return "width=$width, height=$height"
    }
    override fun toJavaString():String{
        val out=StringBuilder()
        out.append("ViewGroup.LayoutParams($width,$height)\n")
        return out.toString()
    }
}