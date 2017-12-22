package com.cz.layout2anko.inflate.item

import com.cz.layout2anko.inflate.VERSIONS

/**
 * Created by cz on 2017/12/21.
 */
class ViewMethodTextConvertItem(val method:String):AttributeConvert{
    //方法使用sdk
    private var sdk=0

    constructor(method:String,sdk:Int=0):this(method){
        this.sdk=sdk
    }

    override fun toAnkoString():String{
        val out=StringBuilder()
        if(0==sdk){
            out.append("$method")
        } else {
            out.append("doFromSdk(Build.VERSION_CODES.${VERSIONS[sdk]}){\n")
            out.append("\t$method")
            out.append("}")
        }
        return out.toString()
    }
    override fun toJavaString():String{
        val out=StringBuilder()
        if(0==sdk){
            out.append("\t$method")
        } else {
            out.append("if(Build.VERSION_SDK_INIT>=Build.VERSION_CODES.${VERSIONS[sdk]}){\n")
            out.append("\t$method")
            out.append("}")
        }
        return out.toString()
    }
}