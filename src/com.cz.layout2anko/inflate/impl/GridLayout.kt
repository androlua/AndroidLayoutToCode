package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------GridLayout all attributes---------------
 * @attr ref android.R.styleable#GridLayout_orientation
 * @attr ref android.R.styleable#GridLayout_rowCount
 * @attr ref android.R.styleable#GridLayout_columnCount
 * @attr ref android.R.styleable#GridLayout_useDefaultMargins
 * @attr ref android.R.styleable#GridLayout_rowOrderPreserved
 * @attr ref android.R.styleable#GridLayout_columnOrderPreserved
 *
 */
open class GridLayout : ViewGroup() {

	fun orientation(orientation:String)=when(orientation){
		"horizontal"->"GridLayout.HORIZONTAL"
		else ->"GridLayout.VERTICAL"
	}
	/**
	 * 解析GridLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"orientation"->attributes.add("orientation=${orientation(value)}")
				"rowCount"->attributes.add("rowCount=${int(value)}")
				"columnCount"->attributes.add("columnCount=${int(value)}")
				"useDefaultMargins"->attributes.add("useDefaultMargins=${bool(value)}")
				"rowOrderPreserved"->attributes.add("isRowOrderPreserved=${bool(value)}")
				"columnOrderPreserved"->attributes.add("isColumnOrderPreserved=${bool(value)}")
			}
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_row
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_rowSpan
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_rowWeight
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_column
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_columnSpan
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_columnWeight
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : MarginLayoutParams() {
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				val value=it.value
				when(name){
					"layout_row"->{
						attributes.add("//Can't reverse layout_row!")
						attributes.add("//layout_row=$value")
					}
					"layout_rowSpan"->{
						attributes.add("//Can't reverse layout_rowSpan!")
						attributes.add("//layout_rowSpan=$value")
					}
					"layout_rowWeight"->{
						attributes.add("//Can't reverse layout_rowWeight!")
						attributes.add("//layout_rowWeight=$value")
					}
					"layout_column"->{
						attributes.add("//Can't reverse layout_column!")
						attributes.add("//layout_column=$value")
					}
					"layout_columnSpan"->{
						attributes.add("//Can't reverse layout_columnSpan!")
						attributes.add("//layout_columnSpan=$value")
					}
					"layout_columnWeight"->{
						attributes.add("//Can't reverse layout_columnWeight!")
						attributes.add("//layout_columnWeight=$value")
					}
					"layout_gravity"->attributes.add("gravity=${gravity(value)}")
				}
			}
		}

	}
	
}
