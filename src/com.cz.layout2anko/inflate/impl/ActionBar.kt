package com.cz.layout2anko.inflate.impl

import com.cz.layout2anko.inflate.gravity
import com.cz.layout2anko.inflate.item.ImportItem
import com.cz.layout2anko.inflate.item.ViewConvertItem
import com.cz.layout2anko.inflate.prefs.AttrType
import com.cz.layout2anko.inflate.prefs.ViewConfiguration
import com.cz.layout2anko.inflate.prefs.ViewConfiguration.Companion.declareStyleable
import com.cz.layout2anko.inflate.prefs.ViewStyle
import org.jdom.Element

/**
 * Created by cz on 2017/12/20.
 */
class ActionBar {
    /**
     * Created by cz on 2017/12/19.
     *
     * ---------------LayoutParams all attributes---------------
     * @attr ref android.R.styleable#Toolbar_LayoutParams_layout_gravity
     *
     */
    open class LayoutParams : ViewGroup.MarginLayoutParams() {

        /**
         * 解析LayoutParams属性集,并返回解析后的anko代码
         */
        override fun inflateAttributes(element: Element){
            super.inflateAttributes(element)
            element.attributes.forEach {
                val name=it.name
                val value=it.value
                when(name){
                    "layout_gravity"->{
                        importList.add(ImportItem("android.view.Gravity"))
                        attributes.add(ViewConvertItem("gravity",gravity(value)))
                    }
                }
            }
        }
        companion object{
            val viewStyleItems= mutableListOf<ViewStyle>()
            init {
                declareStyleable(viewStyleItems){
                    item {
                        field="layout_gravity"
                        importList= arrayOf("android.view.Gravity")
                        attrType= arrayOf(AttrType.FLAG)
                        kotlinMethod{ "gravity = ${gravity(it)}" }
                        javaMethod{ "setGravity(${gravity(it)});" }
                    }
                }
            }
        }

    }
}