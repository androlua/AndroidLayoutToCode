package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.int
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewFlipper all attributes---------------
 * @attr ref android.R.styleable#ViewFlipper_flipInterval
 * @attr ref android.R.styleable#ViewFlipper_autoStart
 *
 */
open class ViewFlipper : ViewAnimator() {
	init {
		attribute{
			field = "flipInterval"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "setFlipInterval(${int(it)})" }
			javaMethod{ "setFlipInterval(${int(it)})" }
		}
		attribute{
			field = "autoStart"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isAutoStart = ${bool(it)}" }
			javaMethod{ "setAutoStart(${bool(it)})" }
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewFlipper"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewFlipper"


}
