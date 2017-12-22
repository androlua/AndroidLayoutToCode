package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------HorizontalScrollView all attributes---------------
 * @attr ref android.R.styleable#HorizontalScrollView_fillViewport
 *
 */
open class HorizontalScrollView : FrameLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="horizontalScrollView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedHorizontalScrollView"
	/**
	 * 解析HorizontalScrollView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"fillViewport"->attributes.add(ViewConvertItem("fillViewport",bool(value)))
			}
		}
	}

}
