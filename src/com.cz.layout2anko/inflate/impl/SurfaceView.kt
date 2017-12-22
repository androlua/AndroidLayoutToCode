package com.cz.layout2anko.inflate.impl

import org.jdom.Element
/**
 * Created by cz on 2017/12/19.
 * 
 * ---------------SurfaceView all attributes---------------
 *
 */
open class SurfaceView : View() {
	/**
	 * 获得控件映射名称
	 */
	override fun getViewName()="surfaceView"

	/**
	 * 获得控件带样式映射名称
	 */
	override fun getThemeViewName()="themedSurfaceView"
	/**
	 * 解析SurfaceView属性集,并返回解析后的anko代码
	 */
	override fun inflateAttributes(element:Element){
		super.inflateAttributes(element)
	}

}
