package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.int
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.stretchMode

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------GridView all expressions---------------
 * @attr ref android.R.styleable#GridView_horizontalSpacing
 * @attr ref android.R.styleable#GridView_verticalSpacing
 * @attr ref android.R.styleable#GridView_stretchMode
 * @attr ref android.R.styleable#GridView_columnWidth
 * @attr ref android.R.styleable#GridView_numColumns
 * @attr ref android.R.styleable#GridView_gravity
 *
 */
open class GridView : AbsListView() {
	
	init {
		attribute{
			field = "horizontalSpacing"
			attrType = arrayOf(AttrType.DIMENSION)
			property("horizontalSpacing"){ dimen(it) }
		}
		attribute{
			field = "verticalSpacing"
			attrType = arrayOf(AttrType.DIMENSION)
			property("verticalSpacing"){ dimen(it) }
		}
		attribute{
			field = "stretchMode"
			attrType = arrayOf(AttrType.ENUM)
			property("stretchMode"){ stretchMode(it) }
		}
		attribute{
			field = "columnWidth"
			attrType = arrayOf(AttrType.DIMENSION)
			property("columnWidth"){ dimen(it) }
		}
		attribute{
			field = "numColumns"
			attrType = arrayOf(AttrType.FLAG)
			property("numColumns"){ int(it) }
		}
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			property("gravity"){ gravity(it) }
		}
	}

}
