package com.cz.layout2code.test

import org.jetbrains.kotlin.idea.intentions.loopToCallChain.match
import org.junit.Test

/**
 * Created by cz on 2018/2/28.
 */
class RegexTest{
    @Test
    fun path(){
        val pattern = "file://(.+/exploded-aar/([\\w_\\.]+)/.+)/res/values/values.xml".toPattern()
        val matcher = pattern.matcher("file:///Users/cz/IntelliJIDEAProjects/MyApplication/app/build/intermediates/exploded-aar/com.android.support/appcompat-v7/26.0.0-alpha1/res/values/values.xml")
        if(matcher.find()){
            println(matcher.group(1))
            println(matcher.group(2))
        }
    }

}