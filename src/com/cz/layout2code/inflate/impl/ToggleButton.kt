package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
import com.cz.layout2code.inflate.float
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------ToggleButton all attributes---------------
 * @attr ref android.R.styleable#ToggleButton_textOn
 * @attr ref android.R.styleable#ToggleButton_textOff
 * @attr ref android.R.styleable#ToggleButton_disabledAlpha
 *
 */
open class ToggleButton : CompoundButton() {
	init {
		attribute{
			field = "textOn"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "textOn = ${string(it)}" }
			javaMethod{ "setTextOn(${string(it)})" }
		}
		attribute{
			field = "textOff"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "textOff = ${string(it)}" }
			javaMethod{ "setTextOff(${string(it)})" }
		}
		uselessAttribute("disabledAlpha")
	}

	override fun getViewName()="toggleButton"

	override fun getThemeViewName()="themedToggleButton"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
