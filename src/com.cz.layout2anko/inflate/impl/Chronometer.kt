package com.cz.layout2anko.inflate.impl

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
	 * 解析Chronometer属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"format"->attributes.add("format=${string(value)}\n")
				"countDown"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.N){\n")
					attributes.add("isCountDown=${bool(value)}\n")
					attributes.add("}")
				}
			}
		}
	}

}
