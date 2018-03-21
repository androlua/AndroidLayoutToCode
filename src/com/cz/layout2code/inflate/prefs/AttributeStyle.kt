package com.cz.layout2code.inflate.prefs

import com.cz.layout2code.inflate.expression.*
import com.cz.layout2code.inflate.expression.value.ElementExpression

/**
 * Created by cz on 2017/12/25.
 */
class AttributeStyle {
    //属性字段名称
    lateinit var field:String
    //属性分类
    var attrType=arrayOf(AttrType.REFERENCE)
    //最低版本
    var sdk=0
    //是否要以被转换,有些属性是完全内部封装,无法转换
    var convert=true
    //注释信息
    private var commentCallback:((String)->String)?=null

    lateinit var expression:AttributeExpression

    /**
     * 属性注释信息
     */
    fun comment(commentCallback:(String)->String){
        this.commentCallback=commentCallback
    }

    fun allProperty(property:String,value:((String)->ElementExpression)){
        expression =AttributeAllPropertyExpression(property,value)
    }

    fun property(action:PropertyItem.()->Unit){
        //记录自定义表达式
        val propertyItem = PropertyItem().apply(action)
        expression =AttributePropertyExpression(propertyItem.java,propertyItem.kotlin,propertyItem.value)
    }

    fun property(property:String,value:((String)->ElementExpression)){
        //记录自定义表达式
        val propertyItem = PropertyItem()
        propertyItem.property=property
        propertyItem.value=value
        expression =AttributePropertyExpression(propertyItem.property,propertyItem.value)
    }

    fun method(action:MethodItem.()->Unit){
        val methodItem = MethodItem().apply(action)
        expression =AttributeMethodExpression(methodItem.method,methodItem.value)
    }

    fun method(method:String,value:((String)->ElementExpression)){
        expression =AttributeMethodExpression(method,value)
    }

    fun methods(method:String,value:((String)->MutableList<ElementExpression>)){
        expression =AttributeMethodMultiParamsExpression(method,value)
    }

    fun expression(item:AttributeMethodBlockExpression.()->Unit){
        expression=AttributeMethodBlockExpression().apply(item)
    }

    inline fun callback(value:String):ElementExpression{
        val attributeExpression = expression.callback(value)
        attributeExpression.sdk=sdk
        return attributeExpression
    }

    class PropertyItem {
        internal lateinit var property:String
        internal lateinit var java:String
        internal lateinit var kotlin:String
        internal lateinit var value:((String)->ElementExpression)

        fun value(callback:(String)->ElementExpression){
            this.value=callback
        }
    }

    class MethodItem {
        internal lateinit var method:String
        internal lateinit var value:((String)->ElementExpression)

        fun value(callback:(String)->ElementExpression){
            this.value=callback
        }
    }
}