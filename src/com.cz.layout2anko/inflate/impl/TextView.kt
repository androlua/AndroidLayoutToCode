package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ImportItem
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.item.ViewMultiMethodConvertItem
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
		//setShadowLayer
		attributes.add(ViewMultiMethodConvertItem("setShadowLayer",element.attributes, arrayOf("shadowColor","shadowDx","shadowDy","shadowRadius"),16))
		attributes.add(ViewMultiMethodConvertItem("setCompoundDrawablesWithIntrinsicBounds",element.attributes,arrayOf("drawableLeft","drawableTop","drawableRight", "drawableBottom")))
		attributes.add(ViewMultiMethodConvertItem("setCompoundDrawablesRelative",element.attributes,arrayOf("drawableStart","drawableTop","drawableEnd", "drawableBottom")))
		attributes.add(ViewMultiMethodConvertItem("setLineSpacing",element.attributes,arrayOf("lineSpacingExtra","lineSpacingMultiplier")))
		attributes.add(ViewMultiMethodConvertItem("setImeActionLabel",element.attributes,arrayOf("imeActionLabel","imeActionId")))
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"text"->attributes.add(ViewConvertItem("text",string(value)))
				"bufferType"->{
					importLists.add(ImportItem("android.widget.TextView.BufferType"))
					attributes.add(ViewMethodConvertItem("setText(text, ${bufferType(value)})"))
				}
				"hint"->attributes.add(ViewConvertItem("hint",string(value)))
				"textColor"->attributes.add(ViewConvertItem("textColor{",color(value)))
				"textColorHighlight"->attributes.add(ViewConvertItem("highlightColor",color(value)))
				"textColorHint"->attributes.add(ViewMethodConvertItem("setHintTextColor(${color(value)})"))
				"textAppearance"->{
					attributes.add(ViewMethodConvertItem("setTextAppearance(${resourceRef(value)})",23))
				}
				"textColorLink"->attributes.add(ViewMethodConvertItem("setLinkTextColor(${color(value)})"))
				"textSize"->attributes.add(ViewConvertItem("textSize=",float(value)))
				"textScaleX"->attributes.add(ViewConvertItem("textScaleX",float(value)))
				"fontFamily"->ViewConvertItem(name,value,false)
				"typeface"->{
					importLists.add(ImportItem("android.graphics.Typeface"))
					attributes.add(ViewConvertItem("typeface",typeface(value)))
				}
				"textStyle"->{
					importLists.add(ImportItem("android.graphics.Typeface"))
					attributes.add(ViewMethodConvertItem("setTypeface(typeface, ${textStyle(value)})"))
				}
				"cursorVisible"->attributes.add(ViewConvertItem("isCursorVisible","setCursorVisible",bool(value)))
				"maxLines"->attributes.add(ViewConvertItem("maxLines",int(value)))
				"maxHeight"->attributes.add(ViewConvertItem("maxHeight",dimen(value)))
				"lines"->attributes.add(ViewConvertItem("lines",int(value)))
				"height"->attributes.add(ViewConvertItem("height",dimen(value)))
				"minLines"->attributes.add(ViewConvertItem("minLines",int(value)))
				"minHeight"->attributes.add(ViewConvertItem("minLines",dimen(value)))
				"maxEms"->attributes.add(ViewConvertItem("maxEms",int(value)))
				"maxWidth"->attributes.add(ViewConvertItem("maxWidth",dimen(value)))
				"ems"->attributes.add(ViewMethodConvertItem("setEms(${int(value)})"))
				"width"->attributes.add(ViewConvertItem("width",dimen(value)))
				"minEms"->attributes.add(ViewConvertItem("minEms",int(value)))
				"minWidth"->attributes.add(ViewConvertItem("minWidth",dimen(value)))
				"gravity"->{
					importLists.add(ImportItem("android.view.Gravity"))
					attributes.add(ViewConvertItem("gravity",gravity(value)))
				}
				"scrollHorizontally"->attributes.add(ViewMethodConvertItem("setHorizontallyScrolling(${bool(value)})"))
				"password"->attributes.add(ViewConvertItem(name,value,false))
				"singleLine"->attributes.add(ViewConvertItem("singleLine",bool(value)))
				"selectAllOnFocus"->attributes.add(ViewMethodConvertItem("setSelectAllOnFocus(${bool(value)})"))
				"includeFontPadding"->attributes.add(ViewConvertItem("includeFontPadding",bool(value)))
				"maxLength"->attributes.add(ViewMethodConvertItem("setFilters(filters = arrayOf<InputFilter>(InputFilter.LengthFilter(${int(value)}))"))

				"autoLink"->{
					importLists.add(ImportItem("android.text.util.Linkify"))
					attributes.add(ViewMethodConvertItem("setAutoLinkMask(${autoLink(value)})"))
				}
				"linksClickable"->attributes.add(ViewConvertItem("linksClickable",bool(value)))
				"numeric"->attributes.add(ViewConvertItem("inputType",bool(value)))
				"digits","phoneNumber","inputMethod",
				"capitalize","autoText","editable"->attributes.add(ViewConvertItem(name,value,false))
				"freezesText"->attributes.add(ViewConvertItem("freezesText",bool(value)))
				"ellipsize"->{
					importLists.add(ImportItem("android.text.TextUtils"))
					attributes.add(ViewConvertItem("ellipsize",ellipsize(value)))
				}
				"drawablePadding"->attributes.add(ViewConvertItem("compoundDrawablePadding",dimen(value)))
				"drawableTint"->attributes.add(ViewMethodConvertItem("setCompoundDrawableTintList(${colorStateList(value)})"))
				"drawableTintMode"->{
					importLists.add(ImportItem("android.graphics.PorterDuff"))
					attributes.add(ViewMethodConvertItem("setCompoundDrawableTintMode(${drawableTintMode(value)})"))
				}
				"marqueeRepeatLimit"->attributes.add(ViewConvertItem("marqueeRepeatLimit",dimen(value)))
				"inputType"->{
					importLists.add(ImportItem("android.text.InputType"))
					attributes.add(ViewConvertItem("inputType",inputType(value)))
				}
				"imeOptions"->{
					importLists.add(ImportItem("android.view.inputmethod.EditorInfo"))
					attributes.add(ViewConvertItem("imeOptions",imeOptions(value)))
				}
				"privateImeOptions"->attributes.add(ViewConvertItem("privateImeOptions",string(value)))

				"editorExtras"->attributes.add(ViewMethodConvertItem("setInputExtras${resourceRef(value)}"))
				"elegantTextHeight"->attributes.add(ViewMethodConvertItem("setElegantTextHeight${bool(value)}",21))
				"letterSpacing"->attributes.add(ViewConvertItem("letterSpacing",dimen(value),21))
				"fontFeatureSettings"->attributes.add(ViewConvertItem("fontFeatureSettings",string(value),21))
				"breakStrategy"->{
					importLists.add(ImportItem("android.text.Layout"))
					attributes.add(ViewConvertItem("breakStrategy",breakStrategy(value),23))
				}
				"hyphenationFrequency"->{
					importLists.add(ImportItem("android.text.Layout"))
					attributes.add(ViewConvertItem("hyphenationFrequency",hyphenationFrequency(value),23))
				}
				"autoSizeTextType","autoSizeMinTextSize",
				"autoSizeMaxTextSize","autoSizeStepGranularity","autoSizePresetSizes"->attributes.add(ViewConvertItem(name,value,false))
			}
		}
	}

}
