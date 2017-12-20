package com.cz.layout2anko.inflate.impl

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
	companion object{
		val DIMEN_PATTERN="((?<int>\\d+)|(?<float>[\\d\\.]+))(?<unit>(?:di?p)|(?:sp)|?:(px))".toPattern()
		val RESOURCE_PATTERN="@+?(\\w+)/(.+)".toPattern()
	}
	val attributes= mutableListOf<Any>()

	fun convert(element:Element,level:Int):String{
		val out=StringBuilder()
		val tab="".padEnd(level,'\t')
		inflateAttributes(element)
		attributes.forEach { out.append("$tab$it") }
		return out.toString()
	}

	fun dimen(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return dimensionValue(value)
		}
	}

	fun string(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return value
		}
	}

	private fun dimensionValue(value: String): String {
		//数值分为
		// float:浮点型,后加f
		// sp/dp/dip/px
		// 固定值
		var result = String()
		val matcher = DIMEN_PATTERN.matcher(value)
		if (matcher.find()) {
			val int = matcher.group("int")
			val float = matcher.group("float")
			val unit = matcher.group("unit")
			val number = int?:float?:"0"
			if (null == unit) {
				result = number
			} else {
				if (null != int) {
					when (unit) {
						"sp" -> result = "sp($number*1f)"
						"dip", "dp" -> result = "dp($number*1f)"
					}
				} else if (null != float) {
					when (unit) {
						"sp" -> result = "sp($number)"
						"dip", "dp" -> result = "dp($number)"
					}
				}
			}
		}
		return result
	}

	fun int(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return dimen(value)
		}
	}

	fun float(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return value+"f"
		}
	}

	fun long(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return value+"l"
		}
	}

	fun bool(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return value
		}
	}

	fun color(value:String):String{
		if(value.startsWith("@")){
			return resource(value)
		} else {
			return value
		}
	}

	fun id(value:String)=resource(value)

	fun colorStateList(value:String):String{
		var result=String()
		val matcher=RESOURCE_PATTERN.matcher(value)
		if(matcher.find()) {
			val type = matcher.group(1)
			val ref = matcher.group(2)
			if("drawable"==type){
				result = "ContextCompat.getColorStateList(context,R.drawable.$ref)"
			} else {
				result = "//$ref is not a drawable ref! Can't reverse it!"
			}
		}
		return result
	}

	/**
	 * 资源引用
	 */
	fun resource(value:String):String{
		//资源引用
		var result=String()
		val matcher=RESOURCE_PATTERN.matcher(value)
		if(matcher.find()){
			val type=matcher.group(1)
			val ref=matcher.group(2)
			when(type){
				"id"->result="R.id.$ref"
				"string"->result="resources.getString(R.string.$ref)"
				"dimen"->result="resources.getDimension(R.dimen.$ref)"
				"integer"->result="resources.getInteger(R.integer.$ref)"
				"color"->result="ContextCompat.getColor(context,R.color.$ref)"
				"drawable"->result="ContextCompat.getDrawable(R.drawable.$ref)"
				"mipmap"->result="ContextCompat.getDrawable(R.mipmap.$ref)"
				"string"->result="context.getString(R.string.$ref)"
				"array"->result="resources.getStringArray(R.array.$ref)"
				"theme"->result="setTheme(R.style.$ref)"
				"anim"->result="AnimationUtils.loadAnimation(context,R.anim.$ref)"
				"bool"->result="resources.getBoolean(R.bool.$ref)"
				"animator"->result="AnimatorInflater.loadAnimator(context,R.animator.$ref)"
			}
		}
		return result
	}

	/**
	 * 资源引用
	 */
	fun resourceRef(value:String):String{
		//资源引用
		var result=String()
		val matcher=RESOURCE_PATTERN.matcher(value)
		if(matcher.find()){
			val type=matcher.group(1)
			val ref=matcher.group(2)
			when(type){
				"id"->result="R.id.$ref"
				"dimen"->result="R.dimen.$ref"
				"integer"->result="R.integer.$ref"
				"color"->result="context,R.color.$ref"
				"drawable"->result="R.drawable.$ref"
				"mipmap"->result="R.mipmap.$ref"
				"string"->result="R.string.$ref"
				"array"->result="R.array.$ref"
				"theme"->result="R.style.$ref"
				"anim"->result="R.anim.$ref"
				"animator"->result="R.animator.$ref"
			}
		}
		return result
	}

	fun drawingCacheQuality(quality:String)=when(quality){
		"low"->"View.DRAWING_CACHE_QUALITY_LOW"
		"high"->"View.DRAWING_CACHE_QUALITY_HIGH"
		else ->"View.DRAWING_CACHE_QUALITY_AUTO"
	}

	/**
	 * View.LAYER_TYPE_NONE
	 * View.LAYER_TYPE_HARDWARE
	 * View.LAYER_TYPE_SOFTWARE
	 */
	fun layerType(type:String)=when(type){
		"hardware"->"View.LAYER_TYPE_HARDWARE"
		"software"->"View.LAYER_TYPE_SOFTWARE"
		else->"View.LAYER_TYPE_NONE"
	}

	/**
	 * {@link #LAYOUT_DIRECTION_LTR},
	 * {@link #LAYOUT_DIRECTION_RTL},
	 * {@link #LAYOUT_DIRECTION_INHERIT},
	 * {@link #LAYOUT_DIRECTION_LOCALE}.
	 */
	fun layoutDirection(direction:String)=when(direction){
		"rtl"->"View.LAYOUT_DIRECTION_RTL"
		"inherit"->"View.LAYOUT_DIRECTION_INHERIT"
		"locale"->"View.LAYOUT_DIRECTION_LOCALE"
		else ->"View.LAYOUT_DIRECTION_LTR"
	}

	/**
	 * @see #SCROLLBARS_INSIDE_OVERLAY
	 * @see #SCROLLBARS_INSIDE_INSET
	 * @see #SCROLLBARS_OUTSIDE_OVERLAY
	 * @see #SCROLLBARS_OUTSIDE_INSET
	 */
	fun scrollBarStyle(style:String)=when(style){
		"overlay"->"View.SCROLLBARS_INSIDE_OVERLAY"
		"inset"->"View.SCROLLBARS_INSIDE_INSET"
		"overlay"->"View.SCROLLBARS_OUTSIDE_OVERLAY"
		else ->"View.SCROLLBARS_OUTSIDE_INSET"
	}

	/**
	 * {@link #TEXT_DIRECTION_INHERIT},
	 * {@link #TEXT_DIRECTION_FIRST_STRONG},
	 * {@link #TEXT_DIRECTION_ANY_RTL},
	 * {@link #TEXT_DIRECTION_LTR},
	 * {@link #TEXT_DIRECTION_RTL},
	 * {@link #TEXT_DIRECTION_LOCALE}
	 * {@link #TEXT_DIRECTION_FIRST_STRONG_LTR},
	 * {@link #TEXT_DIRECTION_FIRST_STRONG_RTL},
	 */
	fun textDirection(direction:String)=when(direction){
		"inherit"->"View.TEXT_DIRECTION_INHERIT"
		"firstStrong"->"View.TEXT_DIRECTION_FIRST_STRONG"
		"anyRtl"->"View.TEXT_DIRECTION_ANY_RTL"
		"rtl"->"View.TEXT_DIRECTION_RTL"
		"locale"->"View.TEXT_DIRECTION_LOCALE"
		"firstStrongLtr"->"View.TEXT_DIRECTION_FIRST_STRONG_LTR"
		"firstStrongRtl"->"View.TEXT_DIRECTION_FIRST_STRONG_RTL"
		else->"View.TEXT_DIRECTION_LTR"
	}

	/**
	 * {@link #VISIBLE}
	 * {@link #INVISIBLE}
	 * {@link #GONE}.
	 */
	fun visibility(visible:String)=when(visible){
		"invisible"->"View.INVISIBLE"
		"gone"->"View.GONE"
		else->"View.VISIBLE"
	}

	/**
	 * {@link #TEXT_ALIGNMENT_INHERIT},
	 * {@link #TEXT_ALIGNMENT_GRAVITY},
	 * {@link #TEXT_ALIGNMENT_CENTER},
	 * {@link #TEXT_ALIGNMENT_TEXT_START},
	 * {@link #TEXT_ALIGNMENT_TEXT_END},
	 * {@link #TEXT_ALIGNMENT_VIEW_START},
	 * {@link #TEXT_ALIGNMENT_VIEW_END}
	 */
	fun textAlignment(alignment:String)=when(alignment){
		"inherit"->"View.TEXT_ALIGNMENT_INHERIT"
		"center"->"View.TEXT_ALIGNMENT_CENTER"
		"textStart"->"View.TEXT_ALIGNMENT_TEXT_START"
		"textEnd"->"View.TEXT_ALIGNMENT_TEXT_END"
		"viewStart"->"View.TEXT_ALIGNMENT_VIEW_START"
		"viewEnd"->"View.TEXT_ALIGNMENT_VIEW_END"
		else->"View.TEXT_ALIGNMENT_GRAVITY"
	}

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
	 * 解析View属性集,并返回解析后的anko代码
	 */
	open fun inflateAttributes(element:Element){
		element.attributes.forEach {
			val name=it.name
			val value = it.value
			when(name){
				"alpha"->attributes.add("alpha=${dimen(value)}")
				"background"->attributes.add("backgroundResource=${resourceRef(value)}")
				"clickable"->attributes.add("isClickable=${bool(value)}")
				"contentDescription"->attributes.add("contentDescription=$value")
				"drawingCacheQuality"->attributes.add("drawingCacheQuality=${drawingCacheQuality(value)}")
				"duplicateParentState"->attributes.add("isDuplicateParentStateEnabled=${bool(value)}")
				"id"->attributes.add("id=${resourceRef(value)}")
				"requiresFadingEdge"->{
					attributes.add("//Can't reverse requiresFadingEdge!\n")
					attributes.add("//requiresFadingEdge=$value")
				}
				"fadeScrollbars"->attributes.add("isScrollbarFadingEnabled=${bool(value)}")
				"fadingEdgeLength"->attributes.add("setFadingEdgeLength(${dimen(value)}")
				"filterTouchesWhenObscured"->attributes.add("filterTouchesWhenObscured=${bool(value)}")
				"fitsSystemWindows"->attributes.add("fitsSystemWindows=${bool(value)}")
				"isScrollContainer"->attributes.add("isScrollContainer=${bool(value)}")
				"focusable"->attributes.add("isFocusable=${bool(value)}")
				"focusableInTouchMode"->attributes.add("isFocusableInTouchMode=${bool(value)}")
				"focusedByDefault"->attributes.add("//focusedByDefault=$value")
				"hapticFeedbackEnabled"->attributes.add("isHapticFeedbackEnabled=${bool(value)}")
				"keepScreenOn"->attributes.add("keepScreenOn=${bool(value)}")
				"keyboardNavigationCluster"->{
					attributes.add("//Can't reverse keyboardNavigationCluster!\n")
					attributes.add("//keyboardNavigationCluster=$value")
				}
				"layerType"->attributes.add("setLayerType(${layerType(value)}, null)")
				"layoutDirection"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN_MR1){\n")
					attributes.add("\tlayoutDirection=View.LAYOUT_DIRECTION_LOCALE\n")
					attributes.add("}\n")
				}
				"longClickable"->attributes.add("isLongClickable=${bool(value)}")
				"minWidth"->attributes.add("minimumWidth=${dimen(value)}")
				"minHeight"->attributes.add("minimumHeight=${dimen(value)}")
				"nextClusterForward"->{
					attributes.add("//Can't reverse nextClusterForward!\n")
					attributes.add("//nextClusterForward=$value")
				}
				"nextFocusDown"->attributes.add("nextFocusDownId=${resourceRef(value)}")
				"nextFocusLeft"->attributes.add("nextFocusLeftId=${resourceRef(value)}")
				"nextFocusRight"->attributes.add("nextFocusRightId=${resourceRef(value)}")
				"nextFocusUp"->attributes.add("nextFocusUpId=${resourceRef(value)}")
				"onClick"->attributes.add("setOnClickListener { $value(this) }")
				"padding"->attributes.add("padding=${dimen(value)}")
				"paddingHorizontal"->attributes.add("paddingHorizontal=${dimen(value)}")
				"paddingVertical"->attributes.add("paddingVertical=${dimen(value)}")
				"paddingBottom"->attributes.add("bottomPadding=${dimen(value)}")
				"paddingLeft"->attributes.add("leftPadding=${dimen(value)}")
				"paddingRight"->attributes.add("rightPadding=${dimen(value)}")
				"paddingTop"->attributes.add("topPadding=${dimen(value)}")
				"paddingStart"->{
					attributes.add("//Can't reverse paddingStart!\n")
					attributes.add("//paddingStart=$value")
				}
				"paddingEnd"->{
					attributes.add("//Can't reverse paddingEnd!")
					attributes.add("//paddingEnd=$value")
				}
				"saveEnabled"->attributes.add("isSaveEnabled=${bool(value)}")
				"rotation"->attributes.add("rotation=${dimen(value)}")
				"rotationX"->attributes.add("rotationX=${dimen(value)}")
				"rotationY"->attributes.add("rotationY=${dimen(value)}")
				"scaleX"->attributes.add("scaleX=${dimen(value)}")
				"scaleY"->attributes.add("scaleY=${dimen(value)}")
				"scrollX"->attributes.add("scrollX=${dimen(value)}")
				"scrollY"->attributes.add("scrollY=${dimen(value)}")
				"scrollbarSize"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){")
					attributes.add("\tscrollBarSize=${dimen(value)}")
					attributes.add("}")
				}
				"scrollbarStyle"->attributes.add("scrollBarStyle=${scrollBarStyle(value)}")
				"scrollbars"->{
					attributes.add("//Can't reverse scrollbars!")
					attributes.add("//scrollbars=$value")
				}
				"scrollbarDefaultDelayBeforeFade"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){")
					attributes.add("\tscrollBarDefaultDelayBeforeFade=${dimen(value)}")
					attributes.add("}")
				}
				"scrollbarFadeDuration"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){")
					attributes.add("\tscrollBarFadeDuration=${dimen(value)}")
					attributes.add("}")
				}
				"scrollbarTrackHorizontal"->{
					attributes.add("//Can't reverse scrollbarTrackHorizontal!")
					attributes.add("//scrollbarTrackHorizontal=$value")
				}
				"scrollbarThumbHorizontal"->{
					attributes.add("//Can't reverse scrollbarThumbHorizontal!")
					attributes.add("//scrollbarThumbHorizontal=$value")
				}
				"scrollbarThumbVertical"->{
					attributes.add("//Can't reverse scrollbarThumbVertical!")
					attributes.add("//scrollbarThumbVertical=$value")
				}
				"scrollbarTrackVertical"->{
					attributes.add("//Can't reverse scrollbarTrackVertical!")
					attributes.add("//scrollbarTrackVertical=$value")
				}
				"scrollbarAlwaysDrawHorizontalTrack"->{
					attributes.add("//Can't reverse scrollbarAlwaysDrawHorizontalTrack!")
					attributes.add("//scrollbarAlwaysDrawHorizontalTrack=$value")
				}
				"scrollbarAlwaysDrawVerticalTrack"->{
					attributes.add("//Can't reverse scrollbarAlwaysDrawVerticalTrack!")
					attributes.add("//scrollbarAlwaysDrawVerticalTrack=$value")
				}
				"stateListAnimator"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.LOLLIPOP){")
					attributes.add("\tval drawable=ContextCompat.getDrawable(context,${resourceRef(value)})")
					attributes.add("\tif(drawable is StateListAnimator){")
					attributes.add("\t\tstateListAnimator=drawable")
					attributes.add("\t}")
					attributes.add("}")
				}
				"transitionName"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.LOLLIPOP){\n")
					attributes.add("\ttransitionName=${resource(value)}\n")
					attributes.add("}")
				}
				"soundEffectsEnabled"->attributes.add("isSoundEffectsEnabled = $value")
				"tag"->attributes.add("tag = ${string(value)}")
				"textAlignment"->attributes.add("textAlignment = ${textAlignment(value)}")
				"textDirection"->attributes.add("textDirection = ${textDirection(value)}")
				"transformPivotX"->attributes.add("transformPivotX = ${dimen(value)}")
				"transformPivotY"->attributes.add("transformPivotY = ${dimen(value)}")
				"translationX"->attributes.add("translationX = ${dimen(value)}")
				"translationY"->attributes.add("translationY = ${dimen(value)}")
				"translationZ"->attributes.add("translationZ = ${dimen(value)}")
				"visibility"->attributes.add("visibility = ${visibility(value)}")
				"theme"->attributes.add("theme = ${resourceRef(value)}")
			}
		}
	}

}
