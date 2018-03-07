package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.element.*
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.prefs.AttributeStyle

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
	//控件引用样式集
	val viewStyleItems = mutableMapOf<String,AttributeStyle>()
	//常规元素转换对象
	val attributes= mutableListOf<ElementConvert>()
	//父容器属性转换对象
	var layoutParamsConvert:LayoutParamsConvertItem?=null
	//是否为compat v7包内控件
	var isCompatView=false
//		set(value) {
//			if(value){
//				importItems.add(ImportItem("org.jetbrains.anko.${getThemeViewName()}"))//v7
//			} else {
//				importItems.add(ImportItem("android.widget.${getViewName()}"))//系统控件
//			}
//		}

	init {
		//当前对象
		attribute{
			field = "accessibilityLiveRegion"
			attrType = arrayOf(AttrType.ENUM)
			sdk=19
			kotlinMethod { "accessibilityLiveRegion = ${accessibilityLiveRegion(it)}" }
			javaMethod{ "setAccessibilityLiveRegion(${accessibilityLiveRegion(it)})" }
		}
		attribute{
			field = "accessibilityTraversalAfter"
			attrType = arrayOf(AttrType.ENUM)
			sdk=22
			kotlinMethod { "accessibilityTraversalAfter = ${int(it)}" }
			javaMethod{ "setAccessibilityTraversalAfter(${int(it)})" }
		}
		attribute{
			field = "accessibilityTraversalBefore"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=22
			kotlinMethod { "accessibilityTraversalBefore = ${int(it)}" }
			javaMethod{ "setAccessibilityTraversalBefore(${int(it)})" }
		}
		attribute{
			field = "alpha"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "alpha = ${float(it)}" }
			javaMethod{ "setAlpha(${float(it)})" }
		}

		attribute{
			field = "autofillHints"
			attrType = arrayOf(AttrType.STRING, AttrType.REFERENCE)
			kotlinMethod {
				"//#View.setAutofillHints"+
				"//setAutofillHints(${string(it)})"
			}
			javaMethod{
				"//#View.setAutofillHints"+
				"//setAutofillHints(${string(it)})"
			}
		}
		attribute{
			field = "background"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			kotlinMethod { "backgroundResource = ${resourceRef(it)}" }
			javaMethod{ "setBackgroundResource(${resourceRef(it)})" }
		}
		attribute{
			field = "backgroundTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "backgroundTintList = ${colorStateList(it)}" }
			javaMethod{ "setBackgroundTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "backgroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "backgroundTintMode = ${tintMode(it)}" }
			javaMethod{ "setBackgroundTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "clickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isClickable = ${bool(it)}" }
			javaMethod{ "setClickable(${bool(it)})" }
		}
		attribute{
			field = "contentDescription"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "contentDescription = ${string(it)}" }
			javaMethod{ "setContentDescription(${string(it)})" }
		}
		attribute{
			field = "contextClickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=23
			kotlinMethod { "isContextClickable = ${bool(it)}" }
			javaMethod{ "setContextClickable(${bool(it)})" }
		}


		attribute{
			field = "defaultFocusHighlightEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=26
			kotlinMethod { "defaultFocusHighlightEnabled = ${bool(it)}" }
			javaMethod{ "setDefaultFocusHighlightEnabled(${bool(it)})" }
		}
		attribute{
			field = "drawingCacheQuality"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.view.View")
			kotlinMethod { "drawingCacheQuality = ${drawingCacheQuality(it)}" }
			javaMethod{ "setDrawingCacheQuality(${drawingCacheQuality(it)})" }
		}
		attribute{
			field = "duplicateParentState"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isDuplicateParentStateEnabled = ${bool(it)}" }
			javaMethod{ "setDuplicateParentStateEnabled(${bool(it)})" }
		}
		attribute{
			field = "elevation"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=21
			kotlinMethod { "elevation = ${dimen(it)}" }
			javaMethod{ "setElevation(${dimen(it)})" }
		}
		attribute{
			field = "fadeScrollbars"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isScrollbarFadingEnabled = ${bool(it)}" }
			javaMethod{ "setScrollbarFadingEnabled(${bool(it)})" }
		}
		uselessAttribute("fadingEdge")
		attribute{
			field = "fadingEdgeLength"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "setFadingEdgeLength(${dimen(it)})" }
			javaMethod{ "setFadingEdgeLength(${dimen(it)})" }
		}



		attribute{
			field = "filterTouchesWhenObscured"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "filterTouchesWhenObscured = ${bool(it)}" }
			javaMethod{ "setFilterTouchesWhenObscured(${bool(it)})" }
		}
		attribute{
			field = "fitsSystemWindows"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "fitsSystemWindows = ${bool(it)}" }
			javaMethod{ "setFitsSystemWindows(${bool(it)})" }
		}
		attribute{
			field = "focusable"
			attrType = arrayOf(AttrType.BOOLEAN, AttrType.ENUM)
			kotlinMethod { "isFocusable = ${bool(it)}" }
			javaMethod{ "setFocusable(${bool(it)})" }
		}
		attribute{
			field = "focusableInTouchMode"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isFocusableInTouchMode = ${bool(it)}" }
			javaMethod{ "setFocusableInTouchMode(${bool(it)})" }
		}
		uselessAttribute("focusedByDefault")
		attribute{
			field = "forceHasOverlappingRendering"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=24
			kotlinMethod { "forceHasOverlappingRendering(${bool(it)})" }
			javaMethod{ "forceHasOverlappingRendering(${bool(it)})" }
		}
		attribute{
			field = "foreground"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			sdk=23
			kotlinMethod { "foreground = ${resource(it)}" }
			javaMethod{ "setForeground(${resource(it)})" }
		}
		attribute{
			field = "foregroundGravity"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "foregroundGravity = ${foregroundGravity(it)}" }
			javaMethod{ "setForegroundGravity(${foregroundGravity(it)})" }
		}
		uselessAttribute("foregroundInsidePadding")
		attribute{
			field = "foregroundTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "foregroundTint = ${color(it)}" }
			javaMethod{ "setForegroundTint(${color(it)})" }
		}
		attribute{
			field = "foregroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "foregroundTintMode = ${tintMode(it)}" }
			javaMethod{ "setForegroundTintMode(${tintMode(it)})" }
		}
		attribute{
			field = "hapticFeedbackEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isHapticFeedbackEnabled = ${bool(it)}" }
			javaMethod{ "setHapticFeedbackEnabled(${bool(it)})" }
		}
		attribute{
			field = "id"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "id = ${resource(it)}" }
			javaMethod{ "setId(${resource(it)})" }
		}
		attribute{
			field = "importantForAccessibility"
			attrType = arrayOf(AttrType.ENUM)
			sdk=16
			kotlinMethod { "importantForAccessibility = ${importantForAccessibility(it)}" }
			javaMethod{ "setImportantForAccessibility(${importantForAccessibility(it)})" }
		}
		attribute{
			field = "importantForAutofill"
			attrType = arrayOf(AttrType.FLAG)
			sdk=26
			kotlinMethod { "importantForAutofill = ${importantForAutofill(it)}" }
			javaMethod{ "setImportantForAutofill(${importantForAutofill(it)})" }
		}
		attribute{
			field = "isScrollContainer"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isScrollContainer = ${bool(it)}" }
			javaMethod{ "setIsScrollContainer(${bool(it)})" }
		}
		attribute{
			field = "keepScreenOn"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "keepScreenOn = ${bool(it)}" }
			javaMethod{ "setKeepScreenOn(${bool(it)})" }
		}
		uselessAttribute("keyboardNavigationCluster")

		attribute{
			field = "labelFor"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=17
			kotlinMethod { "labelFor = ${id(it)}" }
			javaMethod{ "setLabelFor(${id(it)})" }
		}
		attribute{
			field = "layerType"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "setLayerType(${layerType(it)}, null)" }
			javaMethod{ "setLayerType(${layerType(it)}, null)" }
		}

		attribute{
			field = "layoutDirection"
			importList= arrayOf("android.view.View")
			attrType = arrayOf(AttrType.FLAG)
			sdk=17
			kotlinMethod { "layoutDirection = ${layoutDirection(it)}" }
			javaMethod{ "setLayoutDirection(${layoutDirection(it)})" }
		}
		attribute{
			field = "longClickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isLongClickable = ${bool(it)}" }
			javaMethod{ "setLongClickable(${bool(it)})" }
		}
		attribute{
			field = "minHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "minimumHeight = ${dimen(it)}" }
			javaMethod{ "setMinimumHeight(${dimen(it)})" }
		}
		attribute{
			field = "minWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "minimumWidth = ${dimen(it)}" }
			javaMethod{ "setMinimumWidth(${dimen(it)})" }
		}
		attribute{
			field = "nestedScrollingEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			kotlinMethod { "isNestedScrollingEnabled = ${bool(it)}" }
			javaMethod{ "setNestedScrollingEnabled(${bool(it)})" }
		}
		uselessAttribute("nextClusterForward")
		attribute{
			field = "nextFocusDown"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "nextFocusDownId = ${resourceRef(it)}" }
			javaMethod{ "setNextFocusDownId(${resourceRef(it)})" }
		}
		attribute{
			field = "nextFocusForward"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "nextFocusForwardId = ${resourceRef(it)}" }
			javaMethod{ "setNextFocusForwardId(${resourceRef(it)})" }
		}
		attribute{
			field = "nextFocusLeft"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "nextFocusLeftId = ${resourceRef(it)}" }
			javaMethod{ "setNextFocusLeftId(${resourceRef(it)})" }
		}
		attribute{
			field = "nextFocusRight"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "nextFocusRightId = ${resourceRef(it)}" }
			javaMethod{ "setNextFocusRightId(${resourceRef(it)})" }
		}
		attribute{
			field = "nextFocusUp"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "nextFocusUpId = ${resourceRef(it)}" }
			javaMethod{ "setNextFocusUpId(${resourceRef(it)})" }
		}
		attribute{
			field = "onClick"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "setOnClickListener{\n\t$it(this)\n}" }
			javaMethod{ "setOnClickListener{\n\t$it(this)\n}" }
		}
		uselessAttribute("outlineProvider")
		attribute{
			field = "overScrollMode"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "overScrollMode = ${overScrollMode(it)}" }
			javaMethod{ "setOverScrollMode(${overScrollMode(it)})" }
		}

		//-------------------------------------------------
		//以下属性将被组合设定
		//-------------------------------------------------
