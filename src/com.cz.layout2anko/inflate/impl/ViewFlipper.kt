package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewFlipper all attributes---------------
 * @attr ref android.R.styleable#ViewFlipper_flipInterval
 * @attr ref android.R.styleable#ViewFlipper_autoStart
 *
 */
open class ViewFlipper : ViewAnimator() {
	
	/**
	 * 解析ViewFlipper属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"flipInterval"->{
				
				}
				"autoStart"->{
				
				}
			}
		}
	}

}
