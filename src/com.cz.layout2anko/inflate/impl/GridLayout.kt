package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.int
import com.cz.layout2anko.inflate.item.ViewConvertItem
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
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="gridLayout"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedGridLayout"

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
				"orientation"->attributes.add(ViewConvertItem("orientation",orientation(value)))
				"rowCount"->attributes.add(ViewConvertItem("rowCount",int(value)))
				"columnCount"->attributes.add(ViewConvertItem("columnCount",int(value)))
				"useDefaultMargins"->attributes.add(ViewConvertItem("useDefaultMargins","setUseDefaultMargins",bool(value)))
				"rowOrderPreserved"->attributes.add(ViewConvertItem("isRowOrderPreserved","setRowOrderPreserved",bool(value)))
				"columnOrderPreserved"->attributes.add(ViewConvertItem("isColumnOrderPreserved","setColumnOrderPreserved",bool(value)))
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
					"layout_row","layout_rowSpan","layout_rowWeight",
					"layout_column","layout_columnSpan","layout_columnWeight",
					"layout_gravity" ->{
						attributes.add(ViewConvertItem(name,value,false))
					}
				}
			}
		}

	}
	
}
