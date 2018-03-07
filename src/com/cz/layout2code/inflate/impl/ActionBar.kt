package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.prefs.AttrType
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
        init {
            attribute {
                field="layout_gravity"
                importList= arrayOf("android.view.Gravity")
                attrType= arrayOf(AttrType.FLAG)
                kotlinMethod{ "gravity = ${gravity(it)}" }
                javaMethod{ "setGravity(${gravity(it)});" }
            }
        }
    }
}