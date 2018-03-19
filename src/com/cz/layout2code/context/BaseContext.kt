package com.cz.layout2code.context

import com.cz.layout2code.inflate.item.ClassItem

/**
 * 基础类上下文
 * 如activity/fragment/dialog/view/等不同场下,元素的不同体现
 */
abstract class BaseContext {
    /**
     * 获得常规类成员信息
     */
    abstract fun getClassField(key:String): ClassItem
    /**
     * 获得java映射字段
     */
    abstract fun getJavaField(key:String):String

    /**
     * 获得kotlin的映射字段
     */
    abstract fun getKotlinField(key:String):String
}