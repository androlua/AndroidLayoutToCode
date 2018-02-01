package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
/**
 * Created by cz on 2018/1/29.
 */
open class QuickContactBadge : ImageView() {
	init {
		uselessAttribute("LinearLayout")
	}

	override fun getViewName()="quickContactBadge"

	override fun getThemeViewName()="themedQuickContactBadge"

}
