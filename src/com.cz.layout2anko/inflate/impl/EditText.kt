package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------EditText all attributes---------------
 *
 */
open class EditText : TextView() {
	
	/**
	 * 解析EditText属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}