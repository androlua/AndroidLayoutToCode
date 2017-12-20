package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------FrameLayout all attributes---------------
 * @attr ref android.R.styleable#FrameLayout_measureAllChildren
 *
 */
open class FrameLayout : ViewGroup() {
	
	/**
	 * 解析FrameLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"measureAllChildren"->{
				
				}
			}
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#FrameLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : MarginLayoutParams() {
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				when(name){
					"layout_gravity"->{
					
					}
				}
			}
		}

	}
	
}
