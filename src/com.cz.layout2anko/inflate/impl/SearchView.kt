package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ImportItem
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------SearchView all attributes---------------
 * @attr ref android.R.styleable#SearchView_iconifiedByDefault
 * @attr ref android.R.styleable#SearchView_imeOptions
 * @attr ref android.R.styleable#SearchView_inputType
 * @attr ref android.R.styleable#SearchView_maxWidth
 * @attr ref android.R.styleable#SearchView_queryHint
 *
 */
open class SearchView : LinearLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="searchView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedSearchView"
	/**
	 * 解析SearchView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"iconifiedByDefault"->attributes.add(ViewMethodConvertItem("setIconifiedByDefault(${bool(value)})"))
				"imeOptions"->{
					importLists.add(ImportItem("android.view.inputmethod.EditorInfo"))
					attributes.add(ViewConvertItem("imeOptions",imeOptions(value)))
				}
				"inputType"->{
					importLists.add(ImportItem("android.text.InputType"))
					attributes.add(ViewConvertItem("inputType",inputType(value)))
				}
				"maxWidth"->attributes.add(ViewConvertItem("maxWidth",dimen(value)))
				"queryHint"->attributes.add(ViewConvertItem("queryHint",string(value)))
			}
		}
	}

}
