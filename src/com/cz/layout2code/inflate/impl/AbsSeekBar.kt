package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/2/1.
 */
open class AbsSeekBar : ProgressBar() {
    init {
        uselessAttribute("useDisabledAlpha")
        attribute{
            field = "thumbTint"
            attrType = arrayOf(AttrType.COLOR)
            sdk=21
            kotlinMethod { "thumbTintList = ${colorStateList(it)}" }
            javaMethod{ "setThumbTintList(${colorStateList(it)})" }
        }
        attribute{
            field = "thumbTintMode"
            attrType = arrayOf(AttrType.FLAG)
            sdk=21
            kotlinMethod { "thumbTintMode = ${tintMode(it)}" }
            javaMethod{ "setThumbTintMode(${tintMode(it)})" }
        }
        attribute{
            field = "tickMark"
            attrType = arrayOf(AttrType.REFERENCE)
            sdk=24
            kotlinMethod { "tickMark = ${resource(it)}" }
            javaMethod{ "setTickMark(${resource(it)})" }
        }
        attribute{
            field = "tickMarkTint"
            attrType = arrayOf(AttrType.COLOR)
            kotlinMethod { "tickMarkTintList = ${colorStateList(it)}" }
            javaMethod{ "setTickMarkTintList(${colorStateList(it)})" }
        }
        attribute{
            field = "tickMarkTintMode"
            attrType = arrayOf(AttrType.FLAG)
            kotlinMethod { "tickMarkTintMode = ${tintMode(it)}" }
            javaMethod{ "setTickMarkTintMode(${tintMode(it)})" }
        }
    }
    override fun getViewName()="absSeekBar"
    override fun getThemeViewName()="themedAbsSeekBar"
}