package com.cz.layout2code.inflate.expression

import com.cz.layout2code.inflate.expression.value.ElementExpression

abstract class AttributeExpression: ElementExpression(){
    /**
     * 设置属性表达式调用等级
     */
    var sdk=0
    /**
     * 回调表达式取值
     */
    abstract fun callback(value:String):AttributeExpression
}