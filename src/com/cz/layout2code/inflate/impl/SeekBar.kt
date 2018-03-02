package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------SeekBar all attributes---------------
 * @attr ref android.R.styleable#SeekBar_thumb
 *
 */
open class SeekBar : AbsSeekBar() {
	init {
		attribute{
			field = "thumb"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "thumb = ${resource(it)}" }
			javaMethod{ "setThumb(${resource(it)})" }
		}
		attribute{
			field = "thumbOffset"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "thumbOffset = ${dimen(it)}" }
			javaMethod{ "setThumbOffset(${dimen(it)})" }
		}
		attribute{
			field = "splitTrack"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			kotlinMethod { "splitTrack = ${bool(it)}" }
			javaMethod{ "setSplitTrack(${bool(it)})" }
		}
	}

	override fun getViewName()="seekBar"

	override fun getThemeViewName()="themedSeekBar"
}
