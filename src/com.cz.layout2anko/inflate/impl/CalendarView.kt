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
			when(name){
				"showWeekNumber"->{
				
				}
				"firstDayOfWeek"->{
				
				}
				"minDate"->{
				
				}
				"maxDate"->{
				
				}
				"shownWeekCount"->{
				
				}
				"selectedWeekBackgroundColor"->{
				
				}
				"focusedMonthDateColor"->{
				
				}
				"unfocusedMonthDateColor"->{
				
				}
				"weekNumberColor"->{
				
				}
				"weekSeparatorLineColor"->{
				
				}
				"selectedDateVerticalBar"->{
				
				}
				"weekDayTextAppearance"->{
				
				}
				"dateTextAppearance"->{
				
				}
			}
		}
	}

}
