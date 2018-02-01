package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.item.ViewAttributeItem
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextView all attributes---------------
 * @attr ref android.R.styleable#TextView_text
 * @attr ref android.R.styleable#TextView_bufferType
 * @attr ref android.R.styleable#TextView_hint
 * @attr ref android.R.styleable#TextView_textColor
 * @attr ref android.R.styleable#TextView_textColorHighlight
 * @attr ref android.R.styleable#TextView_textColorHint
 * @attr ref android.R.styleable#TextView_textAppearance
 * @attr ref android.R.styleable#TextView_textColorLink
 * @attr ref android.R.styleable#TextView_textSize
 * @attr ref android.R.styleable#TextView_textScaleX
 * @attr ref android.R.styleable#TextView_fontFamily
 * @attr ref android.R.styleable#TextView_typeface
 * @attr ref android.R.styleable#TextView_textStyle
 * @attr ref android.R.styleable#TextView_cursorVisible
 * @attr ref android.R.styleable#TextView_maxLines
 * @attr ref android.R.styleable#TextView_maxHeight
 * @attr ref android.R.styleable#TextView_lines
 * @attr ref android.R.styleable#TextView_height
 * @attr ref android.R.styleable#TextView_minLines
 * @attr ref android.R.styleable#TextView_minHeight
 * @attr ref android.R.styleable#TextView_maxEms
 * @attr ref android.R.styleable#TextView_maxWidth
 * @attr ref android.R.styleable#TextView_ems
 * @attr ref android.R.styleable#TextView_width
 * @attr ref android.R.styleable#TextView_minEms
 * @attr ref android.R.styleable#TextView_minWidth
 * @attr ref android.R.styleable#TextView_gravity
 * @attr ref android.R.styleable#TextView_scrollHorizontally
 * @attr ref android.R.styleable#TextView_password
 * @attr ref android.R.styleable#TextView_singleLine
 * @attr ref android.R.styleable#TextView_selectAllOnFocus
 * @attr ref android.R.styleable#TextView_includeFontPadding
 * @attr ref android.R.styleable#TextView_maxLength
 * @attr ref android.R.styleable#TextView_shadowColor
 * @attr ref android.R.styleable#TextView_shadowDx
 * @attr ref android.R.styleable#TextView_shadowDy
 * @attr ref android.R.styleable#TextView_shadowRadius
 * @attr ref android.R.styleable#TextView_autoLink
 * @attr ref android.R.styleable#TextView_linksClickable
 * @attr ref android.R.styleable#TextView_numeric
 * @attr ref android.R.styleable#TextView_digits
 * @attr ref android.R.styleable#TextView_phoneNumber
 * @attr ref android.R.styleable#TextView_inputMethod
 * @attr ref android.R.styleable#TextView_capitalize
 * @attr ref android.R.styleable#TextView_autoText
 * @attr ref android.R.styleable#TextView_editable
 * @attr ref android.R.styleable#TextView_freezesText
 * @attr ref android.R.styleable#TextView_ellipsize
 * @attr ref android.R.styleable#TextView_drawableTop
 * @attr ref android.R.styleable#TextView_drawableBottom
 * @attr ref android.R.styleable#TextView_drawableRight
 * @attr ref android.R.styleable#TextView_drawableLeft
 * @attr ref android.R.styleable#TextView_drawableStart
 * @attr ref android.R.styleable#TextView_drawableEnd
 * @attr ref android.R.styleable#TextView_drawablePadding
 * @attr ref android.R.styleable#TextView_drawableTint
 * @attr ref android.R.styleable#TextView_drawableTintMode
 * @attr ref android.R.styleable#TextView_lineSpacingExtra
 * @attr ref android.R.styleable#TextView_lineSpacingMultiplier
 * @attr ref android.R.styleable#TextView_marqueeRepeatLimit
 * @attr ref android.R.styleable#TextView_inputType
 * @attr ref android.R.styleable#TextView_imeOptions
 * @attr ref android.R.styleable#TextView_privateImeOptions
 * @attr ref android.R.styleable#TextView_imeActionLabel
 * @attr ref android.R.styleable#TextView_imeActionId
 * @attr ref android.R.styleable#TextView_editorExtras
 * @attr ref android.R.styleable#TextView_elegantTextHeight
 * @attr ref android.R.styleable#TextView_letterSpacing
 * @attr ref android.R.styleable#TextView_fontFeatureSettings
 * @attr ref android.R.styleable#TextView_breakStrategy
 * @attr ref android.R.styleable#TextView_hyphenationFrequency
 * @attr ref android.R.styleable#TextView_autoSizeTextType
 * @attr ref android.R.styleable#TextView_autoSizeMinTextSize
 * @attr ref android.R.styleable#TextView_autoSizeMaxTextSize
 * @attr ref android.R.styleable#TextView_autoSizeStepGranularity
 * @attr ref android.R.styleable#TextView_autoSizePresetSizes
 *
 */
