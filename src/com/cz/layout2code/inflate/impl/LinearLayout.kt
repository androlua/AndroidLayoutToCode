package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------LinearLayout all attributes---------------
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
			kotlinMethod { "orientation = ${orientation(LinearLayout::class.java.simpleName,it)}" }
			javaMethod{ "setOrientation(${orientation(LinearLayout::class.java.simpleName,it)})" }
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "gravity = ${gravity(it)}" }
			javaMethod{ "setGravity(${gravity(it)})" }
		}
		attribute{
			field = "baselineAligned"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isBaselineAligned = ${bool(it)}" }
			javaMethod{ "setBaselineAligned(${bool(it)})" }
		}
		attribute{
			field = "baselineAlignedChildIndex"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "baselineAlignedChildIndex = ${int(it)}" }
			javaMethod{ "setBaselineAlignedChildIndex(${int(it)})" }
		}
		attribute{
			field = "weightSum"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "weightSum = ${float(it)}" }
			javaMethod{ "setWeightSum(${float(it)})" }
		}
		attribute{
			field = "measureWithLargestChild"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isMeasureWithLargestChildEnabled = ${bool(it)}" }
			javaMethod{ "setMeasureWithLargestChildEnabled(${bool(it)})" }
		}
		attribute{
			field = "divider"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			kotlinMethod { "setDividerDrawable(${resource(it)})" }
			javaMethod{ "setDividerDrawable(${resource(it)})" }
		}
		attribute{
			field = "showDividers"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "showDividers = ${dividerMode(it)}" }
			javaMethod{ "setShowDividers(${dividerMode(it)})" }
		}
		attribute{
			field = "dividerPadding"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "dividerPadding = ${dimen(it)}" }
			javaMethod{ "setDividerPadding(${dimen(it)})" }
		}
	}

	override fun getViewName()="linearLayout"

	override fun getThemeViewName()="themedLinearLayout"

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_weight
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : ViewGroup.MarginLayoutParams() {
		init {
			attribute{
				field = "layout_weight"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "weight = ${float(it)}" }
				javaMethod{ "layoutParams.weight = ${float(it)}" }
			}
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "gravity = ${gravity(it)}" }
				javaMethod{ "layoutParams.gravity = (${gravity(it)})" }
			}
		}
	}
	
}
