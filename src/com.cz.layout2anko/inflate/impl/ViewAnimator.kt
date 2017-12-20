package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewAnimator all attributes---------------
 * @attr ref android.R.styleable#ViewAnimator_inAnimation
 * @attr ref android.R.styleable#ViewAnimator_outAnimation
 * @attr ref android.R.styleable#ViewAnimator_animateFirstView
 *
 */
open class ViewAnimator : FrameLayout() {
	
	/**
	 * 解析ViewAnimator属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"inAnimation"->{
				
				}
				"outAnimation"->{
				
				}
				"animateFirstView"->{
				
				}
			}
		}
	}

}
