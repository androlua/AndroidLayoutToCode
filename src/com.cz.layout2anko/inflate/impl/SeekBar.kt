package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.resource
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------SeekBar all attributes---------------
 * @attr ref android.R.styleable#SeekBar_thumb
 *
 */
open class SeekBar : AbsSeekBar() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="seekBar"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedSeekBar"
	/**
	 * 解析SeekBar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"thumb"->attributes.add(ViewConvertItem("thumb",resource(value)))
			}
		}
	}

}
