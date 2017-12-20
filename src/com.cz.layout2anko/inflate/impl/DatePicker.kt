package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------DatePicker all attributes---------------
 * @attr ref android.R.styleable#DatePicker_startYear
 * @attr ref android.R.styleable#DatePicker_endYear
 * @attr ref android.R.styleable#DatePicker_maxDate
 * @attr ref android.R.styleable#DatePicker_minDate
 * @attr ref android.R.styleable#DatePicker_spinnersShown
 * @attr ref android.R.styleable#DatePicker_calendarViewShown
 * @attr ref android.R.styleable#DatePicker_dayOfWeekBackground
 * @attr ref android.R.styleable#DatePicker_dayOfWeekTextAppearance
 * @attr ref android.R.styleable#DatePicker_headerBackground
 * @attr ref android.R.styleable#DatePicker_headerMonthTextAppearance
 * @attr ref android.R.styleable#DatePicker_headerDayOfMonthTextAppearance
 * @attr ref android.R.styleable#DatePicker_headerYearTextAppearance
 * @attr ref android.R.styleable#DatePicker_yearListItemTextAppearance
 * @attr ref android.R.styleable#DatePicker_yearListSelectorColor
 * @attr ref android.R.styleable#DatePicker_calendarTextColor
 * @attr ref android.R.styleable#DatePicker_datePickerMode
 *
 */
open class DatePicker : FrameLayout() {
	
	/**
	 * 解析DatePicker属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"startYear"->{
				}
				"endYear"->{
				}
				"maxDate"->attributes.add("maxDate=${long(value)}\n")
				"minDate"->attributes.add("minDate=${long(value)}\n")
				"spinnersShown"->attributes.add("spinnersShown=${bool(value)}\n")
				"calendarViewShown"->attributes.add("calendarViewShown=${bool(value)}\n")
				"dayOfWeekBackground"->{
					attributes.add("calendarViewShown=${bool(value)}\n")
				}
				"dayOfWeekTextAppearance"->{
				}
				"headerBackground"->{
				}
				"headerMonthTextAppearance"->{
				}
				"headerDayOfMonthTextAppearance"->{
				}
				"headerYearTextAppearance"->{
				}
				"yearListItemTextAppearance"->{
				}
				"yearListSelectorColor"->{
				}
				"calendarTextColor"->{
				}
				"datePickerMode"->{
					attributes.add("//Can't reverse datePickerMode!")
					attributes.add("//datePickerMode=$value")
				}
			}
		}
	}

}
