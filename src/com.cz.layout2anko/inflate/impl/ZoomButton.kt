package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ZoomButton all attributes---------------
 *
 */
open class ZoomButton : ImageButton() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="zoomButton"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedZoomButton"
	/**
	 * 解析ZoomButton属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}
