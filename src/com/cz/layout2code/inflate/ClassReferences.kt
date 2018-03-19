package com.cz.layout2code.inflate

import com.cz.layout2code.inflate.item.ClassItem

/**
 * 常用类引用
 */
object ClassReferences {
    val references= mutableMapOf<String, ClassItem>()
    init {
        item{ classPath="android.animation.LayoutTransition"}
        item{ classPath="android.graphics.Typeface" }
        item{ classPath="android.graphics.PorterDuff" }
        item{ classPath="android.view.View" }
        item{ classPath="android.view.ViewGroup" }
        item{ classPath="android.view.Gravity" }
        item{ classPath="android.view.PointerIcon"}
        item{ classPath="android.text.InputFilter"}
        item{ classPath="android.view.inputmethod.EditorInfo"}
        item{ classPath="android.widget.GridLayout"}
        item{ classPath="android.widget.LinearLayout"}
        item{ classPath="android.widget.ImageView" }
        item{ classPath="android.widget.RelativeLayout"}
        item{ classPath="android.widget.AbsListView"}
        item{ classPath="android.widget.GridView"}
        item{ classPath="android.text.TextUtils"}
        item{ classPath="android.text.InputType"}
        item{ classPath="android.text.util.Linkify"}
        item{ classPath="android.text.Layout"}
    }

    inline fun item(action:Item.()->Unit){
        val item=Item().apply(action)
        val classPath=item.classPath
        if(null!=classPath){
            val classItem= ClassItem(classPath)
            references.put(classItem.className,classItem)
        }
    }

    fun getClassItem(className:String)=references[className]

    class Item{
        var classPath:String?=null
    }
}