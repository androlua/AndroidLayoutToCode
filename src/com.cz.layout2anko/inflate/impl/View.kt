package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.AttributeConvert
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodTextConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------View all attributes---------------
 * @attr ref android.R.styleable#View_alpha
 * @attr ref android.R.styleable#View_background
 * @attr ref android.R.styleable#View_clickable
 * @attr ref android.R.styleable#View_contentDescription
 * @attr ref android.R.styleable#View_drawingCacheQuality
 * @attr ref android.R.styleable#View_duplicateParentState
 * @attr ref android.R.styleable#View_id
 * @attr ref android.R.styleable#View_requiresFadingEdge
 * @attr ref android.R.styleable#View_fadeScrollbars
 * @attr ref android.R.styleable#View_fadingEdgeLength
 * @attr ref android.R.styleable#View_filterTouchesWhenObscured
 * @attr ref android.R.styleable#View_fitsSystemWindows
 * @attr ref android.R.styleable#View_isScrollContainer
 * @attr ref android.R.styleable#View_focusable
 * @attr ref android.R.styleable#View_focusableInTouchMode
 * @attr ref android.R.styleable#View_focusedByDefault
 * @attr ref android.R.styleable#View_hapticFeedbackEnabled
 * @attr ref android.R.styleable#View_keepScreenOn
 * @attr ref android.R.styleable#View_keyboardNavigationCluster
 * @attr ref android.R.styleable#View_layerType
 * @attr ref android.R.styleable#View_layoutDirection
 * @attr ref android.R.styleable#View_longClickable
 * @attr ref android.R.styleable#View_minHeight
 * @attr ref android.R.styleable#View_minWidth
 * @attr ref android.R.styleable#View_nextClusterForward
 * @attr ref android.R.styleable#View_nextFocusDown
 * @attr ref android.R.styleable#View_nextFocusLeft
 * @attr ref android.R.styleable#View_nextFocusRight
 * @attr ref android.R.styleable#View_nextFocusUp
 * @attr ref android.R.styleable#View_onClick
 * @attr ref android.R.styleable#View_padding
 * @attr ref android.R.styleable#View_paddingHorizontal
 * @attr ref android.R.styleable#View_paddingVertical
 * @attr ref android.R.styleable#View_paddingBottom
 * @attr ref android.R.styleable#View_paddingLeft
 * @attr ref android.R.styleable#View_paddingRight
 * @attr ref android.R.styleable#View_paddingTop
 * @attr ref android.R.styleable#View_paddingStart
 * @attr ref android.R.styleable#View_paddingEnd
 * @attr ref android.R.styleable#View_saveEnabled
 * @attr ref android.R.styleable#View_rotation
 * @attr ref android.R.styleable#View_rotationX
 * @attr ref android.R.styleable#View_rotationY
 * @attr ref android.R.styleable#View_scaleX
 * @attr ref android.R.styleable#View_scaleY
 * @attr ref android.R.styleable#View_scrollX
 * @attr ref android.R.styleable#View_scrollY
 * @attr ref android.R.styleable#View_scrollbarSize
 * @attr ref android.R.styleable#View_scrollbarStyle
 * @attr ref android.R.styleable#View_scrollbars
 * @attr ref android.R.styleable#View_scrollbarDefaultDelayBeforeFade
 * @attr ref android.R.styleable#View_scrollbarFadeDuration
 * @attr ref android.R.styleable#View_scrollbarTrackHorizontal
 * @attr ref android.R.styleable#View_scrollbarThumbHorizontal
 * @attr ref android.R.styleable#View_scrollbarThumbVertical
 * @attr ref android.R.styleable#View_scrollbarTrackVertical
 * @attr ref android.R.styleable#View_scrollbarAlwaysDrawHorizontalTrack
 * @attr ref android.R.styleable#View_scrollbarAlwaysDrawVerticalTrack
 * @attr ref android.R.styleable#View_stateListAnimator
 * @attr ref android.R.styleable#View_transitionName
 * @attr ref android.R.styleable#View_soundEffectsEnabled
 * @attr ref android.R.styleable#View_tag
 * @attr ref android.R.styleable#View_textAlignment
 * @attr ref android.R.styleable#View_textDirection
 * @attr ref android.R.styleable#View_transformPivotX
 * @attr ref android.R.styleable#View_transformPivotY
 * @attr ref android.R.styleable#View_translationX
 * @attr ref android.R.styleable#View_translationY
 * @attr ref android.R.styleable#View_translationZ
 * @attr ref android.R.styleable#View_visibility
 * @attr ref android.R.styleable#View_theme
 *
 */
