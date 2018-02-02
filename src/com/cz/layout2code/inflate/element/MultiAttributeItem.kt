package com.cz.layout2code.inflate.element

/**
 * Created by cz on 2017/12/21.
 */
class MultiAttributeItem(val method: String,vararg value: String): ElementConvert {
    val valueItems= mutableListOf<String>()
    init {
        valueItems+=value.toList()
    }
    override fun toKotlinString()="$method(${valueItems.joinToString(", ")})"
    override fun toJavaString()="$method(${valueItems.joinToString(", ")})"

}