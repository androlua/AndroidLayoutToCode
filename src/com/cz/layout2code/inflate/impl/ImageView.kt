package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------ImageView all expressions---------------
 * @attr ref android.R.styleable#ImageView_adjustViewBounds
 * @attr ref android.R.styleable#ImageView_src
 * @attr ref android.R.styleable#ImageView_maxWidth
 * @attr ref android.R.styleable#ImageView_maxHeight
 * @attr ref android.R.styleable#ImageView_tint
 * @attr ref android.R.styleable#ImageView_scaleType
 * @attr ref android.R.styleable#ImageView_cropToPadding
 *
 */
open class ImageView : View() {
	init {
		attribute{
			field = "src"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			method("setImageResource"){ resourceRef(it) }
		}
		attribute{
			field = "scaleType"
			attrType = arrayOf(AttrType.FLAG)
			property("scaleType") { scaleType(it) }
		}
		attribute{
			field = "adjustViewBounds"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("adjustViewBounds") { bool(it) }
		}
		attribute{
			field = "maxWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("maxWidth") { dimen(it) }
		}
		attribute{
			field = "maxHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("maxHeight") { dimen(it) }
		}
		attribute{
			field = "tint"
			attrType = arrayOf(AttrType.COLOR)
			method("setImageTintList") { colorStateList(it) }
		}
		attribute{
			field = "baselineAlignBottom"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("baselineAlignBottom") { bool(it) }
		}
		attribute{
			field = "cropToPadding"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=16
			property("cropToPadding") { bool(it) }
		}
		attribute{
			field = "baseline"
			attrType = arrayOf(AttrType.DIMENSION)
			property("baseline") { dimen(it) }
		}
		attribute{
			field = "drawableAlpha"
			attrType = arrayOf(AttrType.INTEGER)
			method("setImageAlpha") { int(it) }
		}
		attribute{
			field = "tintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property {
				java="setTintMode"
				kotlin="imageTintMode"
				value { tintMode(it) }
			}
		}
	}

	override fun getViewName()="imageView"

	override fun getThemeViewName()="themedImageView"
}
