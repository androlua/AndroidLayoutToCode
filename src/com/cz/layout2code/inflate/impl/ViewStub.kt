package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.id
import com.cz.layout2code.inflate.item.ViewAttributeItem
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resource
import com.cz.layout2code.inflate.resourceRef
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
	init {
		attribute{
			field = "id"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "id = ${id(it)}" }
			javaMethod{ "setId(${id(it)})" }
		}
		attribute{
			field = "layout"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "layoutResource = ${resourceRef(it)}" }
			javaMethod{ "setLayoutResource(${resourceRef(it)})" }
		}
		attribute{
			field = "inflatedId"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "inflatedId = ${id(it)}" }
			javaMethod{ "setInflatedId(${id(it)})" }
		}
	}
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
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
