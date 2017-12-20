package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------GridLayout all attributes---------------
 * @attr ref android.R.styleable#GridLayout_orientation
 * @attr ref android.R.styleable#GridLayout_rowCount
 * @attr ref android.R.styleable#GridLayout_columnCount
 * @attr ref android.R.styleable#GridLayout_useDefaultMargins
 * @attr ref android.R.styleable#GridLayout_rowOrderPreserved
 * @attr ref android.R.styleable#GridLayout_columnOrderPreserved
 *
 */
open class GridLayout : ViewGroup() {
	
	/**
	 * 解析GridLayout属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"orientation"->{
				
				}
				"rowCount"->{
				
				}
				"columnCount"->{
				
				}
				"useDefaultMargins"->{
				
				}
				"rowOrderPreserved"->{
				
				}
				"columnOrderPreserved"->{
				
				}
			}
		}
	}

	override fun getLayoutParams()=LayoutParams()

	/**
	 * Created by cz on 2017/12/19.
	 * 
	 * ---------------LayoutParams all attributes---------------
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_row
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_rowSpan
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_rowWeight
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_column
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_columnSpan
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_columnWeight
	 * @attr ref android.R.styleable#GridLayout_Layout_layout_gravity
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
				when(name){
					"layout_row"->{
					
					}
					"layout_rowSpan"->{
					
					}
					"layout_rowWeight"->{
					
					}
					"layout_column"->{
					
					}
					"layout_columnSpan"->{
					
					}
					"layout_columnWeight"->{
					
					}
					"layout_gravity"->{
					
					}
				}
			}
		}

	}
	
}
