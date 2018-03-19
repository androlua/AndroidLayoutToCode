package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.int
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------Gallery all expressions---------------
 * @attr ref android.R.styleable#Gallery_animationDuration
 * @attr ref android.R.styleable#Gallery_spacing
 * @attr ref android.R.styleable#Gallery_gravity
 *
 */
open class Gallery : AbsSpinner() {
	init {
		attribute{
			field = "animationDuration"
			attrType = arrayOf(AttrType.INTEGER)
			method("setAnimationDuration"){ int(it) }
		}
		attribute{
			field = "spacing"
			attrType = arrayOf(AttrType.INTEGER)
			method("setSpacing"){ int(it) }
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			method("setGravity"){ gravity(it) }
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all expressions---------------
	 *
	 */
	open class LayoutParams : ViewGroup.MarginLayoutParams()
	
}
