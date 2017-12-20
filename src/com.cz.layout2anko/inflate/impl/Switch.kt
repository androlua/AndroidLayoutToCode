package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------Switch all attributes---------------
 * @attr ref android.R.styleable#Switch_textOn
 * @attr ref android.R.styleable#Switch_textOff
 * @attr ref android.R.styleable#Switch_switchMinWidth
 * @attr ref android.R.styleable#Switch_switchPadding
 * @attr ref android.R.styleable#Switch_switchTextAppearance
 * @attr ref android.R.styleable#Switch_thumb
 * @attr ref android.R.styleable#Switch_thumbTextPadding
 * @attr ref android.R.styleable#Switch_track
 *
 */
open class Switch : CompoundButton() {
	
	/**
	 * 解析Switch属性集,并返回解析后的anko代码
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
				"switchMinWidth"->{
				
				}
				"switchPadding"->{
				
				}
				"switchTextAppearance"->{
				
				}
				"thumb"->{
				
				}
				"thumbTextPadding"->{
				
				}
				"track"->{
				
				}
			}
		}
	}

}
