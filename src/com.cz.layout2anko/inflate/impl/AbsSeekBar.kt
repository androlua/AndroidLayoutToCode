package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------AbsSeekBar all attributes---------------
 *
 */
open class AbsSeekBar : ProgressBar() {

	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="absoluteLayout"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedAbsoluteLayout"

	/**
	 * 解析AbsSeekBar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}
