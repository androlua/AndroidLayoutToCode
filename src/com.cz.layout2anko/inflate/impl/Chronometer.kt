package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------Chronometer all attributes---------------
 * @attr ref android.R.styleable#Chronometer_format
 * @attr ref android.R.styleable#Chronometer_countDown
 *
 */
open class Chronometer : TextView() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="chronometer"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedChronometer"
	/**
	 * 解析Chronometer属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"format"->attributes.add(ViewConvertItem("format",string(value)))
				"countDown"->attributes.add(ViewConvertItem("isCountDown","setCountDown",string(value),24))
			}
		}
	}

}
