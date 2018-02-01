package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------ImageView all attributes---------------
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
			kotlinMethod { "setImageResource(${resourceRef(it)})" }
			javaMethod{ "setImageResource(${resourceRef(it)})" }
		}
		attribute{
			field = "scaleType"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "scaleType = ${scaleType(it)}" }
			javaMethod{ "setScaleType(${scaleType(it)})" }
		}
		attribute{
			field = "adjustViewBounds"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "adjustViewBounds = ${bool(it)}" }
			javaMethod{ "setAdjustViewBounds(${bool(it)})" }
		}
		attribute{
			field = "maxWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "maxWidth = ${dimen(it)}" }
			javaMethod{ "setMaxWidth(${dimen(it)})" }
		}
		attribute{
			field = "maxHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "maxHeight = ${dimen(it)}" }
			javaMethod{ "setMaxHeight(${dimen(it)})" }
		}
		attribute{
			field = "tint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setImageTintList(${colorStateList(it)})" }
			javaMethod{ "setImageTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "baselineAlignBottom"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "baselineAlignBottom = ${bool(it)}" }
			javaMethod{ "setBaselineAlignBottom(${bool(it)})" }
		}
		attribute{
			field = "cropToPadding"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=16
			kotlinMethod { "cropToPadding = ${bool(it)}" }
			javaMethod{ "setCropToPadding(${bool(it)})" }
		}
		attribute{
			field = "baseline"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "baseline = ${dimen(it)}" }
			javaMethod{ "setBaseline(${dimen(it)})" }
		}
		attribute{
			field = "drawableAlpha"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "setImageAlpha(${int(it)})" }
			javaMethod{ "setImageAlpha(${int(it)})" }
		}
		attribute{
			field = "tintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "imageTintMode = ${tintMode(it)}" }
			javaMethod{ "setTintMode(${tintMode(it)})" }
		}
	}

	override fun getViewName()="imageView"

	override fun getThemeViewName()="themedImageView"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
