package com.cz.layout2anko.inflate.impl

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
				"baselineAligned"->attributes.add("isBaselineAligned = ${bool(value)})")
				"baselineAlignedChildIndex"->attributes.add("baselineAlignedChildIndex = ${int(value)})")
				"gravity"->attributes.add("gravity = ${gravity(value)})")
				"measureWithLargestChild"->attributes.add("isMeasureWithLargestChildEnabled = ${bool(value)})")
				"orientation"->attributes.add("orientation = ${orientation(value)})")
				"weightSum"-> attributes.add("weightSum = ${float(value)})")
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
				when(name){
					"layout_weight"->{
//						attributes.add("weight = ${float(value)})")
//						attributes.add("maxWidth(=${dimen(value)})")
					}
					"layout_gravity"->{
					
					}
				}
			}
		}

	}
	
}
