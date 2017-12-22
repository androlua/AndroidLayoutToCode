package com.cz.layout2anko.inflate.item

/**
 * Created by cz on 2017/12/21.
 */
interface AttributeConvert {
    /**
     * 转换为 anko 形式
     */
    fun toAnkoString():String

    /**
     * 转换为 java 形式
     */
    fun toJavaString():String
}