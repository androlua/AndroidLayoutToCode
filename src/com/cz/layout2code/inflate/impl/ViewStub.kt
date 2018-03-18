package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.id
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resourceRef
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewStub all expressions---------------
 * @attr ref android.R.styleable#ViewStub_inflatedId
 * @attr ref android.R.styleable#ViewStub_layout
 *
 */
open class ViewStub : View() {
	init {
		attribute{
			field = "id"
			attrType = arrayOf(AttrType.REFERENCE)
			property("id"){id(it)}
		}
		attribute{
			field = "layout"
			attrType = arrayOf(AttrType.REFERENCE)
			property("layoutResource"){resourceRef(it)}
		}
		attribute{
			field = "inflatedId"
			attrType = arrayOf(AttrType.REFERENCE)
			property("inflatedId"){id(it)}
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewStub"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewStub"

}
