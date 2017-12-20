package com.cz.layout2anko.inflate.impl

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
	
	/**
	 * 解析AnalogClock属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"dial"->{
					attributes.add("//Can't reverse dial!")
					attributes.add("//dial=$value")
				}
				"hand_hour"->{
					attributes.add("//Can't reverse hand_hour!")
					attributes.add("//hand_hour=$value")
				}
				"hand_minute"->{
					attributes.add("//Can't reverse hand_minute!")
					attributes.add("//hand_minute=$value")
				}
			}
		}
	}

}
