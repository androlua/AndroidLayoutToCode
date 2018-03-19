package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.expression.value.ClassFieldExpression
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resourceRef

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------AdapterViewAnimator all expressions---------------
 * @attr ref android.R.styleable#AdapterViewAnimator_inAnimation
 * @attr ref android.R.styleable#AdapterViewAnimator_outAnimation
 * @attr ref android.R.styleable#AdapterViewAnimator_animateFirstView
 * @attr ref android.R.styleable#AdapterViewAnimator_loopViews
 *
 */
open class AdapterViewAnimator : AdapterView() {
	init {
		attribute{
			field = "inAnimation"
			attrType = arrayOf(AttrType.BOOLEAN)
			methods("setInAnimation"){
				mutableListOf(ClassFieldExpression("context"), resourceRef(it))
			}
		}
		attribute{
			field = "outAnimation"
			attrType = arrayOf(AttrType.BOOLEAN)
			methods("setOutAnimation"){
				mutableListOf(ClassFieldExpression("context"), resourceRef(it))
			}
		}
		attribute{
			field = "animateFirstView"
			attrType = arrayOf(AttrType.BOOLEAN)
			method("setAnimateFirstView"){ bool(it) }
		}
	}

}
