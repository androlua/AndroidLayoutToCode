package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------ProgressBar all attributes---------------
 * @attr ref android.R.styleable#ProgressBar_animationResolution
 * @attr ref android.R.styleable#ProgressBar_indeterminate
 * @attr ref android.R.styleable#ProgressBar_indeterminateBehavior
 * @attr ref android.R.styleable#ProgressBar_indeterminateDrawable
 * @attr ref android.R.styleable#ProgressBar_indeterminateDuration
 * @attr ref android.R.styleable#ProgressBar_indeterminateOnly
 * @attr ref android.R.styleable#ProgressBar_interpolator
 * @attr ref android.R.styleable#ProgressBar_min
 * @attr ref android.R.styleable#ProgressBar_max
 * @attr ref android.R.styleable#ProgressBar_maxHeight
 * @attr ref android.R.styleable#ProgressBar_maxWidth
 * @attr ref android.R.styleable#ProgressBar_minHeight
 * @attr ref android.R.styleable#ProgressBar_minWidth
 * @attr ref android.R.styleable#ProgressBar_mirrorForRtl
 * @attr ref android.R.styleable#ProgressBar_progress
 * @attr ref android.R.styleable#ProgressBar_progressDrawable
 * @attr ref android.R.styleable#ProgressBar_secondaryProgress
 *
 */
open class ProgressBar : View() {
	init {
		attribute{
			field = "min"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "min = ${int(it)}" }
			javaMethod{ "setMin(${int(it)})" }
		}
		attribute{
			field = "max"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "max = ${int(it)}" }
			javaMethod{ "setMax(${int(it)})" }
		}
		attribute{
			field = "progress"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "progress = ${int(it)}" }
			javaMethod{ "setProgress(${int(it)})" }
		}
		attribute{
			field = "secondaryProgress"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "secondaryProgress = ${int(it)}" }
			javaMethod{ "setSecondaryProgress(${int(it)})" }
		}
		attribute{
			field = "indeterminate"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isIndeterminate = ${bool(it)}" }
			javaMethod{ "setIndeterminate(${bool(it)})" }
		}
		attribute{
			field = "indeterminateOnly"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isIndeterminate = ${bool(it)}" }
			javaMethod{ "setIndeterminate(${bool(it)})" }
		}
		attribute{
			field = "indeterminateDrawable"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "indeterminateDrawable = ${resource(it)}" }
			javaMethod{ "setIndeterminateDrawable(${resource(it)})" }
		}
		attribute{
			field = "progressDrawable"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "progressDrawable = ${resource(it)}" }
			javaMethod{ "setProgressDrawable(${resource(it)})" }
		}
		uselessAttribute("indeterminateDuration")
		uselessAttribute("indeterminateBehavior")
		uselessAttribute("minWidth")
		uselessAttribute("maxWidth")
		uselessAttribute("minHeight")
		uselessAttribute("maxHeight")

		attribute{
			field = "interpolator"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "interpolator = ${resource(it)}" }
			javaMethod{ "setInterpolator(${resource(it)})" }
		}
		uselessAttribute("animationResolution")
		uselessAttribute("mirrorForRtl")
		attribute{
			field = "progressTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setProgressTintList(${colorStateList(it)})" }
			javaMethod{ "setProgressTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "progressTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "progressTintMode = ${tintMode(it)}" }
			javaMethod{ "setProgressTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "progressBackgroundTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			kotlinMethod { "progressBackgroundTintList = ${color(it)}" }
			javaMethod{ "setProgressBackgroundTintList(${color(it)})" }
		}
		attribute{
			field = "progressBackgroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "progressBackgroundTintMode = ${tintMode(it)}" }
			javaMethod{ "setProgressBackgroundTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "secondaryProgressTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			kotlinMethod { "secondaryProgressTintList = ${color(it)}" }
			javaMethod{ "setSecondaryProgressTintList(${color(it)})" }
		}
		attribute{
			field = "secondaryProgressTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "secondaryProgressTintMode = ${tintMode(it)}" }
			javaMethod{ "setSecondaryProgressTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "indeterminateTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			kotlinMethod { "indeterminateTintList = ${color(it)}" }
			javaMethod{ "setIndeterminateTintList(${color(it)})" }
		}
		attribute{
			field = "indeterminateTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "indeterminateTintMode = ${tintMode(it)}" }
			javaMethod{ "setIndeterminateTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "backgroundTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			kotlinMethod { "backgroundTintList = ${color(it)}" }
			javaMethod{ "setBackgroundTintList(${color(it)})" }
		}
		attribute{
			field = "backgroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "backgroundTintMode = ${tintMode(it)}" }
			javaMethod{ "setBackgroundTintMode(${tintMode(it)})" }
		}
	}

	override fun getViewName()="progressBar"

	override fun getThemeViewName()="themedProgressBar"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
