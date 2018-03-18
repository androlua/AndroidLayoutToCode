package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------SearchView all expressions---------------
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
			method("setIconifiedByDefault"){bool(it)}
		}
		attribute{
			field = "maxWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("maxWidth"){dimen(it)}
		}
		attribute{
			field = "queryHint"
			attrType = arrayOf(AttrType.STRING)
			property("queryHint"){string(it)}
		}
		uselessAttribute("defaultQueryHint")
		attribute{
			field = "imeOptions"
			attrType = arrayOf(AttrType.FLAG)
			property("imeOptions"){imeOptions(it)}
		}
		attribute{
			field = "inputType"
			attrType = arrayOf(AttrType.FLAG)
			property("inputType"){inputType(it)}
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


}
