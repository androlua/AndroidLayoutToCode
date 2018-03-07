package com.cz.layout2code.inflate.element

import com.cz.layout2code.inflate.impl.View

/**
 * Created by cz on 2017/12/21.
 */
class ViewDefineConvertItem(val view: View): ElementConvert {

    override fun toKotlinString():String="${view.getViewName()}"

    override fun toJavaString():String{
        val classPath = view.getClassPath()
        val simpleName= classPath.substringAfterLast(".")
        val fieldName= simpleName[0].toLowerCase()+ simpleName.substring(1)
        return "$simpleName $fieldName = new $simpleName(context);"
    }
}