package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------RatingBar all attributes---------------
 * @attr ref android.R.styleable#RatingBar_numStars
 * @attr ref android.R.styleable#RatingBar_rating
 * @attr ref android.R.styleable#RatingBar_stepSize
 * @attr ref android.R.styleable#RatingBar_isIndicator
 *
 */
open class RatingBar : AbsSeekBar() {
	
	/**
	 * 解析RatingBar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"numStars"->{
				
				}
				"rating"->{
				
				}
				"stepSize"->{
				
				}
				"isIndicator"->{
				
				}
			}
		}
	}

}
