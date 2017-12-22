package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.float
import com.cz.layout2anko.inflate.int
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
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
	 * 获得控件映射名称
	 */
	override fun getViewName()="ratingBar"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedRatingBar"
	/**
	 * 解析RatingBar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"numStars"->attributes.add(ViewConvertItem("numStars",int(value)))
				"rating"->attributes.add(ViewConvertItem("rating",float(value)))
				"stepSize"->attributes.add(ViewConvertItem("stepSize",float(value)))
				"isIndicator"->attributes.add(ViewMethodConvertItem("setIsIndicator(${bool(value)})"))
			}
		}
	}

}
