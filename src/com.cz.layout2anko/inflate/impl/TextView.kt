package com.cz.layout2anko.inflate.impl

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
	 * 解析TextView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"text"->{
				
				}
				"bufferType"->{
				
				}
				"hint"->{
				
				}
				"textColor"->{
				
				}
				"textColorHighlight"->{
				
				}
				"textColorHint"->{
				
				}
				"textAppearance"->{
				
				}
				"textColorLink"->{
				
				}
				"textSize"->{
				
				}
				"textScaleX"->{
				
				}
				"fontFamily"->{
				
				}
				"typeface"->{
				
				}
				"textStyle"->{
				
				}
				"cursorVisible"->{
				
				}
				"maxLines"->{
				
				}
				"maxHeight"->{
				
				}
				"lines"->{
				
				}
				"height"->{
				
				}
				"minLines"->{
				
				}
				"minHeight"->{
				
				}
				"maxEms"->{
				
				}
				"maxWidth"->{
				
				}
				"ems"->{
				
				}
				"width"->{
				
				}
				"minEms"->{
				
				}
				"minWidth"->{
				
				}
				"gravity"->{
				
				}
				"scrollHorizontally"->{
				
				}
				"password"->{
				
				}
				"singleLine"->{
				
				}
				"selectAllOnFocus"->{
				
				}
				"includeFontPadding"->{
				
				}
				"maxLength"->{
				
				}
				"shadowColor"->{
				
				}
				"shadowDx"->{
				
				}
				"shadowDy"->{
				
				}
				"shadowRadius"->{
				
				}
				"autoLink"->{
				
				}
				"linksClickable"->{
				
				}
				"numeric"->{
				
				}
				"digits"->{
				
				}
				"phoneNumber"->{
				
				}
				"inputMethod"->{
				
				}
				"capitalize"->{
				
				}
				"autoText"->{
				
				}
				"editable"->{
				
				}
				"freezesText"->{
				
				}
				"ellipsize"->{
				
				}
				"drawableTop"->{
				
				}
				"drawableBottom"->{
				
				}
				"drawableRight"->{
				
				}
				"drawableLeft"->{
				
				}
				"drawableStart"->{
				
				}
				"drawableEnd"->{
				
				}
				"drawablePadding"->{
				
				}
				"drawableTint"->{
				
				}
				"drawableTintMode"->{
				
				}
				"lineSpacingExtra"->{
				
				}
				"lineSpacingMultiplier"->{
				
				}
				"marqueeRepeatLimit"->{
				
				}
				"inputType"->{
				
				}
				"imeOptions"->{
				
				}
				"privateImeOptions"->{
				
				}
				"imeActionLabel"->{
				
				}
				"imeActionId"->{
				
				}
				"editorExtras"->{
				
				}
				"elegantTextHeight"->{
				
				}
				"letterSpacing"->{
				
				}
				"fontFeatureSettings"->{
				
				}
				"breakStrategy"->{
				
				}
				"hyphenationFrequency"->{
				
				}
				"autoSizeTextType"->{
				
				}
				"autoSizeMinTextSize"->{
				
				}
				"autoSizeMaxTextSize"->{
				
				}
				"autoSizeStepGranularity"->{
				
				}
				"autoSizePresetSizes"->{
				
				}
			}
		}
	}

}
