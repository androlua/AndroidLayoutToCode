package com.cz.layout2code.analysis

import com.cz.layout2code.config.DeclareStyleableConfiguration
import com.cz.layout2code.inflate.item.DefineViewNode
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.JarFileSystem
import com.intellij.psi.PsiFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import org.jdom.Document
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.idea.configuration.externalProjectPath
import java.io.File

/**
 * Created by cz on 2018/2/28.
 * 分析外部引入的aar文件内style与类信息
 */
class ModuleAnalyzer(val file:PsiFile):Analyzer<MutableList<DefineViewNode>>{

    override fun analysis(project: Project):MutableList<DefineViewNode> {
        val pattern = "(.+/exploded-aar/([\\w_\\.]+)/.+)/res/values/values.xml".toPattern()
        val virtualFile = file?.virtualFile
        if(null!=virtualFile){
            val module = ModuleUtil.findModuleForFile(virtualFile,project)
            val externalProjectPath = module?.externalProjectPath
            if(null!=externalProjectPath){
                ///Users/cz/IntelliJIDEAProjects/MyApplication/app/src/main/res/values
                val valueFile = virtualFile.fileSystem.findFileByPath("$externalProjectPath/src/main/res/values")
                if(null!=valueFile){
                    valueFile.children.forEach {
                    }
                }
            }
        }
        val valueFiles = FilenameIndex.getVirtualFilesByName(project, "values.xml", GlobalSearchScope.everythingScope(project))
        valueFiles.forEach {
            //获取包名 file:///Users/cz/IntelliJIDEAProjects/MyApplication/app/build/intermediates/exploded-aar/com.android.support/appcompat-v7/26.0.0-alpha1/res/values/values.xml
            var aarPath:String?=null
            var aarPackageName:String?=null
            val matcher = pattern.matcher(it.path)
            if (matcher.find()) {
                //记录前路径下的
                aarPath = matcher.group(1)
                aarPackageName=matcher.group(2)
            }
            DeclareStyleableConfiguration(aarPackageName, File(it.path)).parse()
            val classFile = it.fileSystem.findFileByPath("$aarPath/jars/classes.jar")
            if(null!=classFile){
                val rootLocalFile = JarFileSystem.getInstance().getJarRootForLocalFile(classFile)
                //遍历jar中所有文件

            }
        }
        return null!!
    }


}