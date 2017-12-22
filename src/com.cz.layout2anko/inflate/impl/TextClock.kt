package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextClock all attributes---------------
 * @attr ref android.R.styleable#TextClock_format12Hour
 * @attr ref android.R.styleable#TextClock_format24Hour
 * @attr ref android.R.styleable#TextClock_timeZone
 *
 */
open class TextClock : TextView() {
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
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"format12Hour"->attributes.add(ViewConvertItem("format12Hour",string(value),17))
				"format24Hour"->attributes.add(ViewConvertItem("format24Hour",string(value),17))
				"timeZone"->attributes.add(ViewConvertItem("timeZone",string(value),17))
			}
		}
	}

}
