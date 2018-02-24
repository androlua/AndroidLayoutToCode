package com.cz.layout2code.util

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.projectRoots.ProjectJdkTable
import com.intellij.openapi.projectRoots.Sdk
import com.intellij.psi.*
import com.intellij.psi.search.EverythingGlobalScope
import com.intellij.psi.search.FilenameIndex
import org.jdom.Namespace
import org.jetbrains.kotlin.idea.refactoring.fqName.getKotlinFqName

object Utils {
    private val NS_ANDROID = Namespace.getNamespace("android", "http://schemas.android.com/apk/res/android")

    /**
     * Is using Android SDK?
     */
    fun findAndroidSDK(): Sdk? {
        val allJDKs = ProjectJdkTable.getInstance().allJdks
        for (sdk in allJDKs) {
            if (sdk.sdkType.name.toLowerCase().contains("android")) {
                return sdk
            }
        }
        return null // no Android SDK found
    }

    /**
     * Try to find layout XML file in current source on cursor's position

     * @param editor
     * *
     * @param file
     * *
     * @return
     */
    fun getLayoutFileFromCaret(editor: Editor, file: PsiFile?): PsiFile? {
        var result:PsiFile?=null
        if(null!=editor&&null!=file){
            val offset = editor.caretModel.offset
            val candidateA = file.findElementAt(offset)
            val candidateB = file.findElementAt(offset - 1)
            val layout = findLayoutResource(candidateA)
            if (layout != null) {
                return layout
            }
            result= findLayoutResource(candidateB)
        }
        return result
    }

    /**
     * Try to find layout XML file in selected element

     * @param element
     * *
     * @return
     */
    fun findLayoutResource(element: PsiElement?): PsiFile? {
        if (element == null) {
            return null // nothing to be used
        }
        if (element !is PsiIdentifier) {
            return null // nothing to be used
        }

        val layout = element.parent.firstChild ?: return null // no file to process
        if ("R.layout" != layout.text) {
            return null // not layout file
        }

        val project = element.project
        val name = String.format("%s.xml", element.text)
        return resolveLayoutResourceFile(element, project, name)


    }

    /**
     * 获得资源包名
     */
    fun getResourcesPackage(editor: Editor, file: PsiFile?): String? {
        var packageName:String?=null
         if(null!=editor&&null!=file){
             val offset = editor.caretModel.offset
             val candidate = file.findElementAt(offset - 1)
             //parent 获得R.layout.xxx firstChild获得 R.layout 再一次firstChild获得R对象
             val r = candidate?.parent?.firstChild?.firstChild
             if(null!=r && r is PsiReferenceExpression){
                 val qualifiedName=r.qualifiedName
                 packageName=qualifiedName.substring(0,qualifiedName.lastIndexOf("."))
             }
        }
        return packageName
    }

    private fun resolveLayoutResourceFile(element: PsiElement, project: Project, name: String): PsiFile? {
        // restricting the search to the current module - searching the whole project could return wrong layouts
        val module = ModuleUtil.findModuleForPsiElement(element)
        var files: Array<PsiFile>? = null
        if (module != null) {
            val moduleScope = module.getModuleWithDependenciesAndLibrariesScope(false)
            files = FilenameIndex.getFilesByName(project, name, moduleScope)
        }
        if (files == null || files.size <= 0) {
            // fallback to search through the whole project
            // useful when the project is not properly configured - when the resource directory is not configured
            files = FilenameIndex.getFilesByName(project, name, EverythingGlobalScope(project))
            if (files!!.size <= 0) {
                return null //no matching files
            }
        }

        // TODO - we have a problem here - we still can have multiple layouts (some coming from a dependency)
        // we need to resolve R class properly and find the proper layout for the R class
        return files[0]
    }

    /**
     * Try to find layout XML file by name

     * @param file
     * *
     * @param project
     * *
     * @param fileName
     * *
     * @return
     */
    fun findLayoutResource(file: PsiFile, project: Project, fileName: String): PsiFile? {
        val name = String.format("%s.xml", fileName)
        // restricting the search to the module of layout that includes the layout we are seaching for
        return resolveLayoutResourceFile(file, project, name)
    }


    /**
     * Easier way to check if string is empty
     * @param text
     * *
     * @return
     */
    fun isEmpty(text: String?): Boolean {
        return text == null || text.trim { it <= ' ' }.length == 0
    }

    /**
     * Check whether classpath of a module that corresponds to a [PsiElement] contains given class.

     * @param project    Project
     * *
     * @param psiElement Element for which we check the class
     * *
     * @param className  Class name of the searched class
     * *
     * @return True if the class is present on the classpath
     * *
     * @since 1.3
     */
    fun isClassAvailableForPsiFile(project: Project, psiElement: PsiElement, className: String): Boolean {
        val module = ModuleUtil.findModuleForPsiElement(psiElement) ?: return false
        val moduleScope = module.getModuleWithDependenciesAndLibrariesScope(false)
        val classInModule = JavaPsiFacade.getInstance(project).findClass(className, moduleScope)
        return classInModule != null
    }

    /**
     * Check whether classpath of a the whole project contains given class.
     * This is only fallback for wrongly setup projects.

     * @param project   Project
     * *
     * @param className Class name of the searched class
     * *
     * @return True if the class is present on the classpath
     * *
     * @since 1.3.1
     */
    fun isClassAvailableForProject(project: Project, className: String): Boolean {
        val classInModule = JavaPsiFacade.getInstance(project).findClass(className,
                EverythingGlobalScope(project))
        return classInModule != null
    }

}
