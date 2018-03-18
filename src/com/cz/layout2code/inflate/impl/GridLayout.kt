package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------GridLayout all expressions---------------
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
			property("orientation") { gridLayoutOrientation(it) }
		}
		attribute{
			field = "rowCount"
			attrType = arrayOf(AttrType.INTEGER)
			property("rowCount") { int(it) }
		}
		attribute{
			field = "columnCount"
			attrType = arrayOf(AttrType.INTEGER)
			property("columnCount") { int(it) }
		}
		attribute{
			field = "useDefaultMargins"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("useDefaultMargins") { bool(it) }
		}
		attribute{
			field = "alignmentMode"
			attrType = arrayOf(AttrType.FLAG)
			property("alignmentMode") { alignmentMode(it) }
		}
		attribute{
			field = "rowOrderPreserved"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setRowOrderPreserved"
				kotlin="isRowOrderPreserved"
				value{ bool(it) }
			}
		}
		attribute{
			field = "columnOrderPreserved"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setColumnOrderPreserved"
				kotlin="isColumnOrderPreserved"
				value{ bool(it) }
			}
		}
	}

	override fun getViewName()="gridLayout"

	override fun getThemeViewName()="themedGridLayout"

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all expressions---------------
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
				allProperty("gravity"){ gravity(it) }
			}
		}
	}
	
}
