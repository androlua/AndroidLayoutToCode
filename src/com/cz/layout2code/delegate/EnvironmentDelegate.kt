package com.cz.layout2code.delegate

import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiDirectory

/**
 * Created by cz on 2017/12/13.
 */
class EnvironmentDelegate {

    @Throws(KotlinPluginException::class)
    fun obtainProjectModel(event: AnActionEvent): ProjectModel {
        val directory = checkPath(event)
        val project = event.project
        val virtualFolder = event.getData(LangDataKeys.VIRTUAL_FILE)
        val packageName = ProjectRootManager.getInstance(project!!).fileIndex.getPackageNameByDirectory(virtualFolder!!)
        return ProjectModel.Builder()
                .setDirectory(directory)
                .setPackageName(packageName!!)
                .setProject(project)
                .setVirtualFolder(virtualFolder)
                .build()
    }

    fun refreshProject(projectModel: ProjectModel) {
        ProjectView.getInstance(projectModel.project).refresh()
        projectModel.virtualFolder?.refresh(false, true)
    }

    @Throws(KotlinPluginException::class)
    private fun checkPath(event: AnActionEvent): PsiDirectory {
        val pathItem = event.getData<Navigatable>(CommonDataKeys.NAVIGATABLE)
        if (pathItem != null) {
            if (pathItem is PsiDirectory) {
                return pathItem
            }
        }
        throw PathException()
    }
}
