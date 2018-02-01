package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
/**
 * Created by cz on 2018/1/29.
 */
open class TableLayout : LinearLayout() {
	init {
		//这里3个属性可逆向设置,但太过麻烦,需要反射
		uselessAttribute("stretchColumns")
		uselessAttribute("shrinkColumns")
		uselessAttribute("collapseColumns")
	}

	override fun getViewName()="tableLayout"

	override fun getThemeViewName()="themedTableLayout"

	override fun getLayoutParams()=LayoutParams()

	open class LayoutParams : LinearLayout.LayoutParams()		
	
}
