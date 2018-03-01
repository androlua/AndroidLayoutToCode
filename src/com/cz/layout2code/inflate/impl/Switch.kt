package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------Switch all attributes---------------
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
			kotlinMethod { "setThumbResource(${resourceRef(it)})" }
			javaMethod{ "setThumbResource(${resourceRef(it)})" }
		}
		attribute{
			field = "thumbTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setThumbTintList(${colorStateList(it)})" }
			javaMethod{ "setThumbTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "thumbTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "thumbTintMode = ${tintMode(it)}" }
			javaMethod{ "setThumbTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "track"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "setTrackResource(${resourceRef(it)})" }
			javaMethod{ "setTrackResource(${resourceRef(it)})" }
		}
		attribute{
			field = "trackTint"
			attrType = arrayOf(AttrType.COLOR)
			sdk=23
			kotlinMethod { "trackTintList = ${colorStateList(it)}" }
			javaMethod{ "setTrackTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "trackTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "trackTintMode = ${tintMode(it)}" }
			javaMethod{ "setTrackTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "textOn"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "textOn = ${string(it)}" }
			javaMethod{ "setTextOn(${string(it)})" }
		}
		attribute{
			field = "textOff"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "textOff = ${string(it)}" }
			javaMethod{ "setTextOff(${string(it)})" }
		}
		attribute{
			field = "thumbTextPadding"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			kotlinMethod { "thumbTextPadding = ${dimen(it)}" }
			javaMethod{ "setThumbTextPadding(${dimen(it)})" }
		}
		attribute{
			field = "switchTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setSwitchTextAppearance(context,${resourceRef(it)}" }
			javaMethod{ "setSwitchTextAppearance(context,${resourceRef(it)}" }
		}
		attribute{
			field = "switchMinWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			kotlinMethod { "switchMinWidth = ${dimen(it)}" }
			javaMethod{ "setSwitchMinWidth(${dimen(it)})" }
		}
		attribute{
			field = "switchPadding"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=16
			kotlinMethod { "switchPadding = ${dimen(it)}" }
			javaMethod{ "setSwitchPadding(${dimen(it)})" }
		}
		attribute{
			field = "splitTrack"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			kotlinMethod { "splitTrack = ${bool(it)}" }
			javaMethod{ "setSplitTrack(${bool(it)})" }
		}
		attribute{
			field = "showText"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			kotlinMethod { "showText = ${bool(it)}" }
			javaMethod{ "setShowText(${bool(it)})" }
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
	/**
	 * 解析Switch属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