open class TextView : View() {
	init {
		uselessAttribute("allowUndo")
		attribute{
			field = "autoLink"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.text.util.Linkify")
			kotlinMethod { "autoLinkMask = ${autoLink(it)}" }
			javaMethod{ "setAutoLinkMask(${autoLink(it)})" }
		}
		uselessAttribute("autoSizeTextType")
		uselessAttribute("autoSizeMaxTextSize")
		uselessAttribute("autoSizeMinTextSize")
		uselessAttribute("autoSizePresetSizes")
		uselessAttribute("autoSizeStepGranularity")
		uselessAttribute("autoSizePresetSizes")
		uselessAttribute("autoText")
		attribute{
			field = "breakStrategy"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.text.Layout")
			sdk=23
			kotlinMethod { "breakStrategy = ${breakStrategy(it)}" }
			javaMethod{ "setBreakStrategy(${breakStrategy(it)})" }
		}

		attribute{
			field = "bufferType"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.widget.TextView.BufferType")
			kotlinMethod { "setText(text, ${bufferType(it)})" }
			javaMethod{ "setText(text, ${bufferType(it)})" }
		}
		uselessAttribute("capitalize")
		uselessAttribute("editable")

		attribute{
			field = "cursorVisible"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isCursorVisible = ${bool(it)}" }
			javaMethod{ "setCursorVisible(${bool(it)})" }
		}

		uselessAttribute("digits")
		uselessAttribute("phoneNumber")
		uselessAttribute("inputMethod")
		attribute{
			field = "drawablePadding"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "compoundDrawablePadding = ${dimen(it)}" }
			javaMethod{ "setCompoundDrawablePadding(${dimen(it)})" }
		}
		uselessAttribute("drawableStart")
		uselessAttribute("drawableEnd")

		//-------------------------------------------------
		//以下属性将被组合设定
		//-------------------------------------------------
//		attribute{
//			field = "drawableLeft"
//			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
//			kotlinMethod { "drawableLeft = ${resource(it)}" }
//			javaMethod{ "setDrawableLeft(${resource(it)})" }
//		}
//		attribute{
//			field = "drawableTop"
//			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
//			kotlinMethod { "drawableTop = ${resource(it)}" }
//			javaMethod{ "setDrawableTop(${resource(it)})" }
//		}
//		attribute{
//			field = "drawableRight"
//			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
//			kotlinMethod { "drawableRight = ${resource(it)}" }
//			javaMethod{ "setDrawableRight(${resource(it)})" }
//		}
//		attribute{
//			field = "drawableBottom"
//			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
//			kotlinMethod { "drawableBottom = ${resource(it)}" }
//			javaMethod{ "setDrawableBottom(${resource(it)})" }
//		}


//		attribute{
//			field = "shadowColor"
//			attrType = arrayOf(AttrType.COLOR)
//			kotlinMethod { "shadowColor = ${color(it)}" }
//			javaMethod{ "setShadowColor(${color(it)})" }
//		}
//		attribute{
//			field = "shadowDx"
//			attrType = arrayOf(AttrType.FLOAT)
//			kotlinMethod { "shadowDx = ${float(it)}" }
//			javaMethod{ "setShadowDx(${float(it)})" }
//		}
//		attribute{
//			field = "shadowDy"
//			attrType = arrayOf(AttrType.FLOAT)
//			kotlinMethod { "shadowDy = ${float(it)}" }
//			javaMethod{ "setShadowDy(${float(it)})" }
//		}
//		attribute{
//			field = "shadowRadius"
//			attrType = arrayOf(AttrType.FLOAT)
//			kotlinMethod { "shadowRadius = ${float(it)}" }
//			javaMethod{ "setShadowRadius(${float(it)})" }
//		}
		//-------------------------------------------------
		//-------------------------------------------------

		attribute{
			field = "drawableTint"
			attrType = arrayOf(AttrType.COLOR)
			kotlinMethod { "setCompoundDrawableTintList(${colorStateList(it)})" }
			javaMethod{ "setCompoundDrawableTintList(${colorStateList(it)})" }
		}
		attribute{
			field = "drawableTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			importList= arrayOf("android.graphics.PorterDuff")
			kotlinMethod { "compoundDrawableTintMode = ${tintMode(it)}" }
			javaMethod{ "setCompoundDrawableTintMode(${tintMode(it)})" }
		}

		uselessAttribute("editorExtras")

		attribute{
			field = "elegantTextHeight"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			kotlinMethod { "setElegantTextHeight${bool(it)}" }
			javaMethod{ "setElegantTextHeight${bool(it)}" }
		}
		attribute{
			field = "ellipsize"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.text.TextUtils")
			kotlinMethod { "ellipsize = ${ellipsize(it)}" }
			javaMethod{ "setEllipsize(${ellipsize(it)})" }
		}
		attribute{
			field = "ems"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "setEms(${int(it)})" }
			javaMethod{ "setEms(${int(it)})" }
		}

		attribute{
			field = "enabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "isEnabled = ${bool(it)}" }
			javaMethod{ "setEnabled(${bool(it)})" }
		}
		uselessAttribute("fontFamily")
		attribute{
			field = "fontFeatureSettings"
			attrType = arrayOf(AttrType.STRING)
			sdk=21
			kotlinMethod { "fontFeatureSettings = ${string(it)}" }
			javaMethod{ "setFontFeatureSettings(${string(it)})" }
		}
		attribute{
			field = "freezesText"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "freezesText = ${bool(it)}" }
			javaMethod{ "setFreezesText(${bool(it)})" }
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.view.Gravity")
			kotlinMethod { "gravity = ${gravity(it)}" }
			javaMethod{ "setGravity(${gravity(it)})" }
		}
		attribute{
			field = "height"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "height = ${dimen(it)}" }
			javaMethod{ "setHeight(${dimen(it)})" }
		}
		attribute{
			field = "hint"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "hint = ${string(it)}" }
			javaMethod{ "setHint(${string(it)})" }
		}
		attribute{
			field = "hyphenationFrequency"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.text.Layout")
			sdk=23
			kotlinMethod { "hyphenationFrequency = ${hyphenationFrequency(it)}" }
			javaMethod{ "setHyphenationFrequency(${hyphenationFrequency(it)})" }
		}
		uselessAttribute("imeActionId")
		uselessAttribute("imeActionLabel")
		attribute{
			field = "imeOptions"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.view.inputmethod.EditorInfo")
			kotlinMethod { "imeOptions = ${imeOptions(it)}" }
			javaMethod{ "setImeOptions(${imeOptions(it)})" }
		}
		attribute{
			field = "includeFontPadding"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "includeFontPadding = ${bool(it)}" }
			javaMethod{ "setIncludeFontPadding(${bool(it)})" }
		}
		attribute{
			field = "inputType"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.text.InputType")
			kotlinMethod { "inputType = ${inputType(it)}" }
			javaMethod{ "setInputType(${inputType(it)})" }
		}
		attribute{
			field = "justificationMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=26
			importList= arrayOf("android.text.Layout")
			kotlinMethod { "justificationMode = ${justificationMode(it)}" }
			javaMethod{ "setJustificationMode(${justificationMode(it)})" }
		}
		attribute{
			field = "letterSpacing"
			attrType = arrayOf(AttrType.FLOAT)
			sdk=21
			kotlinMethod { "letterSpacing = ${float(it)}" }
			javaMethod{ "setLetterSpacing(${float(it)})" }
		}
		uselessAttribute("lineSpacingExtra")
		uselessAttribute("lineSpacingMultiplier")
		attribute{
			field = "lines"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "lines = ${int(it)}" }
			javaMethod{ "setLines(${int(it)})" }
		}
		attribute{
			field = "linksClickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "linksClickable = ${bool(it)}" }
			javaMethod{ "setLinksClickable(${bool(it)})" }
		}
		attribute{
			field = "marqueeRepeatLimit"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "marqueeRepeatLimit = ${int(it)}" }
			javaMethod{ "setMarqueeRepeatLimit(${int(it)})" }
		}
		attribute{
			field = "maxEms"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "maxEms = ${int(it)}" }
			javaMethod{ "setMaxEms(${int(it)})" }
		}

		attribute{
			field = "maxHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "maxHeight = ${dimen(it)}" }
			javaMethod{ "setMaxHeight(${dimen(it)})" }
		}
		attribute{
			field = "maxLength"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "setFilters(filters = arrayOf<InputFilter>(InputFilter.LengthFilter(${int(it)}))" }
			javaMethod{ "textView.setFilters(new InputFilter[]{new InputFilter.LengthFilter((${int(it)})})" }
		}
		attribute{
			field = "maxLines"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "maxLines = ${int(it)}" }
			javaMethod{ "setMaxLines(${int(it)})" }
		}
		attribute{
			field = "maxWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "maxWidth = ${dimen(it)}" }
			javaMethod{ "setMaxWidth(${dimen(it)})" }
		}
		attribute{
			field = "minEms"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "minEms = ${int(it)}" }
			javaMethod{ "setMinEms(${int(it)})" }
		}
		attribute{
			field = "minHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "minHeight = ${dimen(it)}" }
			javaMethod{ "setMinHeight(${dimen(it)})" }
		}
		attribute{
			field = "minLines"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "minLines = ${int(it)}" }
			javaMethod{ "setMinLines(${int(it)})" }
		}
		attribute{
			field = "minWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "minWidth = ${dimen(it)}" }
			javaMethod{ "setMinWidth(${dimen(it)})" }
		}
		uselessAttribute("numeric")
		uselessAttribute("password")
		attribute{
			field = "privateImeOptions"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "privateImeOptions = ${string(it)}" }
			javaMethod{ "setPrivateImeOptions(${string(it)})" }
		}

		attribute{
			field = "scrollHorizontally"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setHorizontallyScrolling(${bool(it)})" }
			javaMethod{ "setHorizontallyScrolling(${bool(it)})" }
		}

		attribute{
			field = "selectAllOnFocus"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setSelectAllOnFocus(${bool(it)})" }
			javaMethod{ "setSelectAllOnFocus(${bool(it)})" }
		}

		attribute{
			field = "singleLine"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "singleLine = ${bool(it)}" }
			javaMethod{ "setSingleLine(${bool(it)})" }
		}

		attribute{
			field = "text"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod { "text = ${string(it)}" }
			javaMethod{ "setText(${string(it)})" }
		}
		attribute{
			field = "textAllCaps"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setAllCaps(${bool(it)})" }
			javaMethod{ "setAllCaps(${bool(it)})" }
		}
		attribute{
			field = "textAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=23
			kotlinMethod { "setTextAppearance({resource(it)})" }
			javaMethod{ "setTextAppearance(${resource(it)})" }
		}
		attribute{
			field = "textColor"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			kotlinMethod { "textColor = ${color(it)}" }
			javaMethod{ "setTextColor(${color(it)}" }
		}
		attribute{
			field = "textColorHighlight"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			kotlinMethod { "highlightColor = ${color(it)}" }
			javaMethod{ "setHighlightColor(${color(it)})" }
		}
		attribute{
			field = "textColorHint"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			kotlinMethod { "setHintTextColor(${color(it)})" }
			javaMethod{ "setHintTextColor(${color(it)})" }
		}
		attribute{
			field = "textColorLink"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			kotlinMethod { "setLinkTextColor(${color(it)})" }
			javaMethod{ "setLinkTextColor(${color(it)})" }
		}
		uselessAttribute("textCursorDrawable")
		uselessAttribute("textEditNoPasteWindowLayout")
		uselessAttribute("textEditPasteWindowLayout")
		uselessAttribute("textEditSideNoPasteWindowLayout")
		uselessAttribute("textEditSidePasteWindowLayout")
		uselessAttribute("textEditSuggestionContainerLayout")
		uselessAttribute("textEditSuggestionHighlightStyle")
		uselessAttribute("textEditSuggestionItemLayout")

		attribute{
			field = "textIsSelectable"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "setTextIsSelectable(${bool(it)})" }
			javaMethod{ "setTextIsSelectable(${bool(it)})" }
		}
		attribute{
			field = "textScaleX"
			attrType = arrayOf(AttrType.FLOAT)
			kotlinMethod { "textScaleX = ${float(it)}" }
			javaMethod{ "setTextScaleX(${float(it)})" }
		}
		uselessAttribute("textSelectHandle")
		uselessAttribute("textSelectHandleLeft")
		uselessAttribute("textSelectHandleRight")
		attribute{
			field = "textSize"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "textSize = ${dimen(it)}" }
			javaMethod{ "setTextSize(${dimen(it)})" }
		}
		attribute{
			field = "textStyle"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.graphics.Typeface")
			kotlinMethod { "setTypeface(typeface, ${textStyle(it)})" }
			javaMethod{ "setTypeface(typeface, ${textStyle(it)})" }
		}
		attribute{
			field = "typeface"
			attrType = arrayOf(AttrType.FLAG)
			importList= arrayOf("android.graphics.Typeface")
			kotlinMethod { "typeface = ${typeface(it)}" }
			javaMethod{ "setTypeface(${typeface(it)})" }
		}
		attribute{
			field = "width"
			attrType = arrayOf(AttrType.DIMENSION)
			kotlinMethod { "width = ${dimen(it)}" }
			javaMethod{ "setWidth(${dimen(it)})" }
		}
	}
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="textView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedTextView"
	/**
	 * 解析TextView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
		addDrawableAttribute(element)
		addShadowAttribute(element)
	}

	private fun addShadowAttribute(element: Element) {
		var shadowRadius = element.attributes.find { it.name == "shadowRadius" }?.value
		var shadowColor = element.attributes.find { it.name == "shadowColor" }?.value
		var shadowDx = element.attributes.find { it.name == "shadowDx" }?.value
		var shadowDy = element.attributes.find { it.name == "shadowDy" }?.value
		if (null != shadowColor || null != shadowDx || null != shadowDy || null != shadowRadius) {
			addMultiAttributeItems("setShadowLayer",
					if (null == shadowRadius) "0f" else float(shadowRadius),
					if (null == shadowDx) "0f" else float(shadowDx),
					if (null == shadowDy) "0f" else float(shadowDy),
					if (null == shadowColor) "0" else int(shadowColor))
		}
	}

	private fun addDrawableAttribute(element: Element) {
		val drawableLeft = element.attributes.find { it.name == "drawableLeft" }?.value
		var drawableTop = element.attributes.find { it.name == "drawableTop" }?.value
		var drawableRight = element.attributes.find { it.name == "drawableRight" }?.value
		var drawableBottom = element.attributes.find { it.name == "drawableBottom" }?.value
		if (null != drawableLeft || null != drawableTop || null != drawableRight || null != drawableBottom) {
			addMultiAttributeItems("setCompoundDrawablesWithIntrinsicBounds",
					if (null == drawableLeft) "0" else resourceRef(drawableLeft),
					if (null == drawableTop) "0" else resourceRef(drawableTop),
					if (null == drawableRight) "0" else resourceRef(drawableRight),
					if (null == drawableBottom) "0" else resourceRef(drawableBottom))
		}
	}

}
