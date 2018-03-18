package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ScrollView all expressions---------------
 * @attr ref android.R.styleable#ScrollView_fillViewport
 *
 */
open class ScrollView : FrameLayout() {
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
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="scrollView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedScrollView"

}
