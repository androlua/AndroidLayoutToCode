package com.cz.layout2code.inflate.element

import com.cz.layout2code.inflate.impl.View

/**
 * Created by cz on 2017/12/21.
 */
class ViewDefineConvertItem(val view: View,val name:String?=null): ElementConvert {

    override fun toKotlinString():String="${view.getViewName()}"

    override fun toJavaString():String{
        val classPath = view.getClassPath()
        val simpleName= classPath.substringAfterLast(".")
        return "$simpleName $name = new $simpleName(context);"
    }
}