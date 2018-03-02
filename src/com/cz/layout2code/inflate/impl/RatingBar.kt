package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.float
import com.cz.layout2code.inflate.int
import com.cz.layout2code.inflate.item.ViewNode

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------RatingBar all attributes---------------
 * @attr ref android.R.styleable#RatingBar_numStars
 * @attr ref android.R.styleable#RatingBar_rating
 * @attr ref android.R.styleable#RatingBar_stepSize
 * @attr ref android.R.styleable#RatingBar_isIndicator
 *
 */
open class RatingBar : AbsSeekBar() {
	init {
		attribute{
			field = "numStars"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "numStars = ${int(it)}" }
			javaMethod{ "setNumStars(${int(it)})" }
		}
		attribute{
			field = "rating"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "rating = ${float(it)}" }
			javaMethod{ "setRating(${float(it)})" }
		}
		attribute{
			field = "stepSize"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "stepSize = ${float(it)}" }
			javaMethod{ "setStepSize(${float(it)})" }
		}
		attribute{
			field = "isIndicator"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setIsIndicator(${bool(it)})" }
			javaMethod{ "setIsIndicator(${bool(it)})" }
		}
	}

	override fun getViewName()="ratingBar"

	override fun getThemeViewName()="themedRatingBar"
}
