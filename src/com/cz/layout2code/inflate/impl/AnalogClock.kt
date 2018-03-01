package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------AnalogClock all attributes---------------
 * @attr ref android.R.styleable#AnalogClock_dial
 * @attr ref android.R.styleable#AnalogClock_hand_hour
 * @attr ref android.R.styleable#AnalogClock_hand_minute
 *
 */
open class AnalogClock : View() {
	init {
		uselessAttribute("dial")
		uselessAttribute("hand_hour")
		uselessAttribute("hand_minute")
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="analogClock"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedAnalogClock"
	/**
	 * 解析AnalogClock属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
