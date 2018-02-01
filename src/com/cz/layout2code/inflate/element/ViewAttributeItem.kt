package com.cz.layout2code.inflate.element

import com.cz.layout2code.inflate.prefs.AttributeStyle

/**
 * Created by cz on 2017/12/21.
 */
class ViewAttributeItem(val item: AttributeStyle, val value:String): ElementConvert {
    override fun toKotlinString():String?{
        return item.kotlinCallback?.invoke(value)
    }
    override fun toJavaString():String?{
        return item.javaCallback?.invoke(value)
    }
}