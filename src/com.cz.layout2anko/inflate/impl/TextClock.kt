package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextClock all attributes---------------
 * @attr ref android.R.styleable#TextClock_format12Hour
 * @attr ref android.R.styleable#TextClock_format24Hour
 * @attr ref android.R.styleable#TextClock_timeZone
 *
 */
open class TextClock : TextView() {
	
	/**
	 * 解析TextClock属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"format12Hour"->{
				
				}
				"format24Hour"->{
				
				}
				"timeZone"->{
				
				}
			}
		}
	}

}
