package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TwoLineListItem all attributes---------------
 * @attr ref android.R.styleable#TwoLineListItem_mode
 *
 */
open class TwoLineListItem : RelativeLayout() {
	
	/**
	 * 解析TwoLineListItem属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"mode"->{
				
				}
			}
		}
	}

}
