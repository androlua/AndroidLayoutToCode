package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
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
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="toolbar"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedToolbar"
	/**
	 * 解析Toolbar属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"buttonGravity"->{
				
				}
				"collapseContentDescription"->{
				
				}
				"collapseIcon"->{
				
				}
				"contentInsetEnd"->{
				
				}
				"contentInsetLeft"->{
				
				}
				"contentInsetRight"->{
				
				}
				"contentInsetStart"->{
				
				}
				"contentInsetStartWithNavigation"->{
				
				}
				"contentInsetEndWithActions"->{
				
				}
				"gravity"->{
				
				}
				"logo"->{
				
				}
				"logoDescription"->{
				
				}
				"maxButtonHeight"->{
				
				}
				"navigationContentDescription"->{
				
				}
				"navigationIcon"->{
				
				}
				"popupTheme"->{
				
				}
				"subtitle"->{
				
				}
				"subtitleTextAppearance"->{
				
				}
				"subtitleTextColor"->{
				
				}
				"title"->{
				
				}
				"titleMargin"->{
				
				}
				"titleMarginBottom"->{
				
				}
				"titleMarginEnd"->{
				
				}
				"titleMarginStart"->{
				
				}
				"titleMarginTop"->{
				
				}
				"titleTextAppearance"->{
				
				}
				"titleTextColor"->{
				
				}
			}
		}
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
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				when(name){
					"layout_gravity"->{
					
					}
				}
			}
		}

	}
	
}
