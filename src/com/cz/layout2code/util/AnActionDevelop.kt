package com.cz.layout2code.util

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.PsiDocumentManager
import org.jetbrains.kotlin.idea.internal.Location
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFile

/**
 * Created by Administrator on 2017/5/25.
 */
fun AnActionEvent.getPsiClassFromEvent(): KtClass? {
    val editor = getData(PlatformDataKeys.EDITOR)!!
    val project = editor.project ?: return null
    val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.document)
    var ktClass: KtClass?=null
    if (null!=psiFile&& psiFile is KtFile){
        val location = Location.fromEditor(editor, project)
        val psiElement = psiFile.findElementAt(location.startOffset) ?: return null
        ktClass = psiElement.getKtClassForElement()
    }
    return ktClass
}