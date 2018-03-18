package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------LinearLayout all expressions---------------
 * @attr ref android.R.styleable#LinearLayout_baselineAligned
 * @attr ref android.R.styleable#LinearLayout_baselineAlignedChildIndex
 * @attr ref android.R.styleable#LinearLayout_gravity
 * @attr ref android.R.styleable#LinearLayout_measureWithLargestChild
 * @attr ref android.R.styleable#LinearLayout_orientation
 * @attr ref android.R.styleable#LinearLayout_weightSum
 *
 */
open class LinearLayout : ViewGroup() {
	init {
		attribute{
			field = "orientation"
			attrType = arrayOf(AttrType.FLAG)
			property("orientation") {linearLayoutOrientation(it) }
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			property("gravity") {gravity(it) }
		}
		attribute{
			field = "baselineAligned"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setBaselineAligned"
				kotlin="isBaselineAligned"
				value { bool(it) }
			}
		}
		attribute{
			field = "baselineAlignedChildIndex"
			attrType = arrayOf(AttrType.INTEGER)
			property("baselineAlignedChildIndex") {int(it) }
		}
		attribute{
			field = "weightSum"
			attrType = arrayOf(AttrType.FLOAT)
			property("weightSum") {float(it) }
		}
		attribute{
			field = "measureWithLargestChild"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setMeasureWithLargestChildEnabled"
				kotlin="isMeasureWithLargestChildEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "divider"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			method("setDividerDrawable"){resource(it)}
		}
		attribute{
			field = "showDividers"
			attrType = arrayOf(AttrType.FLAG)
			property("showDividers"){ dividerMode(it) }
		}
		attribute{
			field = "dividerPadding"
			attrType = arrayOf(AttrType.DIMENSION)
			property("dividerPadding"){ dimen(it) }
		}
	}

	override fun getViewName()="linearLayout"

	override fun getThemeViewName()="themedLinearLayout"

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all expressions---------------
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_weight
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : ViewGroup.MarginLayoutParams() {
		init {
			attribute{
				field = "layout_weight"
				attrType = arrayOf(AttrType.REFERENCE)
				allProperty("weight"){ float(it) }
			}
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				allProperty("gravity"){ gravity(it) }
			}
		}
	}
	
}
