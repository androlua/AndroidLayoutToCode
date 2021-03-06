package com.cz.layout2code.inflate.impl

import org.jdom.Element
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.resource
/**
 * Created by cz on 2018/1/29.
 * 
 * ---------------SlidingDrawer all expressions---------------
 * @attr ref android.R.styleable#SlidingDrawer_content
 * @attr ref android.R.styleable#SlidingDrawer_handle
 * @attr ref android.R.styleable#SlidingDrawer_topOffset
 * @attr ref android.R.styleable#SlidingDrawer_bottomOffset
 * @attr ref android.R.styleable#SlidingDrawer_orientation
 * @attr ref android.R.styleable#SlidingDrawer_allowSingleTap
 * @attr ref android.R.styleable#SlidingDrawer_animateOnClick
 *
 */
open class SlidingDrawer : ViewGroup() {
	init {
		uselessAttribute("handle")
		uselessAttribute("expression")
		uselessAttribute("orientation")
		uselessAttribute("bottomOffset")
		uselessAttribute("topOffset")
		uselessAttribute("allowSingleTap")
		uselessAttribute("animateOnClick")
	}

	override fun getViewName()="slidingDrawer"

	override fun getThemeViewName()="themedSlidingDrawer"
}
