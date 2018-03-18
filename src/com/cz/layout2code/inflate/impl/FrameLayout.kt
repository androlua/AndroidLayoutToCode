package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.resource

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------FrameLayout all expressions---------------
 * @attr ref android.R.styleable#FrameLayout_measureAllChildren
 *
 */
open class FrameLayout : ViewGroup() {
	init {
		attribute{
			field = "measureAllChildren"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("measureAllChildren") { bool(it) }
		}
	}

	override fun getViewName()="frameLayout"

	override fun getThemeViewName()="themedFrameLayout"

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all expressions---------------
	 * @attr ref android.R.styleable#FrameLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : MarginLayoutParams() {
		init {
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				allProperty("gravity"){ gravity(it) }
			}
		}
	}
	
}
