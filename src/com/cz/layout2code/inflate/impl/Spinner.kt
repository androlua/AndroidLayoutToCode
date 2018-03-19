package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resource

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------Spinner all expressions---------------
 * @attr ref android.R.styleable#Spinner_dropDownSelector
 * @attr ref android.R.styleable#Spinner_dropDownWidth
 * @attr ref android.R.styleable#Spinner_gravity
 * @attr ref android.R.styleable#Spinner_popupBackground
 * @attr ref android.R.styleable#Spinner_prompt
 * @attr ref android.R.styleable#Spinner_spinnerMode
 * @attr ref android.R.styleable#ListPopupWindow_dropDownVerticalOffset
 * @attr ref android.R.styleable#ListPopupWindow_dropDownHorizontalOffset
 *
 */
open class Spinner : AbsSpinner() {
	init {
		uselessAttribute("dropDownSelector")

		attribute{
			field = "dropDownWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			property("dropDownWidth"){ dimen(it) }
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			property("gravity"){ gravity(it) }
		}
		attribute{
			field = "popupBackground"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			method("setPopupBackgroundDrawable"){ resource(it) }
		}
		attribute{
			field = "popupBackground"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			method("setPopupBackgroundDrawable"){ resource(it) }
		}
		uselessAttribute("prompt")
		uselessAttribute("spinnerMode")
		attribute{
			field = "dropDownVerticalOffset"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			property("dropDownVerticalOffset"){ dimen(it) }
		}
		attribute{
			field = "dropDownHorizontalOffset"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			property("dropDownHorizontalOffset"){ dimen(it) }
		}
	}
}
