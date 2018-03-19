package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.expression.AttributeMethodExpression
import com.cz.layout2code.inflate.expression.AttributeMethodMultiParamsExpression
import com.cz.layout2code.inflate.expression.value.*
import com.cz.layout2code.inflate.item.ImportItem
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TextView all expressions---------------
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
			property("autoLinkMask"){ autoLink(it)}
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
			sdk=23
			property("breakStrategy"){ breakStrategy(it)}
		}

		attribute{
			field = "bufferType"
			attrType = arrayOf(AttrType.FLAG)
			methods("setText"){
				mutableListOf(StringValueExpression("text"),bufferType(it))
			}
		}
		uselessAttribute("capitalize")
		uselessAttribute("editable")

		attribute{
			field = "cursorVisible"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="isCursorVisible"
				kotlin="isCursorVisible"
				value { bool(it) }
			}
		}

		uselessAttribute("digits")
		uselessAttribute("phoneNumber")
		uselessAttribute("inputMethod")
		attribute{
			field = "drawablePadding"
			attrType = arrayOf(AttrType.DIMENSION)
			property("compoundDrawablePadding"){ dimen(it) }
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
			method("setCompoundDrawableTintList"){colorStateList(it)}
		}
		attribute{
			field = "drawableTintMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			property("compoundDrawableTintMode"){tintMode(it)}
		}

		uselessAttribute("editorExtras")

		attribute{
			field = "elegantTextHeight"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=21
			method("setElegantTextHeight"){bool(it)}
		}
		attribute{
			field = "ellipsize"
			attrType = arrayOf(AttrType.FLAG)
			property("ellipsize"){ellipsize(it)}
		}
		attribute{
			field = "ems"
			attrType = arrayOf(AttrType.INTEGER)
			method("setEms"){int(it)}
		}

		attribute{
			field = "enabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setEnabled"
				kotlin="isEnabled"
				value { bool(it) }
			}
		}
		uselessAttribute("fontFamily")
		attribute{
			field = "fontFeatureSettings"
			attrType = arrayOf(AttrType.STRING)
			sdk=21
			property("fontFeatureSettings"){string(it)}
		}
		attribute{
			field = "freezesText"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("freezesText"){bool(it)}
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			property("gravity"){gravity(it)}
		}
		attribute{
			field = "height"
			attrType = arrayOf(AttrType.DIMENSION)
			property("height"){dimen(it)}
		}
		attribute{
			field = "hint"
			attrType = arrayOf(AttrType.STRING)
			property("hint"){string(it)}
		}
		attribute{
			field = "hyphenationFrequency"
			attrType = arrayOf(AttrType.FLAG)
			sdk=23
			property("hyphenationFrequency"){hyphenationFrequency(it)}
		}
		uselessAttribute("imeActionId")
		uselessAttribute("imeActionLabel")
		attribute{
			field = "imeOptions"
			attrType = arrayOf(AttrType.FLAG)
			property("imeOptions"){imeOptions(it)}
		}
		attribute{
			field = "includeFontPadding"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("includeFontPadding"){bool(it)}
		}
		attribute{
			field = "inputType"
			attrType = arrayOf(AttrType.FLAG)
			property("inputType"){inputType(it)}
		}
		attribute{
			field = "justificationMode"
			attrType = arrayOf(AttrType.FLAG)
			sdk=26
			property("justificationMode"){justificationMode(it)}
		}
		attribute{
			field = "letterSpacing"
			attrType = arrayOf(AttrType.FLOAT)
			sdk=21
			property("letterSpacing"){float(it)}
		}
		uselessAttribute("lineSpacingExtra")
		uselessAttribute("lineSpacingMultiplier")
		attribute{
			field = "lines"
			attrType = arrayOf(AttrType.INTEGER)
			property("lines"){int(it)}
		}
		attribute{
			field = "linksClickable"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("linksClickable"){bool(it)}
		}
		attribute{
			field = "marqueeRepeatLimit"
			attrType = arrayOf(AttrType.INTEGER)
			property("marqueeRepeatLimit"){int(it)}
		}
		attribute{
			field = "maxEms"
			attrType = arrayOf(AttrType.INTEGER)
			property("maxEms"){int(it)}
		}

		attribute{
			field = "maxHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("maxHeight"){dimen(it)}
		}
		attribute{
			field = "maxLength"
			attrType = arrayOf(AttrType.INTEGER)
			expression {
				import { mutableListOf(ImportItem("android.text.InputFilter")) }
				javaExpression { matcher,value->
					"setFilters(new InputFilter[]{new InputFilter.LengthFilter((${int(value).getJavaExpression(matcher)})})"
				}
				kotlinExpression { matcher,value->
					"setFilters(filters = arrayOf<InputFilter>(InputFilter.LengthFilter(${int(value).getKotlinExpression(matcher)}))"
				}
			}
		}
		attribute{
			field = "maxLines"
			attrType = arrayOf(AttrType.INTEGER)
			property("maxLines"){int(it)}
		}
		attribute{
			field = "maxWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("maxWidth"){dimen(it)}
		}
		attribute{
			field = "minEms"
			attrType = arrayOf(AttrType.INTEGER)
			property("minEms"){int(it)}
		}
		attribute{
			field = "minHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("minHeight"){dimen(it)}
		}
		attribute{
			field = "minLines"
			attrType = arrayOf(AttrType.INTEGER)
			property("minLines"){int(it)}
		}
		attribute{
			field = "minWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("minWidth"){dimen(it)}
		}
		uselessAttribute("numeric")
		uselessAttribute("password")
		attribute{
			field = "privateImeOptions"
			attrType = arrayOf(AttrType.STRING)
			property("privateImeOptions"){string(it)}
		}

		attribute{
			field = "scrollHorizontally"
			attrType = arrayOf(AttrType.BOOLEAN)
			method("setHorizontallyScrolling"){bool(it)}
		}

		attribute{
			field = "selectAllOnFocus"
			attrType = arrayOf(AttrType.BOOLEAN)
			method("setSelectAllOnFocus"){bool(it)}
		}

		attribute{
			field = "singleLine"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("singleLine"){bool(it)}
		}

		attribute{
			field = "text"
			attrType = arrayOf(AttrType.STRING)
			property("text"){string(it)}
		}
		attribute{
			field = "textAllCaps"
			attrType = arrayOf(AttrType.BOOLEAN)
			method("setAllCaps"){bool(it)}
		}
		attribute{
			field = "textAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=23
			method("setTextAppearance"){resource(it)}
		}
		attribute{
			field = "textColor"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			property("textColor"){color(it)}
		}
		attribute{
			field = "textColorHighlight"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			property("highlightColor"){color(it)}
		}
		attribute{
			field = "textColorHint"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			method("setHintTextColor"){color(it)}
		}
		attribute{
			field = "textColorLink"
			attrType = arrayOf(AttrType.REFERENCE, AttrType.COLOR)
			method("setLinkTextColor"){color(it)}
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
			method("setTextIsSelectable"){color(it)}
		}
		attribute{
			field = "textScaleX"
			attrType = arrayOf(AttrType.FLOAT)
			property("textScaleX"){float(it)}
		}
		uselessAttribute("textSelectHandle")
		uselessAttribute("textSelectHandleLeft")
		uselessAttribute("textSelectHandleRight")
		attribute{
			field = "textSize"
			attrType = arrayOf(AttrType.DIMENSION)
			property("textSize"){dimen(it)}
		}
		attribute{
			field = "textStyle"
			attrType = arrayOf(AttrType.FLAG)
			methods("setTypeface"){
				mutableListOf(ClassFieldExpression("typeface"),textStyle(it))
			}
		}
		attribute{
			field = "typeface"
			attrType = arrayOf(AttrType.FLAG)
			property("typeface"){typeface(it)}
		}
		attribute{
			field = "width"
			attrType = arrayOf(AttrType.DIMENSION)
			property("width"){dimen(it)}
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
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		addDrawableAttribute(element)
		addShadowAttribute(element)
	}

	private fun addShadowAttribute(element: ViewNode) {
		var shadowRadius = element.attributes.find { it.name == "shadowRadius" }
		var shadowColor = element.attributes.find { it.name == "shadowColor" }
		var shadowDx = element.attributes.find { it.name == "shadowDx" }
		var shadowDy = element.attributes.find { it.name == "shadowDy" }
		//应用属性集
		applyAttributes(shadowRadius,shadowColor,shadowDx,shadowDy)

		if (null != shadowColor || null != shadowDx || null != shadowDy || null != shadowRadius) {
			val expression= AttributeMethodMultiParamsExpression("setShadowLayer"){
				mutableListOf(if (null == shadowRadius) StringValueExpression("0f") else float(shadowRadius.value))
				mutableListOf(expression(shadowRadius?.value, FloatFieldExpression("0")){ float(it) },
						expression(shadowDx?.value, FloatFieldExpression("0")){ float(it) },
						expression(shadowDy?.value, FloatFieldExpression("0")){ float(it) },
						expression(shadowColor?.value, FloatFieldExpression("0")){ float(it) })
			}
			expressions.add(expression.callback(String()))
		}
	}

	private fun addDrawableAttribute(element: ViewNode) {
		val drawableLeft = element.attributes.find { it.name == "drawableLeft" }
		var drawableTop = element.attributes.find { it.name == "drawableTop" }
		var drawableRight = element.attributes.find { it.name == "drawableRight" }
		var drawableBottom = element.attributes.find { it.name == "drawableBottom" }
		//应用属性集
		applyAttributes(drawableLeft,drawableTop,drawableRight,drawableBottom)
		//添加属性
		if (null != drawableLeft || null != drawableTop || null != drawableRight || null != drawableBottom) {
			val expression=AttributeMethodMultiParamsExpression("setCompoundDrawablesWithIntrinsicBounds"){
				mutableListOf(expression(drawableLeft?.value,IntFieldExpression("0")){ resourceRef(it) },
						expression(drawableTop?.value,IntFieldExpression("0")){ resourceRef(it) },
						expression(drawableRight?.value,IntFieldExpression("0")){ resourceRef(it) },
						expression(drawableBottom?.value,IntFieldExpression("0")){ resourceRef(it) })
			}
			expressions.add(expression.callback(String()))
		}
	}

	private inline fun expression(value:String?,
								  default:ElementExpression,
								  action:(String)->ElementExpression):ElementExpression=if(null!=value) action(value) else default

}
