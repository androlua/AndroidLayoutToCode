package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewStub all attributes---------------
 * @attr ref android.R.styleable#ViewStub_inflatedId
 * @attr ref android.R.styleable#ViewStub_layout
 *
 */
open class ViewStub : View() {
	
	/**
	 * 解析ViewStub属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"inflatedId"->attributes.add("inflatedId(=${id(value)})")
				"layout"->attributes.add("layoutResource = ${resourceRef(value)})")
			}
		}
	}

}
