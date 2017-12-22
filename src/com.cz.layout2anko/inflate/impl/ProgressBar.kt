package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.dimen
import com.cz.layout2anko.inflate.int
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.resource
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
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
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="progressBar"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedProgressBar"
	/**
	 * 解析ProgressBar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"animationResolution"->attributes.add(ViewConvertItem(name,value,false))
				"indeterminate"->attributes.add(ViewConvertItem("isIndeterminate","setIndeterminate",bool(value)))
				"indeterminateBehavior"->attributes.add(ViewConvertItem(name,value,false))
				"indeterminateDrawable"->attributes.add(ViewConvertItem("indeterminateDrawable",resource(value)))
				"indeterminateDuration"->attributes.add(ViewConvertItem(name,value,false))
				"indeterminateOnly"->attributes.add(ViewConvertItem(name,value,false))
				"interpolator"->attributes.add(ViewConvertItem("interpolator",resource(value)))
				"min"->attributes.add(ViewConvertItem(name,value,false))
				"max"->attributes.add(ViewConvertItem("max",int(value)))
				"maxHeight"->attributes.add(ViewConvertItem(name,value,false))
				"maxWidth"->attributes.add(ViewConvertItem(name,value,false))
				"minHeight"->attributes.add(ViewConvertItem(name,value,false))
				"minWidth"->attributes.add(ViewConvertItem(name,value,false))
				"mirrorForRtl"->attributes.add(ViewConvertItem(name,value,false))
				"progress"->attributes.add(ViewConvertItem("progress",int(value)))
				"progressDrawable"-> attributes.add(ViewConvertItem("progressDrawable",resource(value)))
				"secondaryProgress"->attributes.add(ViewMethodConvertItem("setSecondaryProgress(${int(value)})"))
			}
		}
	}

}
