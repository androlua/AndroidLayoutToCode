package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------CalendarView all attributes---------------
 * @attr ref android.R.styleable#CalendarView_showWeekNumber
 * @attr ref android.R.styleable#CalendarView_firstDayOfWeek
 * @attr ref android.R.styleable#CalendarView_minDate
 * @attr ref android.R.styleable#CalendarView_maxDate
 * @attr ref android.R.styleable#CalendarView_shownWeekCount
 * @attr ref android.R.styleable#CalendarView_selectedWeekBackgroundColor
 * @attr ref android.R.styleable#CalendarView_focusedMonthDateColor
 * @attr ref android.R.styleable#CalendarView_unfocusedMonthDateColor
 * @attr ref android.R.styleable#CalendarView_weekNumberColor
 * @attr ref android.R.styleable#CalendarView_weekSeparatorLineColor
 * @attr ref android.R.styleable#CalendarView_selectedDateVerticalBar
 * @attr ref android.R.styleable#CalendarView_weekDayTextAppearance
 * @attr ref android.R.styleable#CalendarView_dateTextAppearance
 *
 */
open class CalendarView : FrameLayout() {
	
	/**
	 * 解析CalendarView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"showWeekNumber"->attributes.add("showWeekNumber=${bool(value)}")
				"firstDayOfWeek"->attributes.add("showWeekNumber=${int(value)}")
				"minDate"->attributes.add("minDate=${long(value)}")
				"maxDate"->attributes.add("maxDate=${long(value)}")
				"shownWeekCount"->attributes.add("shownWeekCount=${int(value)}")
				"selectedWeekBackgroundColor"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){\n")
					attributes.add("\tselectedWeekBackgroundColor=${color(value)}\n")
					attributes.add("}")
				}
				"focusedMonthDateColor"->attributes.add("focusedMonthDateColor=${color(value)}\n")
				"unfocusedMonthDateColor"->attributes.add("unfocusedMonthDateColor=${color(value)}\n")
				"weekNumberColor"->attributes.add("weekNumberColor=${color(value)}\n")
				"weekSeparatorLineColor"->attributes.add("weekSeparatorLineColor=${color(value)}\n")
				"selectedDateVerticalBar"->attributes.add("selectedDateVerticalBar=${resource(value)}\n")
				"weekDayTextAppearance"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){\n")
					attributes.add("weekDayTextAppearance=${resourceRef(value)}\n")
					attributes.add("}")
				}
				"dateTextAppearance"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){\n")
					attributes.add("\tdateTextAppearance=${resourceRef(value)}\n")
					attributes.add("}")
				}
			}
		}
	}

}
