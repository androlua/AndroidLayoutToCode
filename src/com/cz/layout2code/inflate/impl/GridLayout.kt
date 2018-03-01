package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
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
	init {
		attribute{
			field = "orientation"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "orientation = ${orientation(this::class.java.simpleName,it)}" }
			javaMethod{ "setOrientation(${orientation(this::class.java.simpleName,it)})" }
		}
		attribute{
			field = "rowCount"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "rowCount = ${int(it)}" }
			javaMethod{ "setRowCount(${int(it)})" }
		}
		attribute{
			field = "columnCount"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "columnCount = ${int(it)}" }
			javaMethod{ "setColumnCount(${int(it)})" }
		}
		attribute{
			field = "useDefaultMargins"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "useDefaultMargins = ${bool(it)}" }
			javaMethod{ "setUseDefaultMargins(${bool(it)})" }
		}
		attribute{
			field = "alignmentMode"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "alignmentMode = ${alignmentMode(it)}" }
			javaMethod{ "setAlignmentMode(${alignmentMode(it)})" }
		}
		attribute{
			field = "rowOrderPreserved"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isRowOrderPreserved = ${bool(it)}" }
			javaMethod{ "setRowOrderPreserved(${bool(it)})" }
		}
		attribute{
			field = "columnOrderPreserved"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isColumnOrderPreserved = ${bool(it)}" }
			javaMethod{ "setColumnOrderPreserved(${bool(it)})" }
		}
	}

	override fun getViewName()="gridLayout"

	override fun getThemeViewName()="themedGridLayout"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
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
		init {
			uselessAttribute("layout_row")
			uselessAttribute("layout_rowSpan")
			uselessAttribute("layout_rowWeight")
			uselessAttribute("layout_column")
			uselessAttribute("layout_columnSpan")
			uselessAttribute("layout_columnWeight")
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "gravity = ${gravity(it)}" }
				javaMethod{ "layoutParams.gravity = ${gravity(it)}" }
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
