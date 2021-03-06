package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
import com.cz.layout2code.inflate.float
import com.cz.layout2code.inflate.item.ViewNode

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------ToggleButton all expressions---------------
 * @attr ref android.R.styleable#ToggleButton_textOn
 * @attr ref android.R.styleable#ToggleButton_textOff
 * @attr ref android.R.styleable#ToggleButton_disabledAlpha
 *
 */
open class ToggleButton : CompoundButton() {
	init {
		attribute{
			field = "textOn"
			attrType = arrayOf(AttrType.STRING)
			property("textOn"){ string(it) }
		}
		attribute{
			field = "textOff"
			attrType = arrayOf(AttrType.STRING)
			property("textOff"){ string(it) }
		}
		uselessAttribute("disabledAlpha")
	}

	override fun getViewName()="toggleButton"

	override fun getThemeViewName()="themedToggleButton"

}
