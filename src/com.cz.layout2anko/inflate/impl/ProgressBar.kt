package com.cz.layout2anko.inflate.impl

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
	 * 解析ProgressBar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"animationResolution"->{
				
				}
				"indeterminate"->{
				
				}
				"indeterminateBehavior"->{
				
				}
				"indeterminateDrawable"->{
				
				}
				"indeterminateDuration"->{
				
				}
				"indeterminateOnly"->{
				
				}
				"interpolator"->{
				
				}
				"min"->{
				
				}
				"max"->{
				
				}
				"maxHeight"->{
				
				}
				"maxWidth"->{
				
				}
				"minHeight"->{
				
				}
				"minWidth"->{
				
				}
				"mirrorForRtl"->{
				
				}
				"progress"->{
				
				}
				"progressDrawable"->{
				
				}
				"secondaryProgress"->{
				
				}
			}
		}
	}

}
