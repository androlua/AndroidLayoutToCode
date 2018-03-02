package com.cz.layout2code.analysis

import com.cz.layout2code.config.DeclareStyleableConfiguration
import com.cz.layout2code.inflate.item.DefineViewNode
import com.intellij.openapi.module.ModuleUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.JarFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.PsiFile
import com.intellij.psi.search.GlobalSearchScope
import org.jdom.input.SAXBuilder
import org.jetbrains.kotlin.idea.configuration.externalProjectPath
import java.io.File

/**
 * Created by cz on 2018/2/28.
 * 分析外部引入的aar文件内style与类信息
 */
class ExplodedAarAnalyzer(val file:PsiFile?):Analyzer<MutableList<DefineViewNode>>{
    //过滤列
    private val FILTER_ITEMS= mutableListOf("com.android.support.test","com.android.support.test.espresso")

    override fun analysis(project: Project):MutableList<DefineViewNode> {
        val defineNodes = mutableListOf<DefineViewNode>()
        val virtualFile = file?.virtualFile
        val st=System.currentTimeMillis()
        if(null!=virtualFile){
            val module = ModuleUtil.findModuleForFile(virtualFile,project)
            val externalProjectPath = module?.externalProjectPath
            if(null!=externalProjectPath){
                ///Users/cz/IntelliJIDEAProjects/MyApplication/app/build/intermediates/exploded-aar
                //获取build后的aar目录
                val explodedAArFile = virtualFile.fileSystem.findFileByPath("$externalProjectPath/build/intermediates/exploded-aar")
                if(null!=explodedAArFile){
                    explodedAArFile.children.forEach { file->
                        //不存在过滤包,开始解析
                        if(FILTER_ITEMS.none { it==file.name }){
                            //添加所有library内的aar文件自定义属性以及类信息
                            defineNodes+=parserExplodedAArPackage(project,file)
                        }
                    }
                }
            }
        }
        println("time:${System.currentTimeMillis()-st}")
        return defineNodes
    }

    /**
     * 解析导入aar包文件
     */
    private fun parserExplodedAArPackage(project: Project,file: VirtualFile):MutableList<DefineViewNode> {
        val nodeItems = mutableListOf<DefineViewNode>()
        if(file.isDirectory){
            //遍历子目录,遍历项目名称
            file.children.forEach { artifactId->
                //项目版本号
                artifactId.children.forEach { version->
                    //当前jar包
                    val classesJar = version.findChild("jars")?.run { findChild("classes.jar") }
                    //清单文件
                    val manifest = version.findChild("AndroidManifest.xml")
                    //检测res目录下配置
                    val resourceFolder = version.findChild("res")?.run { findChild("values") }
                    //检测三个文件不为空
                    if(null!=classesJar&&
                            null!=manifest&&
                            null!=resourceFolder&&
                            0<resourceFolder.children.size){
                        //1:获取manifest内配置package
                        var document= SAXBuilder().build(File(manifest.path))
                        val packageName=document.rootElement.getAttributeValue("package")

                        //2:检测jar包内,所有class文件
                        val classItems=mutableListOf<String>()
                        val rootFile = JarFileSystem.getInstance().getJarRootForLocalFile(classesJar)
                        if(null!=rootFile){
                            collectClassFile(project,classItems,rootFile,rootFile)
                        }
                        //采集属性定义
                        resourceFolder.children.forEach {
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
        }
        return nodeItems
    }

    /**
     * 收集所有的class文件
     */
    private fun collectClassFile(project: Project,classItems: MutableList<String>, root: VirtualFile,file:VirtualFile) {
        if(!file.isDirectory&&file.name.none { it=='$' }){
            val start=if(root.path.endsWith("/")) root.path.length else root.path.length+1
            val filePackage = file.path.substring(start,file.path.lastIndexOf("."))
            val className = filePackage.replace("/", ".")
            classItems.add(className)
        } else if(file.isDirectory){
            //获取包名
            file.children.forEach { collectClassFile(project,classItems,root,it) }
        }
    }


}