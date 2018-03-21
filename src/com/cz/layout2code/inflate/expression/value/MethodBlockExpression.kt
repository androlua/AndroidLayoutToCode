package com.cz.layout2code.inflate.expression.value

import com.cz.layout2code.context.BaseContext
import com.cz.layout2code.inflate.item.ImportItem

class MethodBlockExpression(val methodName:String): ElementExpression() {
    private var importCallback:(()->MutableList<ImportItem>)?=null
    private lateinit var javaCallback:(BaseContext)->String
    private lateinit var kotlinCallback:(BaseContext)->String

    fun import(callback:()->MutableList<ImportItem>){
        this.importCallback=callback
    }

    fun javaExpression(callback:(BaseContext)->String){
        this.javaCallback=callback
    }

    fun kotlinExpression(callback:(BaseContext)->String){
        this.javaCallback=callback
    }

    override fun getImportList(): MutableList<ImportItem> {
        return importCallback?.invoke()?: mutableListOf()
    }

    override fun getJavaExpression(context: BaseContext): String {
        return javaCallback(context)
    }

    override fun getKotlinExpression(context: BaseContext): String {
        return kotlinCallback(context)
    }

}