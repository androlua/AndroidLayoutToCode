package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.expression.value.ClassFieldExpression
import com.cz.layout2code.inflate.expression.value.DefineLayoutParamsExpression
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.inflate.expression.value.StringValueExpression
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.prefs.AttributeStyle
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewGroup all expressions---------------
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
			expression {
				import { mutableListOf(ImportItem("android.animation.LayoutTransition")) }
				javaExpression { _,value->
					if(value.toBoolean()) "" else  "setLayoutTransition(new LayoutTransition())"
				}
				kotlinExpression { _,value->
					if(value.toBoolean()) "" else  "layoutTransition = LayoutTransition()"
				}
			}
		}
		attribute{
			field = "clipChildren"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("clipChildren"){bool(it)}
		}
		attribute{
			field = "clipToPadding"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("clipToPadding"){bool(it)}
		}
		attribute{
			field = "layoutAnimation"
			attrType = arrayOf(AttrType.REFERENCE)
			property("layoutAnimation"){resource(it)}
		}
		attribute{
			field = "animationCache"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setAnimationCacheEnabled"
				kotlin="isAnimationCacheEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "persistentDrawingCache"
			attrType = arrayOf(AttrType.FLAG)
			property("persistentDrawingCache"){ persistentDrawingCache(it) }
		}
		attribute{
			field = "alwaysDrawnWithCache"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setAlwaysDrawnWithCacheEnabled"
				kotlin="isAlwaysDrawnWithCacheEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "addStatesFromChildren"
			attrType = arrayOf(AttrType.BOOLEAN)
			method("setAddStatesFromChildren"){bool(it)}
		}
		attribute{
			field = "descendantFocusability"
			attrType = arrayOf(AttrType.FLAG)
			property("descendantFocusability"){descendantFocusability(it)}
		}
		attribute{
			field = "touchscreenBlocksFocus"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			property("touchscreenBlocksFocus"){bool(it)}
		}
		attribute{
			field = "splitMotionEvents"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setMotionEventSplittingEnabled"
				kotlin="isMotionEventSplittingEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "layoutMode"
			attrType = arrayOf(AttrType.FLAG)
			property("layoutMode"){layoutMode(it)}
		}
		attribute{
			field = "transitionGroup"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setTransitionGroup"
				kotlin="isTransitionGroup"
				value { bool(it) }
			}
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
	 * ---------------LayoutParams all expressions---------------
	 * @attr ref android.R.styleable#ViewGroup_Layout_layout_height
	 * @attr ref android.R.styleable#ViewGroup_Layout_layout_width
	 *
	 */
	open class LayoutParams {
		val expressions= mutableListOf<ElementExpression>()
		val viewStyleItems = mutableMapOf<String,AttributeStyle>()

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

		protected fun attribute(action: AttributeStyle.()->Unit){
			val attributeStyle = AttributeStyle().apply(action)
			viewStyleItems[attributeStyle.field] = attributeStyle
		}

		/**
		 * 无用属性,不可转换,指类内部并没有暴露实现方法出来的属性
		 */
		protected fun uselessAttribute(field:String){
			val attributeStyle=AttributeStyle()
			attributeStyle.field=field
			//不可转换属性
			attributeStyle.convert=false
			viewStyleItems.put(field,attributeStyle)
		}

		/**
		 * 解析布局尺寸元素
		 */
		open fun inflateLayoutDimension(element:ViewNode,convertToJava:Boolean=true): DefineLayoutParamsExpression {
			val widthElement=element.attributes.find { it.name=="layout_width" }
			val heightElement=element.attributes.find { it.name=="layout_height" }
			val widthDimension=layoutDimension(widthElement?.value)
			val heightDimension=layoutDimension(heightElement?.value)
			applyAttributes(widthElement,heightElement)
			//添加布局基本配置集
			return DefineLayoutParamsExpression(widthDimension,heightDimension)
		}

		/**
		 * 解析LayoutParams属性集,并返回解析后的转换对象
		 */
		open fun inflateLayoutAttributes(element:ViewNode):MutableList<ElementExpression>{
			//检索到layout系统属性
			val converterItems = mutableListOf<ElementExpression>()
			//添加其他属性集
			element.attributes.forEach{
				val findItem= viewStyleItems[it.name]
				if(null!=findItem){
					//添加控件配置属性
					applyAttributes(it)
					//回调对象取值
					val newExpression=findItem.callback(it.value)
					expressions.add(newExpression)
				}
			}
			return converterItems
		}

		/**
		 * 应用一个属性
		 */
		private inline fun applyAttributes(vararg attributes: AttributeNode?)=attributes?.forEach { it?.isApply=true }

	}
	
	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------MarginLayoutParams all expressions---------------
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
				allProperty("margin"){dimen(it)}
			}
			attribute{
				field = "layout_marginHorizontal"
				attrType = arrayOf(AttrType.DIMENSION)
				expression {
					javaExpression {matcher,value->
						"leftMargin=${dimen(value).getJavaExpression(matcher)}\n"+
						"rightMargin=${dimen(value).getJavaExpression(matcher)}"
					}
					kotlinExpression {matcher,value->
						"leftMargin=${dimen(value).getKotlinExpression(matcher)}\n"+
						"rightMargin=${dimen(value).getKotlinExpression(matcher)}"
					}
				}
			}
			attribute{
				field = "layout_marginVertical"
				attrType = arrayOf(AttrType.DIMENSION)
				expression {
					javaExpression { matcher,value->
						"topMargin=${dimen(value).getJavaExpression(matcher)}"
						"bottomMargin=${dimen(value).getJavaExpression(matcher)}"
					}
					kotlinExpression {matcher,value->
						"topMargin=${dimen(value).getKotlinExpression(matcher)}"
						"bottomMargin=${dimen(value).getKotlinExpression(matcher)}"
					}
				}
			}
			attribute{
				field = "layout_marginLeft"
				attrType = arrayOf(AttrType.DIMENSION)
				allProperty("leftMargin"){dimen(it)}
			}
			attribute{
				field = "layout_marginTop"
				attrType = arrayOf(AttrType.DIMENSION)
				allProperty("topMargin"){dimen(it)}
			}
			attribute{
				field = "layout_marginRight"
				attrType = arrayOf(AttrType.DIMENSION)
				allProperty("rightMargin"){dimen(it)}
			}
			attribute{
				field = "layout_marginBottom"
				attrType = arrayOf(AttrType.DIMENSION)
				allProperty("bottomMargin"){dimen(it)}
			}
			attribute{
				field = "layout_marginStart"
				attrType = arrayOf(AttrType.DIMENSION)
				allProperty("startMargin"){dimen(it)}
			}
			attribute{
				field = "layout_marginEnd"
				attrType = arrayOf(AttrType.DIMENSION)
				allProperty("endMargin"){dimen(it)}
			}
		}

	}
	
}
