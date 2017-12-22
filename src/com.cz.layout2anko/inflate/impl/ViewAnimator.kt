package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.resourceRef
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ViewAnimator all attributes---------------
 * @attr ref android.R.styleable#ViewAnimator_inAnimation
 * @attr ref android.R.styleable#ViewAnimator_outAnimation
 * @attr ref android.R.styleable#ViewAnimator_animateFirstView
 *
 */
open class ViewAnimator : FrameLayout() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="viewAnimator"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedViewAnimator"
	/**
	 * 解析ViewAnimator属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"inAnimation"->attributes.add(ViewMethodConvertItem("setInAnimation(context,${resourceRef(value)}"))
				"outAnimation"->attributes.add(ViewMethodConvertItem("setOutAnimation(context,${resourceRef(value)}"))
				"animateFirstView"->attributes.add(ViewConvertItem("animateFirstView",bool(value)))
			}
		}
	}

}
