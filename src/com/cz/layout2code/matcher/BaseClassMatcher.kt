package com.cz.layout2code.matcher

import com.cz.layout2code.inflate.item.ClassItem

/**
 * 基础类对接器
 * 如activity/fragment/dialog/view/等不同场下,元素的不同体现
 */
abstract class BaseClassMatcher {
    /**
     * 获得类引用信息
     */
    abstract fun getClassItem(key:String): ClassItem

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