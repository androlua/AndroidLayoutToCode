package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.item.ViewNode
import org.jdom.Element

/**
 * Created by cz on 2018/3/1.
 * 控件转换器
 */
interface IView {
    /**
     * 获得控件名称
     */
    fun getClassName():String

    /**
     * 转换文本
     */
    fun convert(viewNode: ViewNode,toJava:Boolean=true):String

    /**
     * 解析属性
     */
    open fun inflateAttributes(element: ViewNode)
}