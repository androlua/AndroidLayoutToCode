package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
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

	init {
		attribute {
			field = "showWeekNumber"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "showWeekNumber = ${bool(it)}" }
			javaMethod { "setShowWeekNumber(${bool(it)});" }
		}
		attribute {
			field = "firstDayOfWeek"
			attrType = arrayOf(AttrType.INTEGER)
			kotlinMethod { "firstDayOfWeek = ${int(it)}" }
			javaMethod { "setFirstDayOfWeek(${int(it)});" }
		}
		attribute {
			//minData配置时为string类型,但方法设置为long型
			field = "minDate"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod {
				"//$it convert to long\n"+
				"minDate = ${long(it)}"
			}
			javaMethod {
				"//$it convert to long\n"+
				"setMinDate(${long(it)});"
			}
		}
		attribute {
			//maxData配置时为string类型,但方法设置为long型
			field = "maxDate"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod {
				"//$it convert to long\n"+
				"maxDate = ${string(it)}"
			}
			javaMethod {
				"//$it convert to long\n"+
				"setMaxDate(${string(it)});"
			}
		}
		attribute {
			field = "shownWeekCount"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=16
			kotlinMethod { "shownWeekCount = ${int(it)}" }
			javaMethod { "setShownWeekCount(${int(it)});" }
		}
		attribute {
			field = "selectedWeekBackgroundColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "selectedWeekBackgroundColor = ${color(it)}" }
			javaMethod { "setSelectedWeekBackgroundColor(${color(it)});" }
		}
		attribute {
			field = "focusedMonthDateColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "focusedMonthDateColor = ${color(it)}" }
			javaMethod { "setfFocusedMonthDateColor(${color(it)});" }
		}
		attribute {
			field = "unfocusedMonthDateColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "unfocusedMonthDateColor = ${color(it)}" }
			javaMethod { "setUnfocusedMonthDateColor(${color(it)});" }
		}
		attribute {
			field = "weekNumberColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "weekNumberColor = ${color(it)}" }
			javaMethod { "setWeekNumberColor(${color(it)});" }
		}
		attribute {
			field = "weekSeparatorLineColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "weekSeparatorLineColor = ${color(it)}" }
			javaMethod { "setWeekSeparatorLineColor(${color(it)});" }
		}
		attribute {
			field = "selectedDateVerticalBar"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			kotlinMethod { "selectedDateVerticalBar = ${resource(it)}" }
			javaMethod { "setSelectedDateVerticalBar(${resource(it)});" }
		}
		attribute {
			field = "weekDayTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "weekDayTextAppearance = ${resourceRef(it)}" }
			javaMethod { "setWeekDayTextAppearance(${resourceRef(it)});" }
		}
		attribute {
			field = "dateTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "dateTextAppearance = ${resourceRef(it)}" }
			javaMethod { "setDateTextAppearance(${resourceRef(it)});" }
		}
	}

	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="calendarView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedCalendarView"

}
