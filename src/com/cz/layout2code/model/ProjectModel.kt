package com.cz.layout2code.delegate

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiDirectory

/**
 * Created by cz on 2017/12/13.
 */
class ProjectModel {
    var directory: PsiDirectory? = null
        private set
    var packageName: String? = null
        private set
    var virtualFolder: VirtualFile? = null
        private set
    var project: Project? = null
        private set

    class Builder {
        private val instance: ProjectModel = ProjectModel()

        fun setDirectory(directory: PsiDirectory): Builder {
            instance.directory = directory
            return this
        }

        fun setPackageName(packageName: String): Builder {
            instance.packageName = packageName
            return this
        }

        fun setVirtualFolder(virtualFolder: VirtualFile): Builder {
            instance.virtualFolder = virtualFolder
            return this
        }

        fun setProject(project: Project): Builder {
            instance.project = project
            return this
        }

        fun build(): ProjectModel {
            return instance
        }
    }
}
