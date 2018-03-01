package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.id
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.relativeRule
import com.cz.layout2code.inflate.resource
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
	init {
		attribute{
			field = "gravity"
			attrType = arrayOf(AttrType.FLAG)
			kotlinMethod { "gravity = ${gravity(it)}" }
			javaMethod{ "setGravity(${gravity(it)})" }
		}
		attribute{
			field = "ignoreGravity"
			attrType = arrayOf(AttrType.REFERENCE)
			kotlinMethod { "setIgnoreGravity(${id(it)})" }
			javaMethod{ "setIgnoreGravity(${id(it)})" }
		}
	}

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
	override fun inflateAttributes(element: ViewNode){
		super.inflateAttributes(element)
		element.attributes.forEach { addAttributeItems(it.name,it.value) }
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
		init {
			uselessAttribute("layout_alignWithParentIfMissing")
			attribute{
				field = "layout_toLeftOf"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_toRightOf"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_above"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_below"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignBaseline"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignLeft"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignTop"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignRight"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignBottom"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_toStartOf"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_toEndOf"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignStart"
				attrType = arrayOf(AttrType.REFERENCE)
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}
			attribute{
				field = "layout_alignEnd"
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)}" }
				kotlinMethod { "addRule(${relativeRule(field)},${id(it)});" }
			}

			attribute{
				field = "layout_alignParentLeft"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_alignParentTop"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_alignParentRight"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod { "layout_alignParentRight = ${resource(it)}" }
				javaMethod{ "setLAYOUT_ALIGNPARENTRIGHTayout_alignParentRight(${resource(it)})" }
			}
			attribute{
				field = "layout_alignParentBottom"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_centerInParent"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_centerHorizontal"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_centerVertical"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_alignParentStart"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
			attribute{
				field = "layout_alignParentEnd"
				attrType = arrayOf(AttrType.BOOLEAN)
				kotlinMethod {
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
				javaMethod{
					if(it!="true") "" else "addRule(${relativeRule(field)}})"
				}
			}
		}

		/**
		 * 解析LayoutParams属性集
		 */
		override fun inflateAttributes(element: Element){
			super.inflateAttributes(element)
			element.attributes.forEach { addAttributeItems(it.name,it.value) }
		}
	}
	
}
