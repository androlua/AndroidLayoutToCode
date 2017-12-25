package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.prefs.AttrType
import com.cz.layout2anko.inflate.prefs.ViewConfiguration
import com.cz.layout2anko.inflate.prefs.ViewStyle
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

	companion object{
		val viewStyleItems= mutableListOf<ViewStyle>()
		init {
			ViewConfiguration.declareStyleable(viewStyleItems) {
				item {
					field = "showWeekNumber"
					attrType = arrayOf(AttrType.BOOLEAN)
					kotlinMethod { "showWeekNumber = ${bool(it)}" }
					javaMethod { "setShowWeekNumber(${bool(it)});" }
				}
				item {
					field = "firstDayOfWeek"
					attrType = arrayOf(AttrType.INTEGER)
					kotlinMethod { "firstDayOfWeek = ${int(it)}" }
					javaMethod { "setFirstDayOfWeek(${int(it)});" }
				}
				item {
					//minData配置时为string类型,但方法设置为long型
					field = "minDate"
					attrType = arrayOf(AttrType.STRING)
					kotlinMethod { "minDate = ${string(it)}" }
					javaMethod { "setMinDate(${string(it)});" }
				}
				item {
					//maxData配置时为string类型,但方法设置为long型
					field = "maxDate"
					attrType = arrayOf(AttrType.STRING)
					kotlinMethod { "maxDate = ${string(it)}" }
					javaMethod { "setMaxDate(${string(it)});" }
				}
				item {
					field = "shownWeekCount"
					attrType = arrayOf(AttrType.BOOLEAN)
					kotlinMethod { "shownWeekCount = ${int(it)}" }
					javaMethod { "setShownWeekCount(${int(it)});" }
				}
				item {
					field = "selectedWeekBackgroundColor"
					attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
					kotlinMethod { "selectedWeekBackgroundColor = ${color(it)}" }
					javaMethod { "setSelectedWeekBackgroundColor(${color(it)});" }
				}
				item {
					field = "focusedMonthDateColor"
					attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
					kotlinMethod { "focusedMonthDateColor = ${color(it)}" }
					javaMethod { "setfFocusedMonthDateColor(${color(it)});" }
				}
				item {
					field = "unfocusedMonthDateColor"
					attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
					kotlinMethod { "unfocusedMonthDateColor = ${color(it)}" }
					javaMethod { "setUnfocusedMonthDateColor(${color(it)});" }
				}
				item {
					field = "weekNumberColor"
					attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
					kotlinMethod { "weekNumberColor = ${color(it)}" }
					javaMethod { "setWeekNumberColor(${color(it)});" }
				}
				item {
					field = "weekSeparatorLineColor"
					attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
					kotlinMethod { "weekSeparatorLineColor = ${color(it)}" }
					javaMethod { "setWeekSeparatorLineColor(${color(it)});" }
				}
				item {
					field = "selectedDateVerticalBar"
					attrType = arrayOf(AttrType.REFERENCE)
					kotlinMethod { "selectedDateVerticalBar = ${resourceRef(it)}" }
					javaMethod { "setWeekDayTextAppearance(${resourceRef(it)});" }
				}
				item {
					field = "weekDayTextAppearance"
					attrType = arrayOf(AttrType.REFERENCE)
					kotlinMethod { "weekDayTextAppearance = ${resourceRef(it)}" }
					javaMethod { "setWeekSeparatorLineColor(${resourceRef(it)});" }
				}
				item {
					field = "dateTextAppearance"
					attrType = arrayOf(AttrType.REFERENCE)
					kotlinMethod { "dateTextAppearance = ${resourceRef(it)}" }
					javaMethod { "setDateTextAppearance(${resourceRef(it)});" }
				}
			}
		}
	}

}
