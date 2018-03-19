package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.color
import com.cz.layout2code.inflate.int
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------AdapterViewFlipper all expressions---------------
 * @attr ref android.R.styleable#AdapterViewFlipper_flipInterval
 * @attr ref android.R.styleable#AdapterViewFlipper_autoStart
 *
 */
open class AdapterViewFlipper : AdapterViewAnimator() {
	init {
		attribute{
			field = "flipInterval"
			attrType = arrayOf(AttrType.INTEGER)
			property("flipInterval"){ int(it) }
		}
		attribute{
			field = "autoStart"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setAutoStart"
				kotlin="isautoStart"
				value { bool(it) }
			}
		}
	}
}
