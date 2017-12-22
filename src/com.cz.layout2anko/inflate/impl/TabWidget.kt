package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.resource
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TabWidget all attributes---------------
 * @attr ref android.R.styleable#TabWidget_divider
 * @attr ref android.R.styleable#TabWidget_tabStripEnabled
 * @attr ref android.R.styleable#TabWidget_tabStripLeft
 * @attr ref android.R.styleable#TabWidget_tabStripRight
 *
 */
open class TabWidget : LinearLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="tabWidget"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTabWidget"
	/**
	 * 解析TabWidget属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"divider"->attributes.add(ViewMethodConvertItem("setDividerDrawable(${resource(value)})"))
				"tabStripEnabled"->attributes.add(ViewConvertItem("isStripEnabled","setStripEnabled",bool(value)))
				"tabStripLeft","tabStripRight"->attributes.add(ViewConvertItem(name,value,false))
			}
		}
	}

}
