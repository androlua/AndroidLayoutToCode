package com.cz.layout2anko.inflate.impl

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
	 * 解析AutoCompleteTextView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"completionHint"->attributes.add("completionHint=${string(value)}")
				"completionThreshold"->attributes.add("threshold=${dimen(value)}")
				"completionHintView"->{
					attributes.add("//Can't reverse completionHintView!")
					attributes.add("//completionHintView=$value")
				}
				"dropDownSelector"->{
					attributes.add("//Can't reverse dropDownSelector!")
					attributes.add("//dropDownSelector=$value")
				}
				"dropDownAnchor"->attributes.add("dropDownAnchor=${id(value)}")
				"dropDownWidth"->attributes.add("dropDownWidth=${int(value)}")
				"dropDownHeight"->attributes.add("dropDownHeight=${int(value)}")
				"dropDownVerticalOffset"->attributes.add("dropDownVerticalOffset=${int(value)}")
				"dropDownHorizontalOffset"->attributes.add("dropDownHorizontalOffset=${int(value)}")
			}
		}
	}

}
