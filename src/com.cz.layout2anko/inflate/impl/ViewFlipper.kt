package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.int
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
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
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewFlipper"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewFlipper"
	/**
	 * 解析ViewFlipper属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"flipInterval"->attributes.add(ViewMethodConvertItem("setFlipInterval(${int(value)})"))
				"autoStart"->attributes.add(ViewConvertItem("isAutoStart","setAutoStart",int(value)))
			}
		}
	}

}
