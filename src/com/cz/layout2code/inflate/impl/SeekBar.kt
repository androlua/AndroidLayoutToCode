package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------SeekBar all expressions---------------
 * @attr ref android.R.styleable#SeekBar_thumb
 *
 */
open class SeekBar : AbsSeekBar() {
	init {
		attribute{
			field = "thumb"
			attrType = arrayOf(AttrType.REFERENCE)
			property("thumb"){resource(it)}
		}
		attribute{
			field = "thumbOffset"
			attrType = arrayOf(AttrType.DIMENSION)
			property("thumbOffset"){dimen(it)}
		}
		attribute{
			field = "splitTrack"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			property("splitTrack"){bool(it)}
		}
	}

	override fun getViewName()="seekBar"

	override fun getThemeViewName()="themedSeekBar"
}
