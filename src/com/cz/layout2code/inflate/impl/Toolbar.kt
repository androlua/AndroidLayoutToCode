package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.expression.value.ClassFieldExpression
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------Toolbar all expressions---------------
 * @attr ref android.R.styleable#Toolbar_buttonGravity
 * @attr ref android.R.styleable#Toolbar_collapseContentDescription
 * @attr ref android.R.styleable#Toolbar_collapseIcon
 * @attr ref android.R.styleable#Toolbar_contentInsetEnd
 * @attr ref android.R.styleable#Toolbar_contentInsetLeft
 * @attr ref android.R.styleable#Toolbar_contentInsetRight
 * @attr ref android.R.styleable#Toolbar_contentInsetStart
 * @attr ref android.R.styleable#Toolbar_contentInsetStartWithNavigation
 * @attr ref android.R.styleable#Toolbar_contentInsetEndWithActions
 * @attr ref android.R.styleable#Toolbar_gravity
 * @attr ref android.R.styleable#Toolbar_logo
 * @attr ref android.R.styleable#Toolbar_logoDescription
 * @attr ref android.R.styleable#Toolbar_maxButtonHeight
 * @attr ref android.R.styleable#Toolbar_navigationContentDescription
 * @attr ref android.R.styleable#Toolbar_navigationIcon
 * @attr ref android.R.styleable#Toolbar_popupTheme
 * @attr ref android.R.styleable#Toolbar_subtitle
 * @attr ref android.R.styleable#Toolbar_subtitleTextAppearance
 * @attr ref android.R.styleable#Toolbar_subtitleTextColor
 * @attr ref android.R.styleable#Toolbar_title
 * @attr ref android.R.styleable#Toolbar_titleMargin
 * @attr ref android.R.styleable#Toolbar_titleMarginBottom
 * @attr ref android.R.styleable#Toolbar_titleMarginEnd
 * @attr ref android.R.styleable#Toolbar_titleMarginStart
 * @attr ref android.R.styleable#Toolbar_titleMarginTop
 * @attr ref android.R.styleable#Toolbar_titleTextAppearance
 * @attr ref android.R.styleable#Toolbar_titleTextColor
 *
 */
open class Toolbar : ViewGroup() {
	init {
		attribute{
			field = "titleTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			methods("setTitleTextAppearance"){
				mutableListOf(ClassFieldExpression("context"),resource(it))
			}
		}
		attribute{
			field = "subtitleTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			methods("setSubtitleTextAppearance"){
				mutableListOf(ClassFieldExpression("context"),resource(it))
			}
		}
		attribute{
			field = "title"
			attrType = arrayOf(AttrType.STRING)
			property("title"){string(it)}
		}
		attribute{
			field = "subtitle"
			attrType = arrayOf(AttrType.STRING)
			property("subtitle"){string(it)}
		}
		uselessAttribute("gravity")
		attribute{
			field = "titleMargin"
			attrType = arrayOf(AttrType.DIMENSION)
			property("titleMargin"){dimen(it)}
		}
		attribute{
			field = "titleMarginStart"
			attrType = arrayOf(AttrType.DIMENSION)
			property("titleMarginStart"){dimen(it)}
		}
		attribute{
			field = "titleMarginEnd"
			attrType = arrayOf(AttrType.DIMENSION)
			property("titleMarginEnd"){dimen(it)}
		}
		attribute{
			field = "titleMarginTop"
			attrType = arrayOf(AttrType.DIMENSION)
			property("titleMarginTop"){dimen(it)}
		}
		attribute{
			field = "titleMarginBottom"
			attrType = arrayOf(AttrType.DIMENSION)
			property("titleMarginBottom"){dimen(it)}
		}
		attribute{
			field = "contentInsetStart"
			attrType = arrayOf(AttrType.DIMENSION)
			property("contentInsetStart"){dimen(it)}
		}
		attribute{
			field = "contentInsetEnd"
			attrType = arrayOf(AttrType.DIMENSION)
			property("contentInsetEnd"){dimen(it)}
		}
		attribute{
			field = "contentInsetLeft"
			attrType = arrayOf(AttrType.DIMENSION)
			property("contentInsetLeft"){dimen(it)}
		}
		attribute{
			field = "contentInsetRight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("contentInsetRight"){dimen(it)}
		}
		attribute{
			field = "contentInsetStartWithNavigation"
			attrType = arrayOf(AttrType.DIMENSION)
			property("contentInsetStartWithNavigation"){dimen(it)}
		}
		attribute{
			field = "contentInsetEndWithActions"
			attrType = arrayOf(AttrType.DIMENSION)
			property("contentInsetEndWithActions"){dimen(it)}
		}
		uselessAttribute("maxButtonHeight")
		uselessAttribute("navigationButtonStyle")
		uselessAttribute("buttonGravity")
		uselessAttribute("collapseIcon")
		uselessAttribute("collapseContentDescription")
		attribute{
			field = "popupTheme"
			attrType = arrayOf(AttrType.REFERENCE)
			property("popupTheme"){resourceRef(it)}
		}
		attribute{
			field = "navigationIcon"
			attrType = arrayOf(AttrType.REFERENCE)
			property("navigationIcon"){resource(it)}
		}
		attribute{
			field = "navigationContentDescription"
			attrType = arrayOf(AttrType.STRING)
			property("navigationContentDescription"){string(it)}
		}
		attribute{
			field = "logo"
			attrType = arrayOf(AttrType.FLAG)
			property("logo"){resource(it)}
		}
		attribute{
			field = "logoDescription"
			attrType = arrayOf(AttrType.STRING)
			property("logoDescription"){string(it)}
		}
		attribute{
			field = "titleTextColor"
			attrType = arrayOf(AttrType.COLOR)
			method("setTitleTextColor"){color(it)}
		}
		attribute{
			field = "subtitleTextColor"
			attrType = arrayOf(AttrType.COLOR)
			method("setSubtitleTextColor"){color(it)}
		}
	}

	override fun getViewName()="toolbar"

	override fun getThemeViewName()="themedToolbar"

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all expressions---------------
	 * @attr ref android.R.styleable#Toolbar_LayoutParams_layout_gravity
	 *
	 */
	open class LayoutParams : ActionBar.LayoutParams() {
		init {
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				allProperty("gravity"){ resource(it) }
			}
		}
	}
	
}
