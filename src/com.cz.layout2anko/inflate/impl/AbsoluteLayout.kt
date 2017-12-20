package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------AbsoluteLayout all attributes---------------
 *
 */
open class AbsoluteLayout : ViewGroup() {
	
	/**
	 * 解析AbsoluteLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 *
	 */
	open class LayoutParams : ViewGroup.LayoutParams() {
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
		}

	}
	
}
