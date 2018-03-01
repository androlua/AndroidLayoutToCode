package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextClock all attributes---------------
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
			kotlinMethod { "format12Hour = ${string(it)}" }
			javaMethod{ "setFormat12Hour(${string(it)})" }
		}
		attribute{
			field = "format24Hour"
			attrType = arrayOf(AttrType.STRING)
			sdk=17
			kotlinMethod { "format24Hour = ${string(it)}" }
			javaMethod{ "setFormat24Hour(${string(it)})" }
		}
		attribute{
			field = "timeZone"
			attrType = arrayOf(AttrType.STRING)
			sdk=17
			kotlinMethod { "timeZone = ${string(it)}" }
			javaMethod{ "setTimeZone(${string(it)})" }
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
	/**
	 * 解析TextClock属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
