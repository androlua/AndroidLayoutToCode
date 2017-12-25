package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.bool
import com.cz.layout2anko.inflate.gravity
import com.cz.layout2anko.inflate.item.ImportItem
import com.cz.layout2anko.inflate.item.ViewConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------FrameLayout all attributes---------------
 * @attr ref android.R.styleable#FrameLayout_measureAllChildren
 *
 */
open class FrameLayout : ViewGroup() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="frameLayout"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedFrameLayout"
	/**
	 * 解析FrameLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"measureAllChildren"->attributes.add(ViewConvertItem("measureAllChildren",bool(value)))
			}
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#FrameLayout_Layout_layout_gravity
	 *
	 */
	open class LayoutParams : MarginLayoutParams() {
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				val value=it.value
				when(name){
					"layout_gravity"->{
						importList.add(ImportItem("android.view.Gravity"))
						attributes.add(ViewConvertItem("gravity",gravity(value)))
					}
				}
			}
		}

	}
	
}
