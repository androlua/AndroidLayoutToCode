package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.long
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
	 * 获得控件映射名称
	 */
	override fun getViewName()="datePicker"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedDatePicker"
	/**
	 * 解析DatePicker属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"startYear"->attributes.add(ViewConvertItem(name,value,0,false))
				"endYear"->attributes.add(ViewConvertItem(name,value,0,false))
				"maxDate"->attributes.add(ViewConvertItem("maxDate",long(value)))
				"minDate"->attributes.add(ViewConvertItem("minDate",long(value)))
				"spinnersShown"->attributes.add(ViewConvertItem("spinnersShown",bool(value)))
				"calendarViewShown"->attributes.add(ViewConvertItem("calendarViewShown",bool(value)))
				"dayOfWeekBackground"->attributes.add(ViewConvertItem("calendarViewShown",bool(value)))
				"dayOfWeekTextAppearance",
				"headerBackground",
				"headerMonthTextAppearance",
				"headerDayOfMonthTextAppearance",
				"headerYearTextAppearance",
				"yearListItemTextAppearance",
				"yearListSelectorColor",
				"calendarTextColor",
				"datePickerMode"->attributes.add(ViewConvertItem(name,value,0,false))
			}
		}
	}

}
