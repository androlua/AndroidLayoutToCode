package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.id
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.resourceRef
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewStub all attributes---------------
 * @attr ref android.R.styleable#ViewStub_inflatedId
 * @attr ref android.R.styleable#ViewStub_layout
 *
 */
open class ViewStub : View() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewStub"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewStub"
	/**
	 * 解析ViewStub属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"inflatedId"->attributes.add(ViewConvertItem("inflatedId",id(value)))
				"layout"->attributes.add(ViewConvertItem("layoutResource",resourceRef(value)))
			}
		}
	}

}
