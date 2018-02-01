package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.prefs.AttrType
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
	init {
		uselessAttribute("layout")
		attribute{
			field = "iconifiedByDefault"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setIconifiedByDefault(${bool(it)})" }
			javaMethod{ "setIconifiedByDefault(${bool(it)})" }
		}
		attribute{
			field = "maxWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "maxWidth = ${dimen(it)}" }
			javaMethod{ "setMaxWidth(${dimen(it)})" }
		}
		attribute{
			field = "queryHint"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "queryHint = ${string(it)}" }
			javaMethod{ "setQueryHint(${string(it)})" }
		}
		uselessAttribute("defaultQueryHint")
		attribute{
			field = "imeOptions"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "imeOptions = ${imeOptions(it)}" }
			javaMethod{ "setImeOptions(${imeOptions(it)})" }
		}
		attribute{
			field = "inputType"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "inputType = ${inputType(it)}" }
			javaMethod{ "setInputType(${inputType(it)})" }
		}
		uselessAttribute("closeIcon")
		uselessAttribute("goIcon")
		uselessAttribute("searchIcon")
		uselessAttribute("searchHintIcon")
		uselessAttribute("searchHintIcon")
		uselessAttribute("voiceIcon")
		uselessAttribute("commitIcon")
		uselessAttribute("suggestionRowLayout")
		uselessAttribute("queryBackground")
		uselessAttribute("SlidingDrawer")
	}

	override fun getViewName()="searchView"

	override fun getThemeViewName()="themedSearchView"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
