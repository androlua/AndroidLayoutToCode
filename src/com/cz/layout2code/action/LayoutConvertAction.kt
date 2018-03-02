package com.cz.layout2code.action

import com.cz.layout2code.analysis.ExplodedAarAnalyzer
import com.cz.layout2code.analysis.ModuleAnalyzer
import com.cz.layout2code.config.DeclareStyleableConfiguration
import com.cz.layout2code.config.WidgetConfiguration
import com.cz.layout2code.delegate.MessageDelegate
import com.cz.layout2code.form.UnknownWidgetForm
import com.cz.layout2code.inflate.AndroidLayoutInflater
import com.cz.layout2code.inflate.item.AttributeNode
import com.cz.layout2code.inflate.item.DefineViewNode
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.util.TextCalculation
import com.cz.layout2code.util.Utils
import com.intellij.codeInsight.CodeInsightActionHandler
import com.intellij.codeInsight.generation.actions.BaseGenerateAction
import com.intellij.compiler.ant.taskdefs.Jar
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiUtilBase
import org.jdom.Element
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.psi.KtFile
import java.io.File
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.vfs.JarFileSystem
import com.intellij.psi.*
import com.intellij.psi.impl.compiled.ClsClassImpl
import org.jetbrains.kotlin.idea.caches.JarUserDataManager
import org.jetbrains.kotlin.idea.caches.resolve.getNullableModuleInfo
import org.jetbrains.kotlin.idea.caches.resolve.lightClasses.KtLightClassForDecompiledDeclaration
import org.jetbrains.kotlin.idea.configuration.externalProjectPath
import org.jetbrains.kotlin.idea.util.projectStructure.allModules
import org.jetbrains.kotlin.idea.util.projectStructure.getModuleDir
import org.jetbrains.kotlin.psi.moduleInfo


/**
 * Created by cz on 2017/12/14.
 * 布局转换代码的事件执行体
 */
class LayoutConvertAction : BaseGenerateAction {
    val defineWidgetAttrs= mutableListOf<DefineViewNode>()
    constructor() : super(null)

    constructor(handler: CodeInsightActionHandler) : super(handler)

    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
        return super.isValidForFile(project, editor, file) &&null!=Utils.getLayoutFileFromCaret(editor, file)
    }


    override fun actionPerformed(event: AnActionEvent) {
        val dataContext = event.dataContext
        val project = DataKeys.PROJECT.getData(dataContext)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor){
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            val layout = Utils.getLayoutFileFromCaret(editor, file)
            //采集所有第三方库,与当前项目内的控件属性声明
            val defineWidgetAttrs=defineWidgetAttrs
            if (defineWidgetAttrs.isEmpty()) {
                //引用库
                defineWidgetAttrs += ExplodedAarAnalyzer(file).analysis(project)
                //当前项目内控件
                defineWidgetAttrs += ModuleAnalyzer(file).analysis(project)
            }
            //不做文件字节码判断,因为判断比直接检索更耗时,己测试,性能相差近30倍,直接操作500毫秒,判断操作time:14842
            if(file !is PsiJavaFile && file !is KtFile){
                //不是kt/java源码文件,转换无意义
                MessageDelegate.showMessage("Not a Kotlin or Java source file!")
            } else {
                if (layout == null) {
                    MessageDelegate.showMessage("No layout found")
                } else {
                    MessageDelegate.logEventMessage("Layout found:${file.name}")
                    val virtualFile = layout.virtualFile
                    if(null!=virtualFile){
                        AndroidLayoutInflater.inflater(project,virtualFile,defineWidgetAttrs,file is PsiJavaFile)
                    }
                }
            }

        }
    }

}
