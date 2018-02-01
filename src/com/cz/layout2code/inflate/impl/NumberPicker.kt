package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.resource
/**
 * Created by cz on 2018/1/29.
 */
open class NumberPicker : LinearLayout() {
	init {
		uselessAttribute("solidColor")
		uselessAttribute("selectionDivider")
		uselessAttribute("selectionDividerHeight")
		uselessAttribute("selectionDividersDistance")
		uselessAttribute("internalMinHeight")
		uselessAttribute("internalMaxHeight")
		uselessAttribute("internalMinWidth")
		uselessAttribute("internalMaxWidth")
		uselessAttribute("internalLayout")
		uselessAttribute("virtualButtonPressedDrawable")
		uselessAttribute("hideWheelUntilFocused")
	}

	override fun getViewName()="numberPicker"

	override fun getThemeViewName()="themedNumberPicker"

}
