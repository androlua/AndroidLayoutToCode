package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.bool
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resourceRef
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
	init {
		attribute{
			field = "inAnimation"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setInAnimation(context,${resourceRef(it)}" }
			javaMethod{ "setInAnimation(context,${resourceRef(it)}" }
		}
		attribute{
			field = "outAnimation"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setOutAnimation(${resourceRef(it)}" }
			javaMethod{ "setOutAnimation(${resourceRef(it)}" }
		}
		attribute{
			field = "animateFirstView"
			attrType = arrayOf(AttrType.BOOLEAN)
			kotlinMethod { "animateFirstView = ${bool(it)}" }
			javaMethod{ "setAnimateFirstView(${bool(it)})" }
		}
	}
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
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
	}

}
