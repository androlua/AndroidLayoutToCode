package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 */
open class CompoundButton : Button() {
	init {
		attribute{
			field = "checked"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "checked = ${bool(it)}" }
			javaMethod{ "setChecked(${bool(it)})" }
		}
		attribute{
			field = "button"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setButtonDrawable(${resourceRef(it)})" }
			javaMethod{ "setButtonDrawable(${resourceRef(it)})" }
		}
		attribute{
			field = "buttonTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setButtonTintList(${colorStateList(it)})" }
			javaMethod{ "setButtonTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "buttonTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "setButtonTintMode(${tintMode(it)})" }
			javaMethod{ "setButtonTintMode(${tintMode(it)})" }
		}
	}

	override fun getViewName()="compoundButton"

	override fun getThemeViewName()="themedCompoundButton"

}
