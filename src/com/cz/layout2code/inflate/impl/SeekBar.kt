package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.color
import com.cz.layout2code.inflate.resource
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
			kotlinMethod { "splitTrack = ${bool(it)}" }
			javaMethod{ "setSplitTrack(${bool(it)})" }
		}
		attribute{
			field = "useDisabledAlpha"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "useDisabledAlpha = ${bool(it)}" }
			javaMethod{ "setUseDisabledAlpha(${bool(it)})" }
		}
		attribute{
			field = "thumbTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "thumbTint = ${color(it)}" }
			javaMethod{ "setThumbTint(${color(it)})" }
		}
		attribute{
			field = "thumbTintMode"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "thumbTintMode = ${thumbTintMode(it)}" }
			javaMethod{ "setThumbTintMode(${thumbTintMode(it)})" }
		}
		attribute{
			field = "tickMark"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "tickMark = ${resource(it)}" }
			javaMethod{ "setTickMark(${resource(it)})" }
		}
		attribute{
			field = "tickMarkTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "tickMarkTint = ${color(it)}" }
			javaMethod{ "setTickMarkTint(${color(it)})" }
		}
		attribute{
			field = "tickMarkTintMode"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "tickMarkTintMode = ${tickMarkTintMode(it)}" }
			javaMethod{ "setTickMarkTintMode(${tickMarkTintMode(it)})" }
		}
	}

	override fun getViewName()="seekBar"

	override fun getThemeViewName()="themedSeekBar"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
