package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------Toolbar all attributes---------------
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
			kotlinMethod { "setTitleTextAppearance(context,${resource(it)})" }
			javaMethod{ "setTitleTextAppearance(context,${resource(it)})" }
		}
		attribute{
			field = "subtitleTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setSubtitleTextAppearance(context,${resource(it)})" }
			javaMethod{ "setSubtitleTextAppearance(context,${resource(it)})" }
		}
		attribute{
			field = "title"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "title = ${string(it)}" }
			javaMethod{ "setTitle(${string(it)})" }
		}
		attribute{
			field = "subtitle"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "subtitle = ${string(it)}" }
			javaMethod{ "setSubtitle(${string(it)})" }
		}
		uselessAttribute("gravity")
		attribute{
			field = "titleMargin"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "titleMargin = ${dimen(it)}" }
			javaMethod{ "setTitleMargin(${dimen(it)})" }
		}
		attribute{
			field = "titleMarginStart"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "titleMarginStart = ${dimen(it)}" }
			javaMethod{ "setTitleMarginStart(${dimen(it)})" }
		}
		attribute{
			field = "titleMarginEnd"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "titleMarginEnd = ${dimen(it)}" }
			javaMethod{ "setTitleMarginEnd(${dimen(it)})" }
		}
		attribute{
			field = "titleMarginTop"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "titleMarginTop = ${dimen(it)}" }
			javaMethod{ "setTitleMarginTop(${dimen(it)})" }
		}
		attribute{
			field = "titleMarginBottom"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "titleMarginBottom = ${dimen(it)}" }
			javaMethod{ "setTitleMarginBottom(${dimen(it)})" }
		}
		attribute{
			field = "contentInsetStart"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "contentInsetStart = ${dimen(it)}" }
			javaMethod{ "setContentInsetStart(${dimen(it)})" }
		}
		attribute{
			field = "contentInsetEnd"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "contentInsetEnd = ${dimen(it)}" }
			javaMethod{ "setContentInsetEnd(${dimen(it)})" }
		}
		attribute{
			field = "contentInsetLeft"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "contentInsetLeft = ${dimen(it)}" }
			javaMethod{ "setContentInsetLeft(${dimen(it)})" }
		}
		attribute{
			field = "contentInsetRight"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "contentInsetRight = ${dimen(it)}" }
			javaMethod{ "setContentInsetRight(${dimen(it)})" }
		}
		attribute{
			field = "contentInsetStartWithNavigation"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "contentInsetStartWithNavigation = ${dimen(it)}" }
			javaMethod{ "setContentInsetStartWithNavigation(${dimen(it)})" }
		}
		attribute{
			field = "contentInsetEndWithActions"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "contentInsetEndWithActions = ${dimen(it)}" }
			javaMethod{ "setContentInsetEndWithActions(${dimen(it)})" }
		}
		uselessAttribute("maxButtonHeight")
		uselessAttribute("navigationButtonStyle")
		uselessAttribute("buttonGravity")
		uselessAttribute("collapseIcon")
		uselessAttribute("collapseContentDescription")
		attribute{
			field = "popupTheme"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "popupTheme = ${resourceRef(it)}" }
			javaMethod{ "setPopupTheme(${resourceRef(it)})" }
		}
		attribute{
			field = "navigationIcon"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "navigationIcon = ${resource(it)}" }
			javaMethod{ "setNavigationIcon(${resource(it)})" }
		}
		attribute{
			field = "navigationContentDescription"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "navigationContentDescription = ${string(it)}" }
			javaMethod{ "setNavigationContentDescription(${string(it)})" }
		}
		attribute{
			field = "logo"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "logo = ${resource(it)}" }
			javaMethod{ "setLogo(${resource(it)})" }
		}
		attribute{
			field = "logoDescription"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "logoDescription = ${string(it)}" }
			javaMethod{ "setLogoDescription(${string(it)})" }
		}
		attribute{
			field = "titleTextColor"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setTitleTextColor(${color(it)})" }
			javaMethod{ "setTitleTextColor(${color(it)})" }
		}
		attribute{
			field = "subtitleTextColor"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setSubtitleTextColor(${color(it)})" }
			javaMethod{ "setSubtitleTextColor(${color(it)})" }
		}
	}

	override fun getViewName()="toolbar"

	override fun getThemeViewName()="themedToolbar"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#Toolbar_LayoutParams_layout_gravity
	 *
	 */
	open class LayoutParams : ActionBar.LayoutParams() {
		init {
			attribute{
				field = "layout_gravity"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "layout_gravity = ${resource(it)}" }
				javaMethod{ "setLAYOUT_GRAVITYayout_gravity(${resource(it)})" }
			}
		}
		
		/**
		 * 解析LayoutParams属性集
		 */
		override fun inflateAttributes(element: Element){
			super.inflateAttributes(element)
			element.attributes.forEach { addAttributeItems(it.name,it.value) }
		}
	}
	
}
