package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TableLayout all attributes---------------
 *
 */
open class TableLayout : LinearLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="tableLayout"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTableLayout"
	/**
	 * 解析TableLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

	 override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 *
	 */
	open class LayoutParams : LinearLayout.LayoutParams() {
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
		}

	}
	
}
