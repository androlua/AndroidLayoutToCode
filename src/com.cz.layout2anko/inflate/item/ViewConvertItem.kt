package com.cz.layout2anko.inflate.item

import com.cz.layout2anko.inflate.VERSIONS

/**
 * Created by cz on 2017/12/21.
 */
class ViewConvertItem(val method:String, val value:String):AttributeConvert{
    //java方法名称
    private var javaMethod:String?=null
    //方法使用sdk
    private var sdk=0
    //是否可以转换,部分属性是无法转换的
    private var canConvert=true
    private var javaConvert:(()->String)?=null
    constructor(method:String,value:String,sdk:Int=0):this(method,value){
        this.sdk=sdk
    }

    constructor(method:String,value:String,convert:Boolean=true):this(method,value){
        this.canConvert=convert
    }

    constructor(method:String,value:String,convert:()->String):this(method,value){
        this.javaConvert=convert
    }

    constructor(method:String,value:String,sdk:Int=0,convert:Boolean=true):this(method,value){
        this.sdk=sdk
        this.canConvert=convert
    }

    constructor(method:String,javaMethod:String,value:String,sdk:Int=0,convert:Boolean=true):this(method,value){
        this.javaMethod=javaMethod
        this.sdk=sdk
        this.canConvert=convert
    }

    override fun toAnkoString():String{
        val out=StringBuilder()
        //属性可以转换
        if(!canConvert){
            out.append("//$method = $value")
        } else {
            if(0==sdk){
                out.append("$method = $value")
            } else {
                out.append("doFromSdk(Build.VERSION_CODES.${VERSIONS[sdk]}){\n")
                out.append("\t$method = $value\n")
                out.append("}")
            }
        }
        return out.toString()
    }
    override fun toJavaString():String{
        val out=StringBuilder()
        val method=getJavaMethodName(method)
        if(!canConvert){
            out.append("//$method($value)")
        } else {
            //转换输入信息
            val methodValue=javaConvert?.invoke()?:"$method($value)"
            if(0==sdk){
                out.append(methodValue)
            } else {
                out.append("if(Build.VERSION.SDK_INIT>=Build.VERSION_CODES.${VERSIONS[sdk]}){\n")
                out.append("\t$methodValue\n")
                out.append("}")
            }
        }
        return out.toString()
    }

    /**
     * 获得 java 方法
     */
    private fun getJavaMethodName(method:String):String{
        return "set"+method[0].toUpperCase()+method.substring(1)
    }
}