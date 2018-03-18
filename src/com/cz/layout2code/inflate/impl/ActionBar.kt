package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.gravity
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.tintMode
import org.jdom.Element

/**
 * Created by cz on 2017/12/20.
 */
class ActionBar {
    /**
     * Created by cz on 2017/12/19.
     *
     * ---------------LayoutParams all expressions---------------
     * @attr ref android.R.styleable#Toolbar_LayoutParams_layout_gravity
     *
     */
    open class LayoutParams : ViewGroup.MarginLayoutParams() {
        init {
            attribute {
                field="layout_gravity"
                attrType= arrayOf(AttrType.FLAG)
                allProperty("gravity"){ gravity(it) }
            }
        }
    }
}