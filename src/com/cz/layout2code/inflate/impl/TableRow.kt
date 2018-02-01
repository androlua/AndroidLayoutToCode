package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resource

/**
 * Created by cz on 2018/1/29.
 */
open class TableRow : LinearLayout() {

	override fun getViewName()="tableRow"

	override fun getThemeViewName()="themedTableRow"

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#TableRow_Cell_layout_column
	 * @attr ref android.R.styleable#TableRow_Cell_layout_span
	 *
	 */
	open class LayoutParams : LinearLayout.LayoutParams() {
		init {
			uselessAttribute("layout_column")
			uselessAttribute("layout_span")
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
