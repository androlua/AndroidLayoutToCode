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
			property("checked") {bool(it)}
		}
		attribute{
			field = "button"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setButtonDrawable"){ resourceRef(it) }
		}
		attribute{
			field = "buttonTint"
			attrType = arrayOf(AttrType.COLOR)
			method("setButtonTintList"){ colorStateList(it) }
		}
		attribute{
			field = "buttonTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			method("setButtonTintMode"){ tintMode(it) }
		}
	}

	override fun getViewName()="compoundButton"

	override fun getThemeViewName()="themedCompoundButton"

}
