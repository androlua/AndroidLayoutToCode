package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TableRow all attributes---------------
 *
 */
open class TableRow : LinearLayout() {
	
	/**
	 * 解析TableRow属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

	 fun getLayoutParams()=LayoutParams()

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
				when(name){
					"layout_column"->{
					
					}
					"layout_span"->{
					
					}
				}
			}
		}

	}
	
}
