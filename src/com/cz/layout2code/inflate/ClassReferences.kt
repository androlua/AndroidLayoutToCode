package com.cz.layout2code.inflate

import com.cz.layout2code.inflate.item.ClassItem

/**
 * 常用类引用
 */
object ClassReferences {
    private val references= mutableMapOf<String, ClassItem>()
    init {
        item{ "android.animation.LayoutTransition"}
        item{ "android.graphics.Typeface" }
        item{ "android.graphics.PorterDuff" }
        item{ "android.view.View" }
        item{ "android.view.ViewGroup" }
        item{ "android.view.Gravity" }
        item{ "android.view.PointerIcon"}
        item{ "android.text.InputFilter"}
        item{ "android.view.inputmethod.EditorInfo"}
        item{ "android.widget.GridLayout"}
        item{ "android.widget.LinearLayout"}
        item{ "android.widget.ImageView" }
        item{ "android.widget.RelativeLayout"}
        item{ "android.widget.AbsListView"}
        item{ "android.widget.GridView"}
        item{ "android.text.TextUtils"}
        item{ "android.text.InputType"}
        item{ "android.text.util.Linkify"}
        item{ "android.text.Layout"}
        item{ "android.content.res.Resources" }
    }

    private inline fun item(action:()->String){
        val referenceName=action()
        addClassItem(referenceName)
    }

    fun getClassItem(className:String)=references[className]

    fun addClassItem(referenceName:String){
        if(null!=referenceName){
            val classItem= ClassItem(referenceName)
            references[classItem.className] = classItem
        }
    }

}