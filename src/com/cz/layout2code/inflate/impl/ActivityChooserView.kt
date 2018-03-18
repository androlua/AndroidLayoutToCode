package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resource
import com.cz.layout2code.inflate.string
/**
 * Created by cz on 2018/1/29.
 */
open class ActivityChooserView : ViewGroup() {
	init {
		attribute{
			field = "initialActivityCount"
			attrType = arrayOf(AttrType.STRING)
			method("setInitialActivityCount"){ string(it) }
		}
		attribute{
			field = "expandActivityOverflowButtonDrawable"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setExpandActivityOverflowButtonDrawable"){ resource(it) }
		}
	}

	override fun getViewName()="activityChooserView"

	override fun getThemeViewName()="themedActivityChooserView"

}
