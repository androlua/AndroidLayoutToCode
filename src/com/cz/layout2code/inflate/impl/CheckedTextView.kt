package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------CheckedTextView all expressions---------------
 * @attr ref android.R.styleable#CheckedTextView_checked
 * @attr ref android.R.styleable#CheckedTextView_checkMark
 *
 */
open class CheckedTextView : TextView() {
	init {
		attribute{
			field = "checked"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setChecked"
				kotlin="isChecked"
				value { bool(it) }
			}
		}
		attribute{
			field = "checkMark"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setCheckMarkDrawable"){ resource(it) }
		}
		attribute{
			field = "checkMarkTint"
			attrType = arrayOf(AttrType.COLOR)
			method("setCheckMarkTintList"){ colorStateList(it) }
		}
		attribute{
			field = "tintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property {
				java="setCheckMarkTintMode"
				kotlin="tintMode"
				value { tintMode(it) }
			}
		}
		uselessAttribute("checkMarkGravity")
	}

	override fun getViewName()="checkedTextView"

	override fun getThemeViewName()="themedCheckedTextView"
}