open class View {

	val attributes= mutableListOf<AttributeConvert>()

	fun convert(element:Element,level:Int):String{
		val out=StringBuilder()
		val tab="".padEnd(level,'\t')
		inflateAttributes(element)
		attributes.forEach { out.append("$tab$it") }
		return out.toString()
	}

	/**
	 * 获得控件映射名称
	 */
	open fun getViewName()="view"

	/**
	 * 获得控件带样式映射名称
	 */
	open fun getThemeViewName()="themeView"

	/**
	 * 解析View属性集,并返回解析后的anko代码
	 */
	open fun inflateAttributes(element:Element){
		element.attributes.forEach {
			val name=it.name
			val value = it.value
			when(name){
				"alpha"->attributes.add(ViewConvertItem("alpha",dimen(value)))
				"background"->attributes.add(ViewConvertItem("backgroundResource",resourceRef(value)))
				"clickable"->attributes.add(ViewConvertItem("isClickable","setClickable",bool(value)))
				"contentDescription"->attributes.add(ViewConvertItem("contentDescription",string(value)))
				"drawingCacheQuality"->attributes.add(ViewConvertItem("drawingCacheQuality",drawingCacheQuality(value)))
				"duplicateParentState"->attributes.add(ViewConvertItem("isDuplicateParentStateEnabled","setDuplicateParentStateEnabled",bool(value)))
				"id"->attributes.add(ViewConvertItem("id",resourceRef(value)))
				"requiresFadingEdge"->attributes.add(ViewConvertItem(name,value,false))
				"fadeScrollbars"->attributes.add(ViewConvertItem("isScrollbarFadingEnabled","setScrollbarFadingEnabled",bool(value)))
				"fadingEdgeLength"->attributes.add(ViewMethodConvertItem("setFadingEdgeLength(${dimen(value)}"))
				"filterTouchesWhenObscured"->attributes.add(ViewConvertItem("filterTouchesWhenObscured",bool(value)))
				"fitsSystemWindows"->attributes.add(ViewConvertItem("fitsSystemWindows",bool(value)))
				"isScrollContainer"->attributes.add(ViewConvertItem("isScrollContainer","setScrollContainer",bool(value)))
				"focusable"->attributes.add(ViewConvertItem("isFocusable","setFocusable",bool(value)))
				"focusableInTouchMode"->attributes.add(ViewConvertItem("isFocusableInTouchMode","setFocusableInTouchMode",bool(value)))
				"focusedByDefault"->attributes.add(ViewConvertItem(name,value,false))
				"hapticFeedbackEnabled"->attributes.add(ViewConvertItem("isHapticFeedbackEnabled","setHapticFeedbackEnabled",bool(value)))
				"keepScreenOn"->attributes.add(ViewConvertItem("keepScreenOn",bool(value)))
				"keyboardNavigationCluster"->attributes.add(ViewConvertItem(name,value,false))
				"layerType"->attributes.add(ViewMethodConvertItem("setLayerType(${layerType(value)}, null)"))
				"layoutDirection"->attributes.add(ViewConvertItem("layoutDirection",layoutDirection(value),17))
				"longClickable"->attributes.add(ViewConvertItem("isLongClickable","setLongClickable",bool(value)))
				"minWidth"->attributes.add(ViewConvertItem("minimumWidth",dimen(value)))
				"minHeight"->attributes.add(ViewConvertItem("minimumHeight",dimen(value)))
				"nextClusterForward"->attributes.add(ViewConvertItem(name,value,false))
				"nextFocusDown"->attributes.add(ViewConvertItem("nextFocusDownId","setNextFocusDownId",resourceRef(value)))
				"nextFocusLeft"->attributes.add(ViewConvertItem("nextFocusLeftId","setNextFocusLeftId",resourceRef(value)))
				"nextFocusRight"->attributes.add(ViewConvertItem("nextFocusRightId","setNextFocusRightId",resourceRef(value)))
				"nextFocusUp"->attributes.add(ViewConvertItem("nextFocusUpId","setNextFocusUpId",resourceRef(value)))
				"onClick"->{
					attributes.add(ViewMethodTextConvertItem("setOnClickListener{\n\tvalue(this)\n}"))
				}
				"padding"->attributes.add(ViewConvertItem("padding",dimen(value)))
				"paddingHorizontal"->attributes.add(ViewConvertItem("paddingHorizontal",dimen(value)))
				"paddingVertical"->attributes.add(ViewConvertItem("paddingVertical",dimen(value)))
				"paddingBottom"->attributes.add(ViewConvertItem("bottomPadding",dimen(value)))
				"paddingLeft"->attributes.add(ViewConvertItem("leftPadding",dimen(value)))
				"paddingRight"->attributes.add(ViewConvertItem("rightPadding",dimen(value)))
				"paddingTop"->attributes.add(ViewConvertItem("topPadding",dimen(value)))
				"paddingStart","paddingEnd"->attributes.add(ViewConvertItem(name,value,false))
				"saveEnabled"->attributes.add(ViewConvertItem("isSaveEnabled","setSaveEnabled",bool(value)))
				"rotation"->attributes.add(ViewConvertItem("rotation","setRotation",dimen(value)))
				"rotationX"->attributes.add(ViewConvertItem("rotationX","setRotationX",dimen(value)))
				"rotationY"->attributes.add(ViewConvertItem("rotationY","setRotationY",dimen(value)))
				"scaleX"->attributes.add(ViewConvertItem("scaleX","setScaleX",dimen(value)))
				"scaleY"->attributes.add(ViewConvertItem("scaleY","setScaleY",dimen(value)))
				"scrollX"->attributes.add(ViewConvertItem("scrollX","setScrollX",dimen(value)))
				"scrollY"->attributes.add(ViewConvertItem("scrollY","setScrollY",dimen(value)))
				"scrollbarSize"->attributes.add(ViewConvertItem("scrollBarSize",dimen(value),16))
				"scrollbarStyle"->attributes.add(ViewConvertItem("scrollBarStyle",scrollBarStyle(value)))
				"scrollbars"->attributes.add(ViewConvertItem(name,value,false))
				"scrollbarDefaultDelayBeforeFade"->attributes.add(ViewConvertItem("scrollBarDefaultDelayBeforeFade",dimen(value),16))
				"scrollbarFadeDuration"-> attributes.add(ViewConvertItem("scrollBarFadeDuration",dimen(value),16))
				"scrollbarTrackHorizontal",
				"scrollbarThumbHorizontal",
				"scrollbarThumbVertical",
				"scrollbarTrackVertical",
				"scrollbarAlwaysDrawHorizontalTrack",
				"scrollbarAlwaysDrawVerticalTrack"->attributes.add(ViewConvertItem(name,value,false))
				"stateListAnimator"->{
					val out=StringBuilder()
					out.append("\tval drawable=ContextCompat.getDrawable(context,${resourceRef(value)})")
					out.append("\tif(drawable is StateListAnimator){")
					out.append("\t\tstateListAnimator=drawable")
					out.append("\t}")
					attributes.add(ViewMethodTextConvertItem(out.toString(),21))
				}
				"transitionName"->attributes.add(ViewConvertItem("transitionName",resource(value),21))
				"soundEffectsEnabled"->attributes.add(ViewConvertItem("isSoundEffectsEnabled","setSoundEffectsEnabled",value))
				"tag"->attributes.add(ViewConvertItem("tag",string(value)))
				"textAlignment"->attributes.add(ViewConvertItem("textAlignment",textAlignment(value)))
				"textDirection"->attributes.add(ViewConvertItem("textDirection",textDirection(value)))
				"transformPivotX"->attributes.add(ViewConvertItem("transformPivotX",dimen(value)))
				"transformPivotY"->attributes.add(ViewConvertItem("transformPivotY",dimen(value)))
				"translationX"->attributes.add(ViewConvertItem("translationX",dimen(value)))
				"translationY"->attributes.add(ViewConvertItem("translationY",dimen(value)))
				"translationZ"->attributes.add(ViewConvertItem("translationZ",dimen(value)))
				"visibility"->attributes.add(ViewConvertItem("visibility",visibility(value)))
				"theme"->attributes.add(ViewConvertItem("theme",resourceRef(value)))
			}
		}
	}

}
