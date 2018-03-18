package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.id
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.int
import com.cz.layout2code.inflate.linearLayoutOrientation

/**
 * Created by cz on 2018/1/29.
 */
open class RadioGroup : LinearLayout() {
	init {
		attribute{
			field = "checkedButton"
			attrType = arrayOf(AttrType.INTEGER)
			method("check"){id(it)}
		}
		attribute{
			field = "orientation"
			attrType = arrayOf(AttrType.FLAG)
			property("orientation"){linearLayoutOrientation(it)}
		}
	}

	override fun getViewName()="radioGroup"

	override fun getThemeViewName()="themedRadioGroup"

	override fun getLayoutParams()=LayoutParams()

	open class LayoutParams : LinearLayout.LayoutParams()		
	
}
