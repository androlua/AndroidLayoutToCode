package com.cz.convert

import com.cz.layout2code.util.getKtClassForElement
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiDocumentManager
import org.jetbrains.kotlin.idea.internal.Location
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile

/**
 * Created by cz on 2017/6/16.
 */
val ACTIVITY="android.app.Activity"
val DIALOG="android.app.Dialog"
val VIEW="android.view.View"
val VIEW_HOLDER="android.support.v7.widget.RecyclerView.ViewHolder"
val FRAGMENT_ITEMS= arrayOf("android.app.Fragment","android.support.v4.app.Fragment")
fun PsiClass.isActivity():Boolean=condition(this){it==ACTIVITY}

fun PsiClass.isFragment():Boolean= condition(this){ qualifiedName->FRAGMENT_ITEMS.any { it==qualifiedName }}

fun PsiClass.isDialog():Boolean=condition(this){it==DIALOG}

fun PsiClass.isView():Boolean=condition(this){it==VIEW}

fun condition(clazz:PsiClass,closure:(String?)->Boolean):Boolean{
    var result=false
    var clazz: PsiClass?=clazz
    while(null!=clazz){
        if(closure.invoke(clazz.qualifiedName)){
            result=true
            break
        }
        clazz=clazz?.superClass
    }
    return result
}
