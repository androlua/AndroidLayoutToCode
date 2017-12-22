package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ViewConvertItem
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
	 * 获得控件映射名称
	 */
	override fun getViewName()="calendarView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedCalendarView"
	/**
	 * 解析CalendarView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"showWeekNumber"->attributes.add(ViewConvertItem("showWeekNumber",bool(value)))
				"firstDayOfWeek"->attributes.add(ViewConvertItem("firstDayOfWeek",int(value)))
				"minDate"->attributes.add(ViewConvertItem("minDate",long(value)))
				"maxDate"->attributes.add(ViewConvertItem("maxDate",long(value)))
				"shownWeekCount"->attributes.add(ViewConvertItem("shownWeekCount",int(value)))
				"selectedWeekBackgroundColor"-> attributes.add(ViewConvertItem("selectedWeekBackgroundColor",color(value),16))
				"focusedMonthDateColor"->attributes.add(ViewConvertItem("focusedMonthDateColor",color(value)))
				"unfocusedMonthDateColor"->attributes.add(ViewConvertItem("unfocusedMonthDateColor",color(value)))
				"weekNumberColor"->attributes.add(ViewConvertItem("weekNumberColor",color(value)))
				"weekSeparatorLineColor"->attributes.add(ViewConvertItem("weekSeparatorLineColor",color(value)))
				"selectedDateVerticalBar"->attributes.add(ViewConvertItem("selectedDateVerticalBar",resource(value)))
				"weekDayTextAppearance"->attributes.add(ViewConvertItem("weekDayTextAppearance",resourceRef(value),16))
				"dateTextAppearance"->attributes.add(ViewConvertItem("dateTextAppearance",resourceRef(value),16))
			}
		}
	}

}
