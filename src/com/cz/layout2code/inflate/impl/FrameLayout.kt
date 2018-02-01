package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.resource

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------FrameLayout all attributes---------------
 * @attr ref android.R.styleable#FrameLayout_measureAllChildren
 *
 */
open class FrameLayout : ViewGroup() {
	init {
		attribute{
			field = "measureAllChildren"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "measureAllChildren = ${bool(it)}" }
			javaMethod{ "setMeasureAllChildren(${bool(it)})" }
		}
	}

	override fun getViewName()="frameLayout"

	override fun getThemeViewName()="themedFrameLayout"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#FrameLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : MarginLayoutParams() {
		init {
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "gravity = ${resource(it)}" }
				javaMethod{ "layoutParams.gravity=${resource(it)}" }
			}
		}
		
		/**
		 * 解析LayoutParams属性集
		 */
		override fun inflateAttributes(element: Element){
			super.inflateAttributes(element)
			element.attributes.forEach { addAttributeItems(it.name,it.value) }
		}
	}
	
}
