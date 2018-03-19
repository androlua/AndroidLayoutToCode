package com.cz.layout2code.inflate.impl

import sun.tools.jconsole.LabeledComponent.layout



/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------AbsSpinner all expressions---------------
 * @attr ref android.R.styleable#AbsSpinner_entries
 *
 */
open class AbsSpinner : AdapterView() {
	init {
//		语句太多,暂不兼容,但机制允许
//		val entries = a.getTextArray(R.styleable.AbsSpinner_entries)
//		if (entries != null) {
//			val adapter = ArrayAdapter<CharSequence>(
//					context, R.layout.simple_spinner_item, entries)
//			adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//			setAdapter(adapter)
//		}
	    uselessAttribute("entries")
	}
}
