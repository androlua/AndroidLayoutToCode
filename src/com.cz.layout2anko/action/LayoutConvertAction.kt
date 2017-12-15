package com.cz.layout2anko.action

import com.cz.layout2anko.delegate.MessageDelegate
import com.cz.layout2anko.util.Utils
import com.cz.layout2anko.util.getPsiClassFromEvent
import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.sun.javafx.scene.CameraHelper.project
import java.util.ArrayList
import com.intellij.psi.util.PsiUtilBase
import org.jetbrains.kotlin.psi.KtFile


/**
 * Created by cz on 2017/12/14.
 * 布局转换anko 的dsl的事件执行体
 */
class LayoutConvertAction : BaseGenerateAction {
    constructor() : super(null)

    constructor(handler: CodeInsightActionHandler) : super(handler)

    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
        return super.isValidForFile(project, editor, file) &&null!=Utils.getLayoutFileFromCaret(editor, file)
    }


    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor){
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            if(file !is KtFile){
                //不是kt源码文件,转换无意义
                MessageDelegate.showMessage("Not a Kotlin class file!")
            } else {
                val layout = Utils.getLayoutFileFromCaret(editor, file)
                if (layout == null) {
                    MessageDelegate.showMessage("No layout found")
                } else {
                    MessageDelegate.logEventMessage("Layout found:${file.packageFqName}")
                }
            }
        }
    }

}
