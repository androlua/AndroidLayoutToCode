package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------DateTimeView all attributes---------------
 *
 */
open class DateTimeView : TextView() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="dateTimeView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedDateTimeView"
	/**
	 * 解析DateTimeView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}
