package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------TwoLineListItem all expressions---------------
 * @attr ref android.R.styleable#TwoLineListItem_mode
 *
 */
open class TwoLineListItem : RelativeLayout() {
	init {
		uselessAttribute("mode")
	}

	override fun getViewName()="twoLineListItem"

	override fun getThemeViewName()="themedTwoLineListItem"
}
