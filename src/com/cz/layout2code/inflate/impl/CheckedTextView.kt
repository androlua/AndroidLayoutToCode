package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------CheckedTextView all attributes---------------
 * @attr ref android.R.styleable#CheckedTextView_checked
 * @attr ref android.R.styleable#CheckedTextView_checkMark
 *
 */
open class CheckedTextView : TextView() {
	init {
		attribute{
			field = "checked"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isChecked = ${bool(it)}" }
			javaMethod{ "setChecked(${bool(it)})" }
		}
		attribute{
			field = "checkMark"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setCheckMarkDrawable(${resource(it)})" }
			javaMethod{ "setCheckMarkDrawable(${resource(it)})" }
		}
		attribute{
			field = "checkMarkTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setCheckMarkTintList(${colorStateList(it)})" }
			javaMethod{ "setCheckMarkTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "tintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "tintMode = ${tintMode(it)}" }
			javaMethod{ "setCheckMarkTintMode(${tintMode(it)})" }
		}
		uselessAttribute("checkMarkGravity")
	}

	override fun getViewName()="checkedTextView"

	override fun getThemeViewName()="themedCheckedTextView"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
