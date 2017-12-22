package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.dimen
import com.cz.layout2anko.inflate.item.ViewConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TableRow all attributes---------------
 *
 */
open class TableRow : LinearLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="tableRow"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTableRow"
	/**
	 * 解析TableRow属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

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
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				val value=it.value
				when(name){
					"layout_column","layout_span"->attributes.add(ViewConvertItem(name,value,false))
				}
			}
		}

	}
	
}
