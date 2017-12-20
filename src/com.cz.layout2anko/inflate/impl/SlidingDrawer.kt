package com.cz.layout2anko.inflate.impl

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
	 * 解析SlidingDrawer属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"content"->{
				
				}
				"handle"->{
				
				}
				"topOffset"->{
				
				}
				"bottomOffset"->{
				
				}
				"orientation"->{
				
				}
				"allowSingleTap"->{
				
				}
				"animateOnClick"->{
				
				}
			}
		}
	}

}
