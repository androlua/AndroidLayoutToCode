package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.expression.AttributeMethodMultiParamsExpression
import com.cz.layout2code.inflate.expression.value.ElementExpression
import com.cz.layout2code.inflate.expression.value.CastIntFieldExpression
import com.cz.layout2code.inflate.expression.value.StringValueExpression
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.prefs.AttributeStyle

/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------View all expressions---------------
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
	//导包列
	val importItems= mutableSetOf<ImportItem>()
	//控件引用样式集
	val viewStyleItems = mutableMapOf<String,AttributeStyle>()
	//常规元素表达式对象
	val expressions= mutableListOf<ElementExpression>()
	//是否为compat v7包内控件
	var isCompatView=false


	init {
		//当前对象
		attribute{
			field = "accessibilityLiveRegion"
			attrType = arrayOf(AttrType.ENUM)
			sdk=19
			property("accessibilityLiveRegion") { accessibilityLiveRegion(it) }
		}
		attribute{
			field = "accessibilityTraversalAfter"
			attrType = arrayOf(AttrType.ENUM)
			sdk=22
			property("accessibilityTraversalAfter") { int(it) }
		}
		attribute{
			field = "accessibilityTraversalBefore"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=22
			property("accessibilityTraversalBefore") { int(it) }
		}
		attribute{
			field = "alpha"
			attrType = arrayOf(AttrType.FLOAT)
			property("alpha") { float(it) }
		}

		uselessAttribute("autofillHints")
		attribute{
			field = "background"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			property("backgroundResource") { resourceRef(it) }
		}
		attribute{
			field = "backgroundTint"
			attrType = arrayOf(AttrType.COLOR)
			property("backgroundTintList") { colorStateList(it) }
		}
		attribute{
			field = "backgroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			property("backgroundTintMode") { colorStateList(it) }
		}
		attribute{
			field = "clickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			property{
				java="setClickable"
				kotlin="isClickable"
				value { bool(it) }
			}
		}
		attribute{
			field = "contentDescription"
			attrType = arrayOf(AttrType.STRING)
			property("contentDescription") { string(it) }
		}
		attribute{
			field = "contextClickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=23
			property{
				java="setContextClickable"
				kotlin="isContextClickable"
				value { bool(it) }
			}
		}
		attribute{
			field = "defaultFocusHighlightEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=26
			property("defaultFocusHighlightEnabled") { bool(it) }
		}
		attribute{
			field = "drawingCacheQuality"
			attrType = arrayOf(AttrType.FLAG)
			property("drawingCacheQuality") { drawingCacheQuality(it) }
		}
		attribute{
			field = "duplicateParentState"
			attrType = arrayOf(AttrType.BOOLEAN)
			property{
				java="setDuplicateParentStateEnabled"
				kotlin="isDuplicateParentStateEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "elevation"
			attrType = arrayOf(AttrType.DIMENSION)
			sdk=21
			property("elevation") { dimen(it) }
		}
		attribute{
			field = "fadeScrollbars"
			attrType = arrayOf(AttrType.BOOLEAN)
			property{
				java="setScrollbarFadingEnabled"
				kotlin="isScrollbarFadingEnabled"
				value { bool(it) }
			}
		}
		uselessAttribute("fadingEdge")
		attribute{
			field = "fadingEdgeLength"
			attrType = arrayOf(AttrType.DIMENSION)
			method("setFadingEdgeLength") { dimen(it) }
		}
		attribute{
			field = "filterTouchesWhenObscured"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("filterTouchesWhenObscured") { bool(it) }
		}
		attribute{
			field = "fitsSystemWindows"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("fitsSystemWindows") { bool(it) }
		}
		attribute{
			field = "focusable"
			attrType = arrayOf(AttrType.BOOLEAN, AttrType.ENUM)
			property {
				java="setFocusable"
				kotlin="isFocusable"
				value{ bool(it) }
			}
		}
		attribute{
			field = "focusableInTouchMode"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setFocusableInTouchMode"
				kotlin="isFocusableInTouchMode"
				value{ bool(it) }
			}
		}
		uselessAttribute("focusedByDefault")
		attribute{
			field = "forceHasOverlappingRendering"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=24
			method("forceHasOverlappingRendering"){ bool(it) }
		}
		attribute{
			field = "foreground"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			sdk=23
			property ("foreground"){ resource(it) }
		}
		attribute{
			field = "foregroundGravity"
			attrType = arrayOf(AttrType.FLAG)
			property ("foregroundGravity"){ foregroundGravity(it) }
		}
		uselessAttribute("foregroundInsidePadding")
		attribute{
			field = "foregroundTint"
			attrType = arrayOf(AttrType.COLOR)
			property ("foregroundTint"){ color(it) }
		}
		attribute{
			field = "foregroundTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			property ("foregroundTintMode"){ tintMode(it) }
		}
		attribute{
			field = "hapticFeedbackEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setHapticFeedbackEnabled"
				kotlin="isHapticFeedbackEnabled"
				value{ bool(it) }
			}
		}
		attribute{
			field = "id"
			attrType = arrayOf(AttrType.REFERENCE)
			property ("id"){ resource(it) }
		}
		attribute{
			field = "importantForAccessibility"
			attrType = arrayOf(AttrType.ENUM)
			sdk=16
			property ("importantForAccessibility"){ importantForAccessibility(it) }
		}
		attribute{
			field = "importantForAutofill"
			attrType = arrayOf(AttrType.FLAG)
			sdk=26
			property ("importantForAutofill"){ importantForAutofill(it) }
		}
		attribute{
			field = "isScrollContainer"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setIsScrollContainer"
				kotlin="isScrollContainer"
				value{ bool(it) }
			}
		}
		attribute{
			field = "keepScreenOn"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("keepScreenOn"){ bool(it) }
		}
		uselessAttribute("keyboardNavigationCluster")

		attribute{
			field = "labelFor"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=17
			property("labelFor"){ id(it) }
		}
		attribute{
			field = "layerType"
			attrType = arrayOf(AttrType.FLAG)
			methods("setLayerType"){ mutableListOf(layerType(it),StringValueExpression("null")) }
		}

		attribute{
			field = "layoutDirection"
			attrType = arrayOf(AttrType.FLAG)
			sdk=17
			property("layoutDirection"){ layoutDirection(it) }
		}
		attribute{
			field = "longClickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setLongClickable"
				kotlin="isLongClickable"
				value{ bool(it) }
			}
		}
		attribute{
			field = "minHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("minimumHeight"){ dimen(it) }
		}
		attribute{
			field = "minWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("minimumWidth"){ dimen(it) }
		}
		attribute{
			field = "nestedScrollingEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			property {
				java="setNestedScrollingEnabled"
				kotlin="isNestedScrollingEnabled"
				value{ bool(it) }
			}
		}
		uselessAttribute("nextClusterForward")
		attribute{
			field = "nextFocusDown"
			attrType = arrayOf(AttrType.REFERENCE)
			property("nextFocusDownId"){ resourceRef(it) }
		}
		attribute{
			field = "nextFocusForward"
			attrType = arrayOf(AttrType.REFERENCE)
			property("nextFocusForwardId"){ resourceRef(it) }
		}
		attribute{
			field = "nextFocusLeft"
			attrType = arrayOf(AttrType.REFERENCE)
			property("nextFocusLeftId"){ resourceRef(it) }
		}
		attribute{
			field = "nextFocusRight"
			attrType = arrayOf(AttrType.REFERENCE)
			property("nextFocusRightId"){ resourceRef(it) }
		}
		attribute{
			field = "nextFocusUp"
			attrType = arrayOf(AttrType.REFERENCE)
			property("nextFocusUpId"){ resourceRef(it) }
		}
		//onClick的方法调用映射,无法做到此类复杂的方法声明表达式直接兼容,此处以一个自定义的语句形式直接拼写
		attribute{
			field = "onClick"
			attrType = arrayOf(AttrType.STRING)
			expression {
				import { mutableListOf(ImportItem("android.view.View")) }
				javaExpression { _,value->
					"setOnClickListener(new View.OnClickListener() {\n" +
							"\t@Override\n" +
							"\tpublic void onClick(View v) {\n" +
							"\t\tthis.$value(v)\n" +
							"\t}\n" +
							"\t});"
				}
				kotlinExpression { _,value->
					"setOnClickListener{ $value(it) }"
				}
			}
			property("nextFocusUpId"){ resourceRef(it) }
		}
		uselessAttribute("outlineProvider")
		attribute{
			field = "overScrollMode"
			attrType = arrayOf(AttrType.FLAG)
			property("overScrollMode"){ overScrollMode(it) }
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
			attrType = arrayOf(AttrType.ENUM)
			property("pointerIcon"){ pointerIcon(it) }
		}
		uselessAttribute("requiresFadingEdge")
		attribute{
			field = "rotation"
			attrType = arrayOf(AttrType.FLOAT)
			property("rotation"){ float(it) }
		}
		attribute{
			field = "rotationX"
			attrType = arrayOf(AttrType.FLOAT)
			property("rotationX"){ float(it) }
		}
		attribute{
			field = "rotationY"
			attrType = arrayOf(AttrType.FLOAT)
			property("rotationY"){ float(it) }
		}
		attribute{
			field = "saveEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setSaveEnabled"
				kotlin="isSaveEnabled"
				value{ bool(it) }
			}
		}
		attribute{
			field = "scaleX"
			attrType = arrayOf(AttrType.FLOAT)
			property("scaleX"){ float(it) }
		}
		attribute{
			field = "scaleY"
			attrType = arrayOf(AttrType.FLOAT)
			property("scaleY"){ float(it) }
		}
		attribute{
			field = "scrollIndicators"
			attrType = arrayOf(AttrType.FLAG)
			property("scrollIndicators"){ scrollIndicators(it) }
		}
		attribute{
			field = "scrollX"
			attrType = arrayOf(AttrType.DIMENSION)
			property("scrollX"){ dimen(it) }
		}
		attribute{
			field = "scrollY"
			attrType = arrayOf(AttrType.DIMENSION)
			property("scrollY"){ dimen(it) }
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
			property("scrollBarDefaultDelayBeforeFade"){ int(it) }
		}
		attribute{
			field = "scrollbarFadeDuration"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=16
			property("scrollbarFadeDuration"){ int(it) }
		}
		uselessAttribute("scrollbarSize")
		attribute{
			field = "scrollbarStyle"
			sdk=16
			attrType = arrayOf(AttrType.FLAG)
			property("scrollbarStyle"){ scrollBarStyle(it) }
		}
		uselessAttribute("scrollbars")

		attribute{
			field = "soundEffectsEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setSoundEffectsEnabled"
				kotlin="isSoundEffectsEnabled"
				value{ bool(it) }
			}
		}

		attribute{
			field = "stateListAnimator"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=21
			property("stateListAnimator"){ resource(it) }
		}
		attribute{
			field = "tag"
			attrType = arrayOf(AttrType.STRING)
			property("tag"){ string(it) }
		}
		attribute{
			field = "textAlignment"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=17
			property("textAlignment"){ textAlignment(it) }
		}
		attribute{
			field = "textDirection"
			attrType = arrayOf(AttrType.ENUM)
			property("textDirection"){ textDirection(it) }
		}

		attribute{
			field = "theme"
			attrType = arrayOf(AttrType.REFERENCE)
			property("theme"){ resourceRef(it) }
		}

		attribute{
			field = "tooltipText"
			attrType = arrayOf(AttrType.STRING)
			sdk=26
			property("tooltipText"){ string(it) }
		}

		attribute{
			field = "transformPivotX"
			attrType = arrayOf(AttrType.DIMENSION)
			property("transformPivotX"){ dimen(it) }
		}
		attribute{
			field = "transformPivotY"
			attrType = arrayOf(AttrType.DIMENSION)
			property("transformPivotY"){ dimen(it) }
		}
		attribute{
			field = "transitionName"
			attrType = arrayOf(AttrType.STRING)
			sdk=21
			property("transitionName"){ dimen(it) }
		}
		attribute{
			field = "translationX"
			attrType = arrayOf(AttrType.DIMENSION)
			property("translationX"){ dimen(it) }
		}
		attribute{
			field = "translationY"
			attrType = arrayOf(AttrType.DIMENSION)
			property("translationY"){ dimen(it) }
		}
		attribute{
			field = "translationZ"
			attrType = arrayOf(AttrType.DIMENSION)
			property("translationZ"){ dimen(it) }
		}
		uselessAttribute("verticalScrollbarPosition")
		attribute{
			field = "visibility"
			attrType = arrayOf(AttrType.ENUM)
			property("visibility"){ visibility(it) }
		}
	}

	private fun getSimpleClassName(): String {
		val className = this.javaClass.simpleName
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
		val simpleName=this.javaClass.simpleName
		return "android.widget.$simpleName"
	}

	/**
	 * 解析View属性集
	 */
	open fun inflateAttributes(viewNode:ViewNode){
		viewNode.attributes.forEach{ attribute->
			val findItem= viewStyleItems[attribute.name]
			if(null!=findItem){
				//添加控件配置属性
				applyAttributes(attribute)
				//回调对象取值
				val newExpression=findItem.expression.callback(attribute.value)
				expressions.add(newExpression)
			}
		}
		//检测导包
		if(isCompatView){
			importItems.add(ImportItem("org.jetbrains.anko.${getThemeViewName()}"))//v7
		} else {
			importItems.add(ImportItem("android.widget.${this.javaClass.simpleName}"))//系统控件
		}
		//添加内边距属性
		addPaddingAttribute(viewNode)
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
			val expression=AttributeMethodMultiParamsExpression("setPadding"){
				mutableListOf(dimenPadding(padding.value),
						dimenPadding(padding.value),
						dimenPadding(padding.value),
						dimenPadding(padding.value))
			}
			expressions.add(expression)
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
				val expression=AttributeMethodMultiParamsExpression("setPadding"){
					mutableListOf(dimenPadding(paddingLeft?.value),
					dimenPadding(paddingTop?.value),
					dimenPadding(paddingRight?.value),
					dimenPadding(paddingBottom?.value))
				}
				expressions.add(expression)
			} else if (null != paddingStart || null != paddingEnd) {
				val expression=AttributeMethodMultiParamsExpression("setPadding"){
					mutableListOf(dimenPadding(paddingStart?.value),
							dimenPadding(paddingTop),
							dimenPadding(paddingEnd?.value),
							dimenPadding(paddingBottom?.value))
				}
				expressions.add(expression)
			}
		}
	}

	private inline fun dimenPadding(value:String?):CastIntFieldExpression{
		val expression=if(null==value) StringValueExpression("0") else dimen(value)
		return CastIntFieldExpression(expression)
	}

	/**
	 * 应用一个属性
	 */
	inline fun applyAttributes(vararg attributes: AttributeNode?)=attributes?.forEach { it?.isApply=true }


}