//		attribute{
//			field = "padding"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "padding = ${dimen(it)}" }
//			javaMethod{ "setPadding(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingBottom"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingBottom = ${dimen(it)}" }
//			javaMethod{ "setPaddingBottom(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingEnd"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingEnd = ${dimen(it)}" }
//			javaMethod{ "setPaddingEnd(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingHorizontal"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingHorizontal = ${dimen(it)}" }
//			javaMethod{ "setPaddingHorizontal(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingLeft"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingLeft = ${dimen(it)}" }
//			javaMethod{ "setPaddingLeft(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingRight"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingRight = ${dimen(it)}" }
//			javaMethod{ "setPaddingRight(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingStart"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingStart = ${dimen(it)}" }
//			javaMethod{ "setPaddingStart(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingTop"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingTop = ${dimen(it)}" }
//			javaMethod{ "setPaddingTop(${dimen(it)})" }
//		}
//		attribute{
//			field = "paddingVertical"
//			attrType = arrayOf(AttrType.DIMENSION)
//			kotlinMethod { "paddingVertical = ${dimen(it)}" }
//			javaMethod{ "setPaddingVertical(${dimen(it)})" }
//		}

		//-------------------------------------------------
		//-------------------------------------------------


		attribute{
			field = "pointerIcon"
			sdk=24
			importList= arrayOf("android.view.PointerIcon")
			attrType = arrayOf(AttrType.ENUM)
			kotlinMethod { "pointerIcon = ${pointerIcon(it)}" }
			javaMethod{ "setPointerIcon(${pointerIcon(it)})" }
		}
		uselessAttribute("requiresFadingEdge")
		attribute{
			field = "rotation"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "rotation = ${float(it)}" }
			javaMethod{ "setRotation(${float(it)})" }
		}
		attribute{
			field = "rotationX"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "rotationX = ${float(it)}" }
			javaMethod{ "setRotationX(${float(it)})" }
		}
		attribute{
			field = "rotationY"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "rotationY = ${float(it)}" }
			javaMethod{ "setRotationY(${float(it)})" }
		}
		attribute{
			field = "saveEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isSaveEnabled = ${bool(it)}" }
			javaMethod{ "setSaveEnabled(${bool(it)})" }
		}
		attribute{
			field = "scaleX"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "scaleX = ${float(it)}" }
			javaMethod{ "setScaleX(${float(it)})" }
		}
		attribute{
			field = "scaleY"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "scaleY = ${float(it)}" }
			javaMethod{ "setScaleY(${float(it)})" }
		}
		attribute{
			field = "scrollIndicators"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "scrollIndicators = ${scrollIndicators(it)}" }
			javaMethod{ "setScrollIndicators(${scrollIndicators(it)})" }
		}
		attribute{
			field = "scrollX"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "scrollX = ${dimen(it)}" }
			javaMethod{ "setScrollX(${dimen(it)})" }
		}
		attribute{
			field = "scrollY"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "scrollY = ${dimen(it)}" }
			javaMethod{ "setScrollY(${dimen(it)})" }
		}
		uselessAttribute("scrollbarTrackHorizontal")
		uselessAttribute("scrollbarTrackVertical")
		uselessAttribute("scrollbarThumbHorizontal")
		uselessAttribute("scrollbarThumbVertical")
		uselessAttribute("scrollbarAlwaysDrawHorizontalTrack")
		uselessAttribute("scrollbarAlwaysDrawVerticalTrack")


		attribute{
			field = "scrollbarDefaultDelayBeforeFade"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=16
			kotlinMethod { "scrollBarDefaultDelayBeforeFade = ${int(it)}" }
			javaMethod{ "setScrollbarDefaultDelayBeforeFade(${int(it)})" }
		}
		attribute{
			field = "scrollbarFadeDuration"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=16
			kotlinMethod { "scrollbarFadeDuration = ${int(it)}" }
			javaMethod{ "setScrollbarFadeDuration(${int(it)})" }
		}
		uselessAttribute("scrollbarSize")
		attribute{
			field = "scrollbarStyle"
			sdk=16
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "scrollbarStyle = ${scrollBarStyle(it)}" }
			javaMethod{ "setScrollbarStyle(${scrollBarStyle(it)})" }
		}
		uselessAttribute("scrollbars")

		attribute{
			field = "soundEffectsEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isSoundEffectsEnabled = ${bool(it)}" }
			javaMethod{ "setSoundEffectsEnabled(${bool(it)})" }
		}

		attribute{
			field = "stateListAnimator"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=21
			kotlinMethod { "stateListAnimator = ${resource(it)}" }
			javaMethod{ "setStateListAnimator(${resource(it)})" }
		}
		attribute{
			field = "tag"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "tag = ${string(it)}" }
			javaMethod{ "setTag(${string(it)})" }
		}
		attribute{
			field = "textAlignment"
			attrType = arrayOf(AttrType.INTEGER)
			importList= arrayOf("android.view.View")
			sdk=17
			kotlinMethod { "textAlignment = ${textAlignment(it)}" }
			javaMethod{ "setTextAlignment(${textAlignment(it)})" }
		}
		attribute{
			field = "textDirection"
			attrType = arrayOf(AttrType.ENUM)
			kotlinMethod { "textDirection = ${textDirection(it)}" }
			javaMethod{ "setTextDirection(${textDirection(it)})" }
		}

		attribute{
			field = "theme"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "theme = ${resourceRef(it)}" }
			javaMethod{ "setTheme(${resourceRef(it)})" }
		}

		attribute{
			field = "tooltipText"
			attrType = arrayOf(AttrType.STRING)
			sdk=26
			kotlinMethod { "tooltipText = ${string(it)}" }
			javaMethod{ "setTooltipText(${string(it)})" }
		}

		attribute{
			field = "transformPivotX"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "transformPivotX = ${dimen(it)}" }
			javaMethod{ "setTransformPivotX(${dimen(it)})" }
		}
		attribute{
			field = "transformPivotY"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "transformPivotY = ${dimen(it)}" }
			javaMethod{ "setTransformPivotY(${dimen(it)})" }
		}
		attribute{
			field = "transitionName"
			attrType = arrayOf(AttrType.STRING)
			sdk=21
			kotlinMethod { "transitionName = ${string(it)}" }
			javaMethod{ "setTransitionName(${string(it)})" }
		}
		attribute{
			field = "translationX"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "translationX = ${dimen(it)}" }
			javaMethod{ "setTranslationX(${dimen(it)})" }
		}
		attribute{
			field = "translationY"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "translationY = ${dimen(it)}" }
			javaMethod{ "setTranslationY(${dimen(it)})" }
		}
		attribute{
			field = "translationZ"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "translationZ = ${dimen(it)}" }
			javaMethod{ "setTranslationZ(${dimen(it)})" }
		}
		uselessAttribute("verticalScrollbarPosition")
		attribute{
			field = "visibility"
			attrType = arrayOf(AttrType.ENUM)
			importList= arrayOf("android.view.View")
			kotlinMethod { "visibility = ${visibility(it)}" }
			javaMethod{ "setVisibility(${visibility(it)})" }
		}
	}

	private fun getSimpleClassName(): String {
		val className = this::class.java.name
		return className.substring(className.lastIndexOf(".") + 1)
	}

	/**
	 * 常规属性
	 */
	protected inline fun attribute(action: AttributeStyle.()->Unit){
		val item = AttributeStyle().apply(action)
		viewStyleItems.put(item.field,item)
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
	 * 根据系统配置属性,添加到属性集
	 */
	protected fun addAttributeItems(attribute: AttributeNode){
		val findItem= viewStyleItems[attribute.name]
		if(null!=findItem){
			//添加控件配置属性
			applyAttributes(attribute)
			attributes.add(ViewAttributeItem(findItem,attribute.value))
		}
	}

	/**
	 * 根据系统配置属性,添加到属性集
	 */
	protected fun addMultiAttributeItems(name:String,vararg value:String){
		//添加多个控件配置属性
		attributes.add(MultiAttributeItem(name,*value))
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
	 * class的声明对象
	 */
	open fun getClassName(): String=if (!isCompatView) getViewName() else getThemeViewName()

	/**
	 * 获得android控件的classPath
	 */
	open fun getClassPath():String{
		val simpleName=this::class.java.simpleName
		return "android.widget.$simpleName"
	}

	/**
	 * 解析View属性集,并返回解析后的anko代码
	 */
	open fun inflateAttributes(viewNode:ViewNode){
		viewNode.attributes.forEach(this::addAttributeItems)
		//添加内边距属性
		addPaddingAttribute(viewNode)
	}

	/**
	 * 获取控件节点内,未使用的属性
	 */
	fun getUnknownAttributes(viewNode:ViewNode):MutableList<UnknownViewAttributeItem>{
		//未应用属性
		val unknownAttributes = mutableListOf<UnknownViewAttributeItem>()
		val uselessAttributes = viewNode.attributes.filter { !it.isApply }
		uselessAttributes.forEach {
			//添加未知属性转换器
			unknownAttributes.add(UnknownViewAttributeItem(it.name,it.value))
		}
		return unknownAttributes
	}

	/**
	 * 添加内边距属性
	 */
	private fun addPaddingAttribute(element:ViewNode) {
		val padding = element.attributes.find { it.name == "padding" }
		var paddingLeft = element.attributes.find { it.name == "paddingLeft" }
		var paddingTop = element.attributes.find { it.name == "paddingTop" }
		var paddingRight = element.attributes.find { it.name == "paddingRight" }
		var paddingBottom = element.attributes.find { it.name == "paddingBottom" }
		val paddingStart = element.attributes.find { it.name == "paddingStart" }
		val paddingEnd = element.attributes.find { it.name == "paddingEnd" }
		val paddingHorizontal = element.attributes.find { it.name == "paddingHorizontal" }
		val paddingVertical = element.attributes.find { it.name == "paddingVertical" }
		//应用属性集
		applyAttributes(padding,paddingLeft,paddingTop,
				paddingRight,paddingBottom,paddingStart,
				paddingEnd,paddingHorizontal,paddingVertical)
		//添加属性表达式
		if (null != padding) {
			addMultiAttributeItems("setPadding", dimenPadding(padding.value),
					dimenPadding(padding.value),
					dimenPadding(padding.value),
					dimenPadding(padding.value))
		} else {
			if (null != paddingHorizontal) {
				paddingLeft = paddingHorizontal
				paddingRight = paddingHorizontal
			}
			if (null != paddingVertical) {
				paddingTop = paddingVertical
				paddingBottom = paddingVertical
			}
			if (null != paddingLeft || null != paddingTop
					|| null != paddingRight || null != paddingRight) {
				addMultiAttributeItems("setPadding", dimenPadding(paddingLeft?.value),
						dimenPadding(paddingTop?.value),
						dimenPadding(paddingRight?.value),
						dimenPadding(paddingBottom?.value))
			} else if (null != paddingStart || null != paddingEnd) {
				addMultiAttributeItems("setPaddingRelative", dimenPadding(paddingStart?.value),
						dimenPadding(paddingTop),
						dimenPadding(paddingEnd?.value),
						dimenPadding(paddingBottom?.value))
			}
		}
	}

	private inline fun dimenPadding(value:String?)=if(null==value) "0" else dimen(value)

	/**
	 * 应用一个属性
	 */
	inline fun applyAttributes(vararg attributes: AttributeNode?)=attributes?.forEach { it?.isApply=true }


}
