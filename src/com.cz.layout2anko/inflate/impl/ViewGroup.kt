package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.*
import com.cz.layout2anko.inflate.prefs.ViewStyle
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewGroup all attributes---------------
 * @attr ref android.R.styleable#ViewGroup_clipChildren
 * @attr ref android.R.styleable#ViewGroup_clipToPadding
 * @attr ref android.R.styleable#ViewGroup_layoutAnimation
 * @attr ref android.R.styleable#ViewGroup_animationCache
 * @attr ref android.R.styleable#ViewGroup_persistentDrawingCache
 * @attr ref android.R.styleable#ViewGroup_alwaysDrawnWithCache
 * @attr ref android.R.styleable#ViewGroup_addStatesFromChildren
 * @attr ref android.R.styleable#ViewGroup_descendantFocusability
 * @attr ref android.R.styleable#ViewGroup_animateLayoutChanges
 * @attr ref android.R.styleable#ViewGroup_splitMotionEvents
 * @attr ref android.R.styleable#ViewGroup_layoutMode
 *
 */
open class ViewGroup : View() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewGroup"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewGroup"
	/**
	 * 解析ViewGroup属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"clipChildren"->attributes.add(ViewConvertItem("clipChildren",bool(value)))
				"clipToPadding"->attributes.add(ViewConvertItem("clipToPadding",bool(value)))
				"layoutAnimation"->attributes.add(ViewMethodConvertItem("setLayoutAnimation(${resource(value)})"))
				"animationCache"->attributes.add(ViewConvertItem("isAnimationCacheEnabled",resource(value)))
				"persistentDrawingCache"->{
					importLists.add(ImportItem("android.view.ViewGroup"))
					attributes.add(ViewConvertItem("persistentDrawingCache",persistentDrawingCache(value)))
				}
				"alwaysDrawnWithCache"->attributes.add(ViewConvertItem("isAlwaysDrawnWithCacheEnabled","setAlwaysDrawnWithCacheEnabled",persistentDrawingCache(value)))
				"addStatesFromChildren"->attributes.add(ViewMethodConvertItem("setAddStatesFromChildren${bool(value)}"))
				"descendantFocusability"->{
					importLists.add(ImportItem("android.view.ViewGroup"))
					attributes.add(ViewConvertItem("descendantFocusability",descendantFocusability(value)))
				}
				"animateLayoutChanges"->{
//					val animateLayoutChanges = a.getBoolean(attr, false)
//					if (animateLayoutChanges) {
//						setLayoutTransition(LayoutTransition())
//					}
				}
				"splitMotionEvents"->attributes.add(ViewConvertItem("isMotionEventSplittingEnabled","setMotionEventSplittingEnabled",bool(value)))
				"layoutMode"->{
					importLists.add(ImportItem("android.view.ViewGroup"))
					attributes.add(ViewConvertItem("layoutMode",layoutMode(value)))
				}
			}
		}
	}

	open fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#ViewGroup_Layout_layout_height
	 * @attr ref android.R.styleable#ViewGroup_Layout_layout_width
	 *
	 */
	open class LayoutParams {
		val viewStyleItems = mutableListOf<ViewStyle>()
		val importList= mutableSetOf<ImportItem>()
		val attributes= mutableListOf<AttributeConvert>()

		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		open fun inflateAttributes(element:Element){
			val width=element.attributes.find { it.name=="layout_width" }?.value
			val height=element.attributes.find { it.name=="layout_height" }?.value
			val widthDimension=layoutDimension(width)?:"ViewGroup.LayoutParams.WRAP_CONTENT"
			val heightDimension=layoutDimension(height)?:"ViewGroup.LayoutParams.WRAP_CONTENT"
			attributes.add(LayoutParamsConvertItem(widthDimension,heightDimension))
		}

	}
	
	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------MarginLayoutParams all attributes---------------
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_margin
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginHorizontal
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginVertical
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginLeft
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginTop
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginRight
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginBottom
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginStart
	 * @attr ref android.R.styleable#ViewGroup_MarginLayout_layout_marginEnd
	 *
	 */
	open class MarginLayoutParams : ViewGroup.LayoutParams() {
		
		/**
		 * 解析MarginLayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				val value=it.value
				when(name){
					"layout_margin"->attributes.add(ViewConvertItem("margin",dimen(value)) { "layoutParams.margin=${dimen(value)}" })
					"layout_marginHorizontal"->{
						attributes.add(ViewConvertItem("horizontalMargin",dimen(value)) {
							"layoutParams.leftMargin=${dimen(value)}"
							"layoutParams.rightMargin=${dimen(value)}"
						})
					}
					"layout_marginVertical"->{
						attributes.add(ViewConvertItem("verticalMargin",dimen(value)) {
							"layoutParams.topMargin=${dimen(value)}"
							"layoutParams.bottomMargin=${dimen(value)}"
						})
					}
					"layout_marginLeft"->attributes.add(ViewConvertItem("leftMargin",dimen(value)) { "layoutParams.leftMargin=${dimen(value)}" })
					"layout_marginTop"->attributes.add(ViewConvertItem("topMargin",dimen(value)) { "layoutParams.topMargin=${dimen(value)}" })
					"layout_marginRight"->attributes.add(ViewConvertItem("rightMargin",dimen(value)) { "layoutParams.rightMargin=${dimen(value)}" })
					"layout_marginBottom"->attributes.add(ViewConvertItem("bottomMargin",dimen(value)) { "layoutParams.bottomMargin=${dimen(value)}" })
					"layout_marginStart"->attributes.add(ViewConvertItem("marginStart",dimen(value),17))
					"layout_marginEnd"->attributes.add(ViewConvertItem("marginEnd",dimen(value),17))
				}
			}
		}

	}
	
}
