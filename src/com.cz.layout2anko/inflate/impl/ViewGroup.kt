package com.cz.layout2anko.inflate.impl

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
	 * 解析ViewGroup属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"clipChildren"->{
				
				}
				"clipToPadding"->{
				
				}
				"layoutAnimation"->{
				
				}
				"animationCache"->{
				
				}
				"persistentDrawingCache"->{
				
				}
				"alwaysDrawnWithCache"->{
				
				}
				"addStatesFromChildren"->{
				
				}
				"descendantFocusability"->{
				
				}
				"animateLayoutChanges"->{
				
				}
				"splitMotionEvents"->{
				
				}
				"layoutMode"->{
				
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

		val attributes= mutableListOf<String>()

		/**
		 * Gravity.BOTTOM
		 * Gravity.CENTER
		 * Gravity.CENTER_HORIZONTAL
		 * Gravity.CENTER_VERTICAL
		 * Gravity.CLIP_HORIZONTAL
		 * Gravity.CLIP_VERTICAL
		 * Gravity.END
		 * Gravity.FILL
		 * Gravity.FILL_HORIZONTAL
		 * Gravity.FILL_VERTICAL
		 * Gravity.LEFT
		 * Gravity.RIGHT
		 * Gravity.START
		 * Gravity.TOP
		 */
		fun gravity(gravity:String)=when(gravity){
			"bottom"->"Gravity.BOTTOM"
			"center"->"Gravity.CENTER"
			"center_horizontal"->"Gravity.CENTER_HORIZONTAL"
			"center_vertical"->"Gravity.CENTER_VERTICAL"
			"clip_horizontal"->"Gravity.CLIP_HORIZONTAL"
			"clip_vertical"->"Gravity.CLIP_VERTICAL"
			"end"->"Gravity.END"
			"fill"->"Gravity.FILL"
			"fill_horizontal"->"Gravity.FILL_HORIZONTAL"
			"fill_vertical"->"Gravity.FILL_VERTICAL"
			"right"->"Gravity.RIGHT"
			"start"->"Gravity.START"
			"top"->"Gravity.TOP"
			else->"Gravity.LEFT"
		}
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		open fun inflateAttributes(element:Element){
			element.attributes.forEach {
				val name=it.name
				when(name){
					"layout_height"->{
					
					}
					"layout_width"->{
					
					}
				}
			}
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
				when(name){
					"layout_margin"->{
					
					}
					"layout_marginHorizontal"->{
					
					}
					"layout_marginVertical"->{
					
					}
					"layout_marginLeft"->{
					
					}
					"layout_marginTop"->{
					
					}
					"layout_marginRight"->{
					
					}
					"layout_marginBottom"->{
					
					}
					"layout_marginStart"->{
					
					}
					"layout_marginEnd"->{
					
					}
				}
			}
		}

	}
	
}
