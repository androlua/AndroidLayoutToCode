package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.dimen
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.resourceRef
import com.cz.layout2anko.inflate.string
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
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"textOn"->attributes.add(ViewConvertItem("textOn",string(value)))
				"textOff"->attributes.add(ViewConvertItem("textOff",string(value)))
				"switchMinWidth"-> attributes.add(ViewConvertItem("switchMinWidth",dimen(value),16))
				"switchPadding"->attributes.add(ViewConvertItem("switchPadding",dimen(value),16))
				"switchTextAppearance"->attributes.add(ViewMethodConvertItem("setSwitchTextAppearance(context,${resourceRef(value)}"))
				"thumb"->attributes.add(ViewMethodConvertItem("setSwitchTextAppearance(context,${resourceRef(value)})"))
				"thumbTextPadding"->attributes.add(ViewMethodConvertItem("setThumbResource(${resourceRef(value)})",16))
				"track"->attributes.add(ViewMethodConvertItem("setTrackResource(${resourceRef(value)})",16))
			}
		}
	}

}
