package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.color
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.resource
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------TimePicker all attributes---------------
 * @attr ref android.R.styleable#TimePicker_timePickerMode
 *
 */
open class TimePicker : FrameLayout() {
	init {
		uselessAttribute("legacyLayout")
		uselessAttribute("internalLayout")
		uselessAttribute("headerTextColor")
		uselessAttribute("headerBackground")
		uselessAttribute("numbersTextColor")
		uselessAttribute("numbersInnerTextColor")
		uselessAttribute("numbersBackgroundColor")
		uselessAttribute("numbersSelectorColor")
		uselessAttribute("timePickerMode")
		uselessAttribute("headerAmPmTextAppearance")
		uselessAttribute("headerTimeTextAppearance")
		uselessAttribute("amPmTextColor")
		uselessAttribute("amPmBackgroundColor")
		uselessAttribute("dialogMode")
	}

	override fun getViewName()="timePicker"

	override fun getThemeViewName()="themedTimePicker"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}