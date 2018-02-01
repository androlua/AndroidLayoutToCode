package com.cz.layout2code.inflate.item

/**
 * Created by cz on 2017/12/21.
 */
interface ElementConvert {
    /**
     * 转换为 anko 形式
     */
    fun toKotlinString():String?

    /**
     * 转换为 java 形式
     */
    fun toJavaString():String?
}