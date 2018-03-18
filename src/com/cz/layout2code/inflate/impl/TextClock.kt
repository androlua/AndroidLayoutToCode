package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextClock all expressions---------------
 * @attr ref android.R.styleable#TextClock_format12Hour
 * @attr ref android.R.styleable#TextClock_format24Hour
 * @attr ref android.R.styleable#TextClock_timeZone
 * apk 17新出控件
 */
open class TextClock : TextView() {
	init {
		attribute{
			field = "format12Hour"
			attrType = arrayOf(AttrType.STRING)
			sdk=17
			property("format12Hour"){ string(it) }
		}
		attribute{
			field = "format24Hour"
			attrType = arrayOf(AttrType.STRING)
			sdk=17
			property("format24Hour"){ string(it) }
		}
		attribute{
			field = "timeZone"
			attrType = arrayOf(AttrType.STRING)
			sdk=17
			property("timeZone"){ string(it) }
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="textClock"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTextClock"

}
