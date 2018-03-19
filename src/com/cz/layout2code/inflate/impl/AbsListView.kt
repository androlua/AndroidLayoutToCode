package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.*
import com.cz.layout2code.inflate.prefs.AttrType

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------AbsListView all expressions---------------
 * @attr ref android.R.styleable#AbsListView_listSelector
 * @attr ref android.R.styleable#AbsListView_drawSelectorOnTop
 * @attr ref android.R.styleable#AbsListView_stackFromBottom
 * @attr ref android.R.styleable#AbsListView_scrollingCache
 * @attr ref android.R.styleable#AbsListView_textFilterEnabled
 * @attr ref android.R.styleable#AbsListView_transcriptMode
 * @attr ref android.R.styleable#AbsListView_cacheColorHint
 * @attr ref android.R.styleable#AbsListView_fastScrollEnabled
 * @attr ref android.R.styleable#AbsListView_smoothScrollbar
 * @attr ref android.R.styleable#AbsListView_choiceMode
 *
 */
open class AbsListView : AdapterView() {
	init {
		attribute{
			field = "listSelector"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setSelector"){ resourceRef(it) }
		}
		attribute{
			field = "drawSelectorOnTop"
			attrType = arrayOf(AttrType.BOOLEAN)
			method("setDrawSelectorOnTop"){ bool(it) }
		}
		attribute{
			field = "stackFromBottom"
			attrType = arrayOf(AttrType.BOOLEAN)
			property("stackFromBottom"){ bool(it) }
		}
		attribute{
			field = "scrollingCache"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setScrollingCacheEnabled"
				kotlin="isScrollingCacheEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "textFilterEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setTextFilterEnabled"
				kotlin="isTextFilterEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "transcriptMode"
			attrType = arrayOf(AttrType.ENUM)
			property("transcriptMode") { transcriptMode(it) }
		}
		attribute{
			field = "cacheColorHint"
			attrType = arrayOf(AttrType.COLOR)
			property("cacheColorHint"){ color(it) }
		}

		attribute{
			field = "fastScrollEnabled"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setFastScrollEnabled"
				kotlin="isFastScrollEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "smoothScrollbar"
			attrType = arrayOf(AttrType.BOOLEAN)
			property {
				java="setSmoothScrollbarEnabled"
				kotlin="isSmoothScrollbarEnabled"
				value { bool(it) }
			}
		}
		attribute{
			field = "choiceMode"
			attrType = arrayOf(AttrType.ENUM)
			property("choiceMode") { listChoiceMode(it) }
		}

	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all expressions---------------
	 *
	 */
	open class LayoutParams : ViewGroup.MarginLayoutParams()
}
