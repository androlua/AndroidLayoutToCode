package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.element.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.prefs.AttributeStyle
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
	init {
		attribute{
			field = "animateLayoutChanges"
			attrType = arrayOf(AttrType.BOOLEAN)
			importList= arrayOf("android.animation.LayoutTransition")
			kotlinMethod { if("true"!=it) "" else  "layoutTransition = LayoutTransition()" }
			javaMethod{ if("true"!=it) "" else  "setLayoutTransition(LayoutTransition())"  }
		}
		attribute{
			field = "clipChildren"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "clipChildren = ${bool(it)}" }
			javaMethod{ "setClipChildren(${bool(it)})" }
		}
		attribute{
			field = "clipToPadding"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "clipToPadding = ${bool(it)}" }
			javaMethod{ "setClipToPadding(${bool(it)})" }
		}
		attribute{
			field = "layoutAnimation"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "layoutAnimation = ${resource(it)}" }
			javaMethod{ "setLayoutAnimation(${resource(it)})" }
		}
		attribute{
			field = "animationCache"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isAnimationCacheEnabled = ${bool(it)}" }
			javaMethod{ "setAnimationCacheEnabled(${bool(it)})" }
		}
		attribute{
			field = "persistentDrawingCache"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "persistentDrawingCache = ${persistentDrawingCache(it)}" }
			javaMethod{ "setPersistentDrawingCache(${persistentDrawingCache(it)})" }
		}
		attribute{
			field = "alwaysDrawnWithCache"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isAlwaysDrawnWithCacheEnabled = ${bool(it)}" }
			javaMethod{ "setAlwaysDrawnWithCacheEnabled(${bool(it)})" }
		}
		attribute{
			field = "addStatesFromChildren"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setAddStatesFromChildren(${bool(it)})" }
			javaMethod{ "setAddStatesFromChildren(${bool(it)})" }
		}
		attribute{
			field = "descendantFocusability"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "descendantFocusability = ${descendantFocusability(it)}" }
			javaMethod{ "setDescendantFocusability(${descendantFocusability(it)})" }
		}
		attribute{
			field = "touchscreenBlocksFocus"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			kotlinMethod { "touchscreenBlocksFocus = ${bool(it)}" }
			javaMethod{ "setTouchscreenBlocksFocus(${bool(it)})" }
		}
		attribute{
			field = "splitMotionEvents"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isMotionEventSplittingEnabled = ${bool(it)}" }
			javaMethod{ "setMotionEventSplittingEnabled(${bool(it)})" }
		}
		attribute{
			field = "layoutMode"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "layoutMode = ${layoutMode(it)}" }
			javaMethod{ "setLayoutMode(${layoutMode(it)})" }
		}
		attribute{
			field = "transitionGroup"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isTransitionGroup = ${bool(it)}" }
			javaMethod{ "setTransitionGroup(${bool(it)})" }
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewGroup"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewGroup"

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
		val viewStyleItems = mutableMapOf<String,MutableList<AttributeStyle>>()
		val attributes= mutableListOf<ElementConvert>()

		init {
			//直接合并处理
//			attribute{
//				field = "layout_width"
//				attrType = arrayOf(AttrType.REFERENCE)
//				kotlinMethod { "width = ${resource(it)}" }
//				javaMethod{ "layoutParams.width = ${resource(it)}" }
//			}
//			attribute{
//				field = "layout_height"
//				attrType = arrayOf(AttrType.REFERENCE)
//				kotlinMethod { "height = ${resource(it)}" }
//				javaMethod{ "layoutParams.height = ${resource(it)}" }
//			}
		}

		private fun getSimpleClassName(): String {
			val className = this::class.java.name
			val simpleClassName = className.substring(className.lastIndexOf(".") + 1)
			return simpleClassName
		}

		protected fun attribute(action: AttributeStyle.()->Unit){
			val className=this::class.java.name
			val simpleClassName=className.substring(className.lastIndexOf(".")+1)
			val items=viewStyleItems.getOrPut(simpleClassName) { mutableListOf<AttributeStyle>()}
			items.add(AttributeStyle().apply(action))
		}

		/**
		 * 无用属性,不可转换,指类内部并没有暴露实现方法出来的属性
		 */
		protected fun uselessAttribute(field:String){
			val simpleClassName=getSimpleClassName()
			val items=viewStyleItems.getOrPut(simpleClassName) { mutableListOf<AttributeStyle>()}
			val attributeStyle=AttributeStyle()
			attributeStyle.field=field
			//不可转换属性
			attributeStyle.convert=false
			items.add(attributeStyle)
		}

		/**
		 * 根据系统配置属性,添加到属性集
		 */
		protected fun addAttributeItems(name:String,value:String){
			val simpleClassName = getSimpleClassName()
			val items= viewStyleItems[simpleClassName]
			val findItem=items?.find { it.field==name }
			if(null!=findItem){
				//添加控件配置属性
				attributes.add(ViewAttributeItem(findItem,value))
			}
		}

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
		init {
			attribute{
				field = "layout_margin"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "margin=${dimen(it)}" }
				javaMethod{ "layoutParams.margin=${dimen(it)}" }
			}
			attribute{
				field = "layout_marginHorizontal"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod {
					"leftMargin=${dimen(it)}"
					"rightMargin=${dimen(it)}"
				}
				javaMethod{
					"layoutParams.leftMargin=${dimen(it)}"
					"layoutParams.rightMargin=${dimen(it)}"
				}
			}
			attribute{
				field = "layout_marginVertical"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod {
					"topMargin=${dimen(it)}"
					"bottomMargin=${dimen(it)}"
				}
				javaMethod{
					"layoutParams.topMargin=${dimen(it)}"
					"layoutParams.bottomMargin=${dimen(it)}"
				}
			}
			attribute{
				field = "layout_marginLeft"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "leftMargin=${dimen(it)}" }
				javaMethod{ "layoutParams.leftMargin=${dimen(it)}" }
			}
			attribute{
				field = "layout_marginTop"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "topMargin=${dimen(it)}" }
				javaMethod{ "layoutParams.topMargin=${dimen(it)}" }
			}
			attribute{
				field = "layout_marginRight"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "rightMargin=${dimen(it)}" }
				javaMethod{ "layoutParams.rightMargin=${dimen(it)}" }
			}
			attribute{
				field = "layout_marginBottom"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "bottomMargin=${dimen(it)}" }
				javaMethod{ "layoutParams.bottomMargin=${dimen(it)}" }
			}
			attribute{
				field = "layout_marginStart"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "startMargin=${dimen(it)}" }
				javaMethod{ "layoutParams.startMargin=${dimen(it)}" }
			}
			attribute{
				field = "layout_marginEnd"
				attrType = arrayOf(AttrType.DIMENSION)
				kotlinMethod { "endMargin=${dimen(it)}" }
				javaMethod{ "layoutParams.endMargin=${dimen(it)}" }
			}
		}
		/**
		 * 解析MarginLayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach { addAttributeItems(it.name,it.value) }
		}

	}
	
}
