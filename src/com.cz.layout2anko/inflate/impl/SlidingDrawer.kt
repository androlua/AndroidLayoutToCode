package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.item.ViewConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------SlidingDrawer all attributes---------------
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
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="slidingDrawer"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedSlidingDrawer"
	/**
	 * 解析SlidingDrawer属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"content","handle",
				"topOffset","bottomOffset",
				"orientation","allowSingleTap","animateOnClick"->attributes.add(ViewConvertItem(name,value,false))
			}
		}
	}

}
