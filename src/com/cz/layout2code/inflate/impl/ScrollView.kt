package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.item.ViewAttributeItem
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ScrollView all attributes---------------
 * @attr ref android.R.styleable#ScrollView_fillViewport
 *
 */
open class ScrollView : FrameLayout() {
	init {
		attribute{
			field = "fillViewport"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isFillViewport = ${bool(it)}" }
			javaMethod{ "setFillViewport(${bool(it)})" }
		}
	}
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
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
