package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/1/29.
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
	init {
		attribute{
			field = "firstDayOfWeek"
			attrType = arrayOf(AttrType.INTEGER)
			sdk=21
			kotlinMethod { "firstDayOfWeek = ${int(it)}" }
			javaMethod{ "setFirstDayOfWeek(${int(it)})" }
		}
		attribute{
			field = "minDate"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod {
				"$it convert to long"+
				"minDate = ${long(it)}"
			}
			javaMethod{
				"$it convert to long"+
				"setMinDate(${long(it)})"
			}
		}
		attribute{
			field = "maxDate"
			attrType = arrayOf(AttrType.STRING)
			kotlinMethod {
				"$it convert to long"+
				"maxDate = ${string(it)}"
			}
			javaMethod{
				"$it convert to long"+
				"setMaxDate(${string(it)})"
			}
		}
		attribute{
			field = "spinnersShown"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "spinnersShown = ${bool(it)}" }
			javaMethod{ "setSpinnersShown(${bool(it)})" }
		}
		attribute{
			field = "calendarViewShown"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "calendarViewShown = ${bool(it)}" }
			javaMethod{ "setCalendarViewShown(${bool(it)})" }
		}
		uselessAttribute("internalLayout")
		uselessAttribute("legacyLayout")
		uselessAttribute("headerTextColor")
		uselessAttribute("headerBackground")
		uselessAttribute("yearListItemTextAppearance")
		uselessAttribute("yearListItemActivatedTextAppearance")
		uselessAttribute("calendarTextColor")
		uselessAttribute("datePickerMode")
		uselessAttribute("startYear")
		uselessAttribute("endYear")
		uselessAttribute("headerMonthTextAppearance")
		uselessAttribute("headerDayOfMonthTextAppearance")
		uselessAttribute("headerYearTextAppearance")
		uselessAttribute("dayOfWeekBackground")
		uselessAttribute("dayOfWeekTextAppearance")
		uselessAttribute("yearListSelectorColor")
		uselessAttribute("dialogMode")
	}

	override fun getViewName()="datePicker"

	override fun getThemeViewName()="themedDatePicker"

	/**
	 * 解析LayoutParams属性集
	 */
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}
}
