package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resource
import sun.tools.jconsole.LabeledComponent.layout



/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------ListView all expressions---------------
 * @attr ref android.R.styleable#ListView_entries
 * @attr ref android.R.styleable#ListView_divider
 * @attr ref android.R.styleable#ListView_dividerHeight
 * @attr ref android.R.styleable#ListView_headerDividersEnabled
 * @attr ref android.R.styleable#ListView_footerDividersEnabled
 *
 */
open class ListView : AbsListView() {
	
	init {
//		val entries = a.getTextArray(R.styleable.ListView_entries)
//		if (entries != null) {
//			setAdapter(ArrayAdapter(context, R.layout.simple_list_item_1, entries))
//		}
		uselessAttribute("entries")
		attribute{
			field = "divider"
			attrType = arrayOf(AttrType.REFERENCE)
			property("divider"){ resource(it) }
		}
		attribute{
			field = "dividerHeight"
			attrType = arrayOf(AttrType.DIMENSION)
			property("dividerHeight"){ dimen(it) }
		}
		attribute{
			field = "headerDividersEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("setHeaderDividersEnabled"){ bool(it) }
		}
		attribute{
			field = "footerDividersEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("setFooterDividersEnabled"){ bool(it) }
		}
	}

}
