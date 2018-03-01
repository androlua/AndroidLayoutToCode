package com.cz.layout2code.analysis

import com.cz.layout2code.config.DeclareStyleableConfiguration
import com.cz.layout2code.inflate.item.DefineViewNode
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.JarFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiFile
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import org.jdom.Document
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.idea.caches.resolve.productionSourceInfo
import org.jetbrains.kotlin.idea.configuration.externalProjectPath
import java.io.File

/**
 * Created by cz on 2018/2/28.
 * 分析内部声明的attrs与自定义控件信息
 */
class ModuleAnalyzer(val file:PsiFile?):Analyzer<MutableList<DefineViewNode>>{

    override fun analysis(project: Project):MutableList<DefineViewNode> {
        val nodeItems = mutableListOf<DefineViewNode>()
        val virtualFile = file?.virtualFile
        if(null!=virtualFile){
            val module = ModuleUtil.findModuleForFile(virtualFile,project)
            val externalProjectPath = module?.externalProjectPath
            if(null!=externalProjectPath){
                ///Users/cz/IntelliJIDEAProjects/MyApplication/app/src/main/res/values
                virtualFile.fileSystem.findFileByPath("$externalProjectPath/src/main/res/values")
                //values目录
                val valueFile = virtualFile.fileSystem.findFileByPath("$externalProjectPath/src/main/res/values")
                //源码目录
                val srcFile = virtualFile.fileSystem.findFileByPath("$externalProjectPath/src/main/java")
                //清单文件
                val manifest = virtualFile.fileSystem.findFileByPath("$externalProjectPath/src/main/AndroidManifest.xml")
                if(null!=srcFile&&null!=valueFile&&null!=manifest){
                    var document= SAXBuilder().build(File(manifest.path))
                    val packageName=document.rootElement.getAttributeValue("package")
                    //采集所有源码类信息
                    val classItems = mutableListOf<String>()
                    collectClassFile(project,classItems,srcFile,srcFile)
                    //采集所有的values目录内的属性声明
                    valueFile.children.forEach {
                        //检测xml内配置
                        val configuration = DeclareStyleableConfiguration(packageName, File(it.path))
                        nodeItems+=configuration.parse()
                    }
                    //让style-attr与class建立关联关系
                    nodeItems.forEach { item->
                        val qualifiedName = classItems.find { it.endsWith(item.name) }
                        if(!qualifiedName.isNullOrEmpty()){
                            //记录控件全称
                            item.qualifiedName=qualifiedName
                            //记录控件包名
                            item.packageName=qualifiedName?.substring(0,qualifiedName.lastIndexOf("."))
                        }
                    }
                }
            }
        }
        return nodeItems
    }

    /**
     * 收集所有的class文件
     */
    private fun collectClassFile(project: Project, classItems: MutableList<String>, root: VirtualFile, file: VirtualFile) {
        if(!file.isDirectory&&file.name.none { it=='$' }){
            val start=if(root.path.endsWith("/")) root.path.length else root.path.length+1
            val filePackage = file.path.substring(start,file.path.lastIndexOf("."))
            classItems.add(filePackage.replace("/","."))
        } else if(file.isDirectory){
            //获取包名
            file.children.forEach { collectClassFile(project,classItems,root,it) }
        }
    }


}