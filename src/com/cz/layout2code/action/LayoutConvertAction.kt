package com.cz.layout2code.action

import com.cz.convert.isActivity
import com.cz.convert.isDialog
import com.cz.convert.isFragment
import com.cz.convert.isView
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
import com.intellij.openapi.actionSystem.AnAction
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
import org.jetbrains.kotlin.idea.refactoring.getLineNumber
import org.jetbrains.kotlin.idea.util.projectStructure.allModules
import org.jetbrains.kotlin.idea.util.projectStructure.getModuleDir
import org.jetbrains.kotlin.psi.moduleInfo
import sun.tools.jconsole.LabeledComponent.layout
import com.cz.layout2code.inflate.impl.FrameLayout
import com.cz.layout2code.inflate.impl.RelativeLayout
import com.intellij.psi.impl.PsiClassImplUtil
import com.intellij.psi.impl.source.tree.java.PsiReferenceExpressionImpl
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.refactoring.RefactoringFactory
import com.intellij.util.Query
import org.jetbrains.kotlin.j2k.getContainingClass
import java.util.regex.Pattern


/**
 * Created by cz on 2017/12/14.
 * 布局转换代码的事件执行体
 */
class LayoutConvertAction(handler: CodeInsightActionHandler?) : BaseGenerateAction(handler) {
    val defineWidgetAttrs= mutableListOf<DefineViewNode>()

//    override fun isValidForFile(project: Project, editor: Editor, file: PsiFile): Boolean {
//        return super.isValidForFile(project, editor, file) &&null!=Utils.getLayoutFileFromCaret(editor, file)
//    }

    override fun actionPerformed(event: AnActionEvent) {
        val dataContext = event.dataContext
        val project = DataKeys.PROJECT.getData(dataContext)
        val editor = event.getData(PlatformDataKeys.EDITOR)
        if(null!=project&&null!=editor){
            val file = PsiUtilBase.getPsiFileInEditor(editor, project)
            val layout = Utils.getLayoutFileFromCaret(editor, file)

            //采集所有第三方库,与当前项目内的控件属性声明
//            val defineWidgetAttrs=defineWidgetAttrs
//            if (defineWidgetAttrs.isEmpty()) {
//                //引用库
//                defineWidgetAttrs += ExplodedAarAnalyzer(file).analysis(project)
//                //当前项目内控件
//                defineWidgetAttrs += ModuleAnalyzer(file).analysis(project)
//            }
            //不做文件字节码判断,因为判断比直接检索更耗时,己测试,性能相差近30倍,直接操作500毫秒,判断操作time:14842
//            if(file !is PsiJavaFile && file !is KtFile){
//                //不是kt/java源码文件,转换无意义
//                MessageDelegate.showMessage("Not a Kotlin or Java source file!")
//            } else {
                if (layout == null) {
                    MessageDelegate.showMessage("No layout found")
                } else {
                    if(null!=file){
                        val candidate = file.findElementAt(editor.caretModel.offset - 1)
                        var parent=candidate?.parent
                        //当当前指针表达式,为一个调用方法时,中断
                        while(null!=parent&&parent !is PsiMethodCallExpression){
                            parent=parent?.parent
                        }
                        val text=parent?.text
                        val clazz = getTargetClass(editor, file)
                        if(null!=text&&null!=clazz){
                            if(clazz.isActivity()){

                            } else if(clazz.isFragment()){

                            } else if(clazz.isDialog()){

                            } else if(clazz.isView()){
//                                inflate(context,R.layout.activity_main,this)
                            } else {
                                //其他
                            }
//                            setContentView(R.layout.activity_main)
//                            val parentLayout = RelativeLayout(this)
//                            val inflate1 = View.inflate(this, R.layout.activity_main, parentLayout)
//                            val parentLayout1 = FrameLayout(this)
//                            val inflate = LayoutInflater.from(this).inflate(R.layout.activity_main, parentLayout1, false)
                            val matcher = ("(?<contentLayout>setContentView.+)|" +
                                    "(inflate\\(\\w+,\\s*[\\w\\.]+,\\s*(?<viewInflate>\\w+)\\))|" +
                                    "(inflate\\([\\w\\.]+,\\s*(?<inflate>\\w+),\\s*\\w+\\))").toPattern().matcher(text)
                            var parentLayout:String?=null
                            if(matcher.find()){
                                parentLayout=matcher.group("contentLayout")?: matcher.group("viewInflate")?:matcher.group("inflate")
                            }
                            val findElement=parent?.children?.flatMap { it.children.toList() }?.find { parentLayout==it.text }
                            if(null!=findElement){
                                val reference = findElement.reference
                                val search: Query<PsiReference> = ReferencesSearch.search(findElement)
                                if(reference is PsiReferenceExpressionImpl){
                                    println(reference?.qualifiedName)
                                }
                                if(findElement is PsiReferenceExpressionImpl){
                                    println(findElement)
                                }
                            }
                        }
                    }
                    MessageDelegate.logEventMessage("Layout found:${file?.name}")
                    val virtualFile = layout.virtualFile
                    if(null!=virtualFile&&null!=file){
//                        AndroidLayoutInflater.inflater(project,file,virtualFile,defineWidgetAttrs,file is PsiJavaFile)
                    }
                }
//            }

        }
    }

}
