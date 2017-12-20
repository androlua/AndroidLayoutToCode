package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------TabWidget all attributes---------------
 * @attr ref android.R.styleable#TabWidget_divider
 * @attr ref android.R.styleable#TabWidget_tabStripEnabled
 * @attr ref android.R.styleable#TabWidget_tabStripLeft
 * @attr ref android.R.styleable#TabWidget_tabStripRight
 *
 */
open class TabWidget : LinearLayout() {
	
	/**
	 * 解析TabWidget属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"divider"->{
				
				}
				"tabStripEnabled"->{
				
				}
				"tabStripLeft"->{
				
				}
				"tabStripRight"->{
				
				}
			}
		}
	}

}
