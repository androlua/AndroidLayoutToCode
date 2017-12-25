package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ImportItem
import com.cz.layout2anko.inflate.item.ViewConvertItem
import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------LinearLayout all attributes---------------
 * @attr ref android.R.styleable#LinearLayout_baselineAligned
 * @attr ref android.R.styleable#LinearLayout_baselineAlignedChildIndex
 * @attr ref android.R.styleable#LinearLayout_gravity
 * @attr ref android.R.styleable#LinearLayout_measureWithLargestChild
 * @attr ref android.R.styleable#LinearLayout_orientation
 * @attr ref android.R.styleable#LinearLayout_weightSum
 *
 */
open class LinearLayout : ViewGroup() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="linearLayout"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedLinearLayout"

	fun orientation(orientation:String)=when(orientation){
		"horizontal"->"LinearLayout.HORIZONTAL"
		else ->"LinearLayout.VERTICAL"
	}
	/**
	 * 解析LinearLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"baselineAligned"->attributes.add(ViewConvertItem("isBaselineAligned","setBaselineAligned",bool(value)))
				"baselineAlignedChildIndex"->attributes.add(ViewConvertItem("baselineAlignedChildIndex",int(value)))
				"gravity"->{
					importLists.add(ImportItem("android.view.Gravity"))
					attributes.add(ViewConvertItem("gravity",gravity(value)))
				}
				"measureWithLargestChild"->attributes.add(ViewConvertItem("isMeasureWithLargestChildEnabled","setMeasureWithLargestChildEnabled",bool(value)))
				"orientation"->attributes.add(ViewConvertItem("orientation",orientation(value)))
				"weightSum"-> attributes.add(ViewConvertItem("weightSum",float(value)))
			}
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_weight
	 * @attr ref android.R.styleable#LinearLayout_Layout_layout_gravity
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
					"layout_weight"->attributes.add(ViewConvertItem("weight",float(value)))
					"layout_gravity"->{
						importList.add(ImportItem("android.view.Gravity"))
						attributes.add(ViewConvertItem("gravity",gravity(value)))
					}
				}
			}
		}

	}
	
}
