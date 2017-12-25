package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.*
import com.cz.layout2anko.inflate.item.ImportItem
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.item.ViewMethodConvertItem
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
	 * 获得控件映射名称
	 */
	override fun getViewName()="imageView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedImageView"

	/**
	 * 解析ImageView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"adjustViewBounds"->attributes.add(ViewConvertItem("adjustViewBounds",bool(value)))
				"src"->attributes.add(ViewMethodConvertItem("setImageResource(${resourceRef(value)})"))
				"maxWidth"->attributes.add(ViewConvertItem("maxWidth",dimen(value)))
				"maxHeight"->attributes.add(ViewConvertItem("maxWidth",dimen(value)))
				"tint"->attributes.add(ViewConvertItem("imageTintList",colorStateList(value)))
				"scaleType"->{
					importLists.add(ImportItem("android.widget.ImageView"))
					attributes.add(ViewConvertItem("scaleType",scaleType(value)))
				}
				"cropToPadding"->attributes.add(ViewConvertItem("cropToPadding",bool(value),16))
			}
		}
	}

}
