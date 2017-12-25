package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.prefs.AttrType
import com.cz.layout2anko.inflate.prefs.ViewConfiguration
import com.cz.layout2anko.inflate.prefs.ViewStyle
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


	companion object{
		val viewStyleItems= mutableListOf<ViewStyle>()
		init {
			ViewConfiguration.declareStyleable(viewStyleItems) {
				item {
					field = "completionHint"
					attrType = arrayOf(AttrType.STRING)
					kotlinMethod { "completionHint = ${string(it)}" }
					javaMethod { "setCompletionHint(${string(it)});" }
				}
				item {
					field = "completionThreshold"
					attrType = arrayOf(AttrType.INTEGER)
					kotlinMethod { "threshold = ${int(it)}" }
					javaMethod { "setThreshold(${int(it)});" }
				}
				item {
					field = "completionHintView"
					convert=false
				}
				item {
					field = "dropDownSelector"
					convert=false
				}
				item {
					field = "dropDownAnchor"
					attrType = arrayOf(AttrType.REFERENCE)
					kotlinMethod { "dropDownAnchor = ${id(it)}" }
					javaMethod { "setDropDownAnchor(${id(it)});" }
				}
				item {
					field = "dropDownWidth"
					attrType = arrayOf(AttrType.DIMENSION)
					kotlinMethod { "dropDownWidth = ${dimen(it)}" }
					javaMethod { "setDropDownWidth(${dimen(it)});" }
				}
				item {
					field = "dropDownHeight"
					attrType = arrayOf(AttrType.DIMENSION)
					kotlinMethod { "dropDownHeight = ${dimen(it)}" }
					javaMethod { "setDropDownHeight(${dimen(it)});" }
				}
				item {
					field = "dropDownVerticalOffset"
					attrType = arrayOf(AttrType.DIMENSION)
					kotlinMethod { "dropDownVerticalOffset = ${dimen(it)}" }
					javaMethod { "setDropDownVerticalOffset(${dimen(it)});" }
				}
				item {
					field = "dropDownHorizontalOffset"
					attrType = arrayOf(AttrType.DIMENSION)
					kotlinMethod { "dropDownHorizontalOffset = ${dimen(it)}" }
					javaMethod { "setDropDownHorizontalOffset(${dimen(it)});" }
				}

			}
		}
	}

}
