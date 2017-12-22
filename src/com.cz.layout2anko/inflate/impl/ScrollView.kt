package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.relativeRule
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ScrollView all attributes---------------
 * @attr ref android.R.styleable#ScrollView_fillViewport
 *
 */
open class ScrollView : FrameLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="scrollView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedScrollView"
	/**
	 * 解析ScrollView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"fillViewport"->attributes.add(ViewConvertItem("isFillViewport","setFillViewport",bool(value)))
			}
		}
	}

}
