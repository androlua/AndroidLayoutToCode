package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextInputTimePickerView all attributes---------------
 *
 */
open class TextInputTimePickerView : RelativeLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="textInputTimePickerView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTextInputTimePickerView"
	/**
	 * 解析TextInputTimePickerView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}
