package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.resourceRef
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
	 * 获得控件映射名称
	 */
	override fun getViewName()="checkedTextView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedCheckedTextView"
	/**
	 * 解析CheckedTextView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"checked"->attributes.add(ViewConvertItem("isChecked","setChecked",bool(value)))
				"checkMark"->attributes.add(ViewConvertItem("checkMarkDrawableResource","setCheckMarkDrawable",resourceRef(value)))
			}
		}
	}

}
