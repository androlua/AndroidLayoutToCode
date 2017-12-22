package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.gravity
import com.cz.layout2anko.inflate.id
import com.cz.layout2anko.inflate.int
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
import com.cz.layout2anko.inflate.relativeRule
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------RelativeLayout all attributes---------------
 * @attr ref android.R.styleable#RelativeLayout_gravity
 * @attr ref android.R.styleable#RelativeLayout_ignoreGravity
 *
 */
open class RelativeLayout : ViewGroup() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="relativeLayout"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedRelativeLayout"
	/**
	 * 解析RelativeLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"gravity"->attributes.add(ViewConvertItem("gravity",gravity(value)))
				"ignoreGravity"->attributes.add(ViewMethodConvertItem("setIgnoreGravity(${id(value)})"))
			}
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignWithParentIfMissing
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_toLeftOf
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_toRightOf
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_above
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_below
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignBaseline
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignLeft
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignTop
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignRight
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignBottom
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignParentLeft
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignParentTop
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignParentRight
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignParentBottom
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_centerInParent
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_centerHorizontal
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_centerVertical
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_toStartOf
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_toEndOf
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignStart
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignEnd
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignParentStart
	 * @attr ref android.R.styleable#RelativeLayout_Layout_layout_alignParentEnd
	 *
	 */
	open class LayoutParams : ViewGroup.MarginLayoutParams() {
		
		/**
		 * 解析LayoutParams属性集,并返回解析后的anko代码
		 */
		override fun inflateAttributes(element:Element){
			super.inflateAttributes(element)
			element.attributes.forEach {
				val name=it.name
				val value=it.value
				when(name){
					"layout_alignWithParentIfMissing"->attributes.add(ViewConvertItem(name,value,false))
					"layout_toLeftOf", "layout_toRightOf",
					"layout_above", "layout_below", "layout_alignBaseline",
					"layout_alignLeft", "layout_alignTop",
					"layout_alignRight", "layout_alignBottom", "layout_toStartOf",
					"layout_toEndOf", "layout_alignStart",
					"layout_alignEnd"->attributes.add(ViewMethodConvertItem("addRule(${relativeRule(name)},${id(value)})"))
					"layout_alignParentLeft", "layout_alignParentTop", "layout_alignParentRight",
					"layout_alignParentBottom", "layout_centerHorizontal", "layout_centerVertical",
					"layout_alignParentStart", "layout_alignParentEnd", "layout_centerInParent"->{
						attributes.add(ViewMethodConvertItem("addRule(${relativeRule(name)}})"))

					}
				}
			}
		}

	}
	
}
