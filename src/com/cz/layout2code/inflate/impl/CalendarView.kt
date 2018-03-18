package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------CalendarView all expressions---------------
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
			property("showWeekNumber"){ bool(it) }
		}
		attribute {
			field = "firstDayOfWeek"
			attrType = arrayOf(AttrType.INTEGER)
			property("firstDayOfWeek"){ int(it) }
		}
		attribute {
			//minData配置时为string类型,但方法设置为long型
			field = "minDate"
			attrType = arrayOf(AttrType.STRING)
			comment { "//$it convert to long" }
			property("minDate"){ long(it) }
		}
		attribute {
			//maxData配置时为string类型,但方法设置为long型
			field = "maxDate"
			attrType = arrayOf(AttrType.STRING)
			comment { "//$it convert to long" }
			property("maxDate"){ long(it) }
		}
		attribute {
			field = "shownWeekCount"
			attrType = arrayOf(AttrType.BOOLEAN)
			sdk=16
			property("shownWeekCount"){ int(it) }
		}
		attribute {
			field = "selectedWeekBackgroundColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			property("selectedWeekBackgroundColor"){ color(it) }
		}
		attribute {
			field = "focusedMonthDateColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			property("focusedMonthDateColor"){ color(it) }
		}
		attribute {
			field = "unfocusedMonthDateColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			property("unfocusedMonthDateColor"){ color(it) }
		}
		attribute {
			field = "weekNumberColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			property("weekNumberColor"){ color(it) }
		}
		attribute {
			field = "weekSeparatorLineColor"
			attrType = arrayOf(AttrType.COLOR,AttrType.REFERENCE)
			sdk=16
			property("weekSeparatorLineColor"){ color(it) }
		}
		attribute {
			field = "selectedDateVerticalBar"
			attrType = arrayOf(AttrType.REFERENCE)
			sdk=16
			property("selectedDateVerticalBar"){ resource(it) }
		}
		attribute {
			field = "weekDayTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			property("weekDayTextAppearance"){ resourceRef(it) }
		}
		attribute {
			field = "dateTextAppearance"
			attrType = arrayOf(AttrType.REFERENCE)
			property("dateTextAppearance"){ resourceRef(it) }
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
