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
	 * ImageView.ScaleType.CENTER
	 * ImageView.ScaleType.CENTER_CROP
	 * ImageView.ScaleType.CENTER_INSIDE
	 * ImageView.ScaleType.FIT_CENTER
	 * ImageView.ScaleType.FIT_END
	 * ImageView.ScaleType.FIT_START
	 * ImageView.ScaleType.FIT_XY
	 * ImageView.ScaleType.MATRIX
	 */
	fun scaleType(type:String)=when(type){
		"center"->"ImageView.ScaleType.CENTER"
		"centerCrop"->"ImageView.ScaleType.CENTER_CROP"
		"centerInside"->"ImageView.ScaleType.CENTER_INSIDE"
		"fitCenter"->"ImageView.ScaleType.FIT_CENTER"
		"fitEnd"->"ImageView.ScaleType.FIT_END"
		"fitStart"->"ImageView.ScaleType.FIT_START"
		"fitXY"->"ImageView.ScaleType.FIT_XY"
		else ->"ImageView.ScaleType.MATRIX"
	}
	/**
	 * 解析ImageView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
		element.attributes.forEach {
			val name=it.name
			val value=it.value
			when(name){
				"adjustViewBounds"->attributes.add("adjustViewBounds=${bool(value)}")
				"src"->attributes.add("setImageResource(=${resourceRef(value)})")
				"maxWidth"->{
					attributes.add("setImageDrawable(=${resource(value)})")
				}
				"maxHeight"->attributes.add("maxWidth(=${dimen(value)})")
				"tint"->attributes.add("imageTintList(=${colorStateList(value)})")
				"scaleType"->attributes.add("imageTintList(=${scaleType(value)})")
				"cropToPadding"->{
					attributes.add("doFromSdk(Build.VERSION_CODES.JELLY_BEAN){\n")
					attributes.add("\tcropToPadding=${bool(value)}\n")
					attributes.add("}\n")
				}
			}
		}
	}

}
