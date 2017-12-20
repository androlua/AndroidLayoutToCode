package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------ImageView all attributes---------------
 * @attr ref android.R.styleable#ImageView_adjustViewBounds
 * @attr ref android.R.styleable#ImageView_src
 * @attr ref android.R.styleable#ImageView_maxWidth
 * @attr ref android.R.styleable#ImageView_maxHeight
 * @attr ref android.R.styleable#ImageView_tint
 * @attr ref android.R.styleable#ImageView_scaleType
 * @attr ref android.R.styleable#ImageView_cropToPadding
 *
 */
open class ImageView : View() {
	
	/**
	 * 解析ImageView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			when(name){
				"adjustViewBounds"->{
				
				}
				"src"->{
				
				}
				"maxWidth"->{
				
				}
				"maxHeight"->{
				
				}
				"tint"->{
				
				}
				"scaleType"->{
				
				}
				"cropToPadding"->{
				
				}
			}
		}
	}

}
