package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.expression.value.ClassFieldExpression
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------Switch all expressions---------------
 * @attr ref android.R.styleable#Switch_textOn
 * @attr ref android.R.styleable#Switch_textOff
 * @attr ref android.R.styleable#Switch_switchMinWidth
 * @attr ref android.R.styleable#Switch_switchPadding
 * @attr ref android.R.styleable#Switch_switchTextAppearance
 * @attr ref android.R.styleable#Switch_thumb
 * @attr ref android.R.styleable#Switch_thumbTextPadding
 * @attr ref android.R.styleable#Switch_track
 *
 */
open class Switch : CompoundButton() {
	init {
		attribute{
			field = "thumb"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			method("setThumbResource"){ resourceRef(it) }
		}
		attribute{
			field = "thumbTint"
			attrType = arrayOf(AttrType.COLOR)
			method("setThumbTintList"){ colorStateList(it) }
		}
		attribute{
			field = "thumbTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			property("thumbTintMode"){ tintMode(it) }
		}
		attribute{
			field = "track"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			method("setTrackResource"){ resourceRef(it) }
		}
		attribute{
			field = "trackTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=23
			property("trackTintList"){ colorStateList(it) }
		}
		attribute{
			field = "trackTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			property("trackTintMode"){ tintMode(it) }
		}
		attribute{
			field = "textOn"
			attrType = arrayOf(AttrType.STRING)
			property("textOn"){ string(it) }
		}
		attribute{
			field = "textOff"
			attrType = arrayOf(AttrType.STRING)
			property("textOff"){ string(it) }
		}
		attribute{
			field = "thumbTextPadding"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			property("thumbTextPadding"){ dimen(it) }
		}
		attribute{
			field = "switchTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			methods("setSwitchTextAppearance"){
				mutableListOf(ClassFieldExpression("context"),resourceRef(it))
			}
		}
		attribute{
			field = "switchMinWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			property("switchMinWidth"){dimen(it)}
		}
		attribute{
			field = "switchPadding"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			property("switchPadding"){dimen(it)}
		}
		attribute{
			field = "splitTrack"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			property("splitTrack"){bool(it)}
		}
		attribute{
			field = "showText"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			property("showText"){bool(it)}
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="switch"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedSwitch"

}
