package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.dimen
import com.cz.layout2anko.inflate.id
import com.cz.layout2anko.inflate.int
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------AutoCompleteTextView all attributes---------------
 * @attr ref android.R.styleable#AutoCompleteTextView_completionHint
 * @attr ref android.R.styleable#AutoCompleteTextView_completionThreshold
 * @attr ref android.R.styleable#AutoCompleteTextView_completionHintView
 * @attr ref android.R.styleable#AutoCompleteTextView_dropDownSelector
 * @attr ref android.R.styleable#AutoCompleteTextView_dropDownAnchor
 * @attr ref android.R.styleable#AutoCompleteTextView_dropDownWidth
 * @attr ref android.R.styleable#AutoCompleteTextView_dropDownHeight
 * @attr ref android.R.styleable#ListPopupWindow_dropDownVerticalOffset
 * @attr ref android.R.styleable#ListPopupWindow_dropDownHorizontalOffset
 *
 */
open class AutoCompleteTextView : EditText() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="autoCompleteTextView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedAutoCompleteTextView"
	/**
	 * 解析AutoCompleteTextView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"completionHint"->attributes.add(ViewConvertItem("completionHint",string(value)))
				"completionThreshold"->attributes.add(ViewConvertItem("threshold",int(value)))
				"completionHintView","dropDownSelector"->attributes.add(ViewConvertItem(name,value,false))
				"dropDownAnchor"->attributes.add(ViewConvertItem("dropDownAnchor",id(value)))
				"dropDownWidth"->attributes.add(ViewConvertItem("dropDownWidth",int(value)))
				"dropDownHeight"->attributes.add(ViewConvertItem("dropDownHeight",int(value)))
				"dropDownVerticalOffset"->attributes.add(ViewConvertItem("dropDownVerticalOffset",int(value)))
				"dropDownHorizontalOffset"->attributes.add(ViewConvertItem("dropDownHorizontalOffset",int(value)))
			}
		}
	}

}
