package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------Space all attributes---------------
 *
 */
open class Space : View() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="space"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedSpace"
	/**
	 * 解析Space属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}
