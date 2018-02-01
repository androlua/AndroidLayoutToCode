package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------Chronometer all attributes---------------
 * @attr ref android.R.styleable#Chronometer_format
 * @attr ref android.R.styleable#Chronometer_countDown
 *
 */
open class Chronometer : TextView() {
	init {
		attribute{
			field = "format"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "format = ${string(it)}" }
			javaMethod{ "setFormat(${string(it)})" }
		}
		attribute{
			field = "countDown"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=24
			kotlinMethod { "isCountDown = ${bool(it)}" }
			javaMethod{ "setCountDown(${bool(it)})" }
		}
	}

	override fun getViewName()="chronometer"

	override fun getThemeViewName()="themedChronometer"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
