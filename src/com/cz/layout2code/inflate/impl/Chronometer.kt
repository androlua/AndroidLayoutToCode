package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.colorStateList
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.string
import org.jdom.Element
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------Chronometer all expressions---------------
 * @attr ref android.R.styleable#Chronometer_format
 * @attr ref android.R.styleable#Chronometer_countDown
 *
 */
open class Chronometer : TextView() {
	init {
		attribute{
			field = "format"
			attrType = arrayOf(AttrType.STRING)
			property("format"){ string(it) }
		}
		attribute{
			field = "countDown"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=24
			property {
				java="setCountDown"
				kotlin="isCountDown"
				value { bool(it) }
			}
		}
	}

	override fun getViewName()="chronometer"

	override fun getThemeViewName()="themedChronometer"
}
