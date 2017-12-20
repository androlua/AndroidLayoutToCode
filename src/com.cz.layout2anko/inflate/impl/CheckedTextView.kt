package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------CheckedTextView all attributes---------------
 * @attr ref android.R.styleable#CheckedTextView_checked
 * @attr ref android.R.styleable#CheckedTextView_checkMark
 *
 */
open class CheckedTextView : TextView() {
	
	/**
	 * 解析CheckedTextView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"checked"->attributes.add("isChecked=${bool(value)}\n")
				"checkMark"->attributes.add("checkMarkDrawableResource=${resourceRef(value)}\n")
			}
		}
	}

}
