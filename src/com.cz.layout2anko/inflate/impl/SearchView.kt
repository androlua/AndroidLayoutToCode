package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------SearchView all attributes---------------
 * @attr ref android.R.styleable#SearchView_iconifiedByDefault
 * @attr ref android.R.styleable#SearchView_imeOptions
 * @attr ref android.R.styleable#SearchView_inputType
 * @attr ref android.R.styleable#SearchView_maxWidth
 * @attr ref android.R.styleable#SearchView_queryHint
 *
 */
open class SearchView : LinearLayout() {
	
	/**
	 * 解析SearchView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"iconifiedByDefault"->{
				
				}
				"imeOptions"->{
				
				}
				"inputType"->{
				
				}
				"maxWidth"->{
				
				}
				"queryHint"->{
				
				}
			}
		}
	}

}
