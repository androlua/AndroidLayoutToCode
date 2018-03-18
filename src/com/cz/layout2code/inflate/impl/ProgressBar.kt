package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------ProgressBar all expressions---------------
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
			property("min"){int(it)}
		}
		attribute{
			field = "max"
			attrType = arrayOf(AttrType.INTEGER)
			property("max"){int(it)}
		}
		attribute{
			field = "progress"
			attrType = arrayOf(AttrType.INTEGER)
			property("progress"){int(it)}
		}
		attribute{
			field = "secondaryProgress"
			attrType = arrayOf(AttrType.INTEGER)
			property("secondaryProgress"){int(it)}
		}
		attribute{
			field = "indeterminate"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				kotlin="isIndeterminate"
				java="setIndeterminate"
				value { bool(it) }
			}
		}
		attribute{
			field = "indeterminateOnly"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				kotlin="isIndeterminate"
				java="setIndeterminate"
				value { bool(it) }
			}
		}
		attribute{
			field = "indeterminateDrawable"
			attrType = arrayOf(AttrType.REFERENCE)
			property("indeterminateDrawable"){resource(it)}
		}
		attribute{
			field = "progressDrawable"
			attrType = arrayOf(AttrType.REFERENCE)
			property("progressDrawable"){resource(it)}
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
			property("interpolator"){resource(it)}
		}
		uselessAttribute("animationResolution")
		uselessAttribute("mirrorForRtl")
		attribute{
			field = "progressTint"
			attrType = arrayOf(AttrType.COLOR)
			method("setProgressTintList"){ colorStateList(it) }
		}
		attribute{
			field = "progressTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property ("progressTintMode"){ tintMode(it) }
		}
		attribute{
			field = "progressBackgroundTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			property ("progressBackgroundTintList"){ color(it) }
		}
		attribute{
			field = "progressBackgroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property ("progressBackgroundTintMode"){ tintMode(it) }
		}
		attribute{
			field = "secondaryProgressTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			property ("secondaryProgressTintList"){ color(it) }
		}
		attribute{
			field = "secondaryProgressTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property ("secondaryProgressTintMode"){ tintMode(it) }
		}
		attribute{
			field = "indeterminateTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			property ("indeterminateTintList"){ color(it) }
		}
		attribute{
			field = "indeterminateTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property ("indeterminateTintMode"){ tintMode(it) }
		}
		attribute{
			field = "backgroundTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=21
			property ("backgroundTintList"){ color(it) }
		}
		attribute{
			field = "backgroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=21
			property ("backgroundTintMode"){ tintMode(it) }
		}
	}

	override fun getViewName()="progressBar"

	override fun getThemeViewName()="themedProgressBar"
}
