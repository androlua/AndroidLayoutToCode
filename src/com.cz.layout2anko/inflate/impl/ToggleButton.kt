package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ToggleButton all attributes---------------
 * @attr ref android.R.styleable#ToggleButton_textOn
 * @attr ref android.R.styleable#ToggleButton_textOff
 * @attr ref android.R.styleable#ToggleButton_disabledAlpha
 *
 */
open class ToggleButton : CompoundButton() {
	
	/**
	 * 解析ToggleButton属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"textOn"->{
				
				}
				"textOff"->{
				
				}
				"disabledAlpha"->{
				
				}
			}
		}
	}

}
