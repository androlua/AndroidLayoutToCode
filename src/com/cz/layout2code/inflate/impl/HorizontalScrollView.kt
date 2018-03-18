package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.item.ViewNode

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------HorizontalScrollView all expressions---------------
 * @attr ref android.R.styleable#HorizontalScrollView_fillViewport
 *
 */
open class HorizontalScrollView : FrameLayout() {
	init {
		attribute{
			field = "fillViewport"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setFillViewport"
				kotlin="isFillViewport"
				value { bool(it) }
			}
		}
	}

	override fun getViewName()="horizontalScrollView"

	override fun getThemeViewName()="themedHorizontalScrollView"

}
