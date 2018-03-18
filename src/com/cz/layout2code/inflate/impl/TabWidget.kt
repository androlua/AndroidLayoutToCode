package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resourceRef
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TabWidget all expressions---------------
 * @attr ref android.R.styleable#TabWidget_divider
 * @attr ref android.R.styleable#TabWidget_tabStripEnabled
 * @attr ref android.R.styleable#TabWidget_tabStripLeft
 * @attr ref android.R.styleable#TabWidget_tabStripRight
 *
 */
open class TabWidget : LinearLayout() {
	init {
		attribute{
			field = "divider"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			method("setDividerDrawable") {resourceRef(it)}
		}
		attribute{
			field = "tabStripEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setTabStripEnabled"
				kotlin="isStripEnabled"
				value { bool(it) }
			}
		}
		uselessAttribute("tabStripLeft")
		uselessAttribute("tabStripRight")
		uselessAttribute("tabLayout")
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="tabWidget"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTabWidget"

}
