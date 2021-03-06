package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------AutoCompleteTextView all expressions---------------
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

	init {
		attribute {
			field = "completionHint"
			attrType = arrayOf(AttrType.STRING)
			property("completionHint"){ string(it) }
		}
		attribute {
			field = "completionThreshold"
			attrType = arrayOf(AttrType.INTEGER)
			property("threshold"){ int(it) }
		}
		//不可用属性
		uselessAttribute("completionHintView")
		uselessAttribute("dropDownSelector")
		attribute {
			field = "dropDownAnchor"
			attrType = arrayOf(AttrType.REFERENCE)
			property("dropDownAnchor"){ id(it) }
		}
		attribute {
			field = "dropDownWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("dropDownWidth"){ dimen(it) }
		}
		attribute {
			field = "dropDownHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("dropDownHeight"){ dimen(it) }
		}
		attribute {
			field = "dropDownVerticalOffset"
			attrType = arrayOf(AttrType.DIMENSION)
			property("dropDownVerticalOffset"){ dimen(it) }
		}
		attribute {
			field = "dropDownHorizontalOffset"
			attrType = arrayOf(AttrType.DIMENSION)
			property("dropDownHorizontalOffset"){ dimen(it) }
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="autoCompleteTextView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedAutoCompleteTextView"
}
