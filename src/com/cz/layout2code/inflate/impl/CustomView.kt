package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.search.GlobalSearchScope
import java.util.*

/**
 * Created by cz on 2018/2/27.
 * 标记自定义控件的抽象生成体
 */
class CustomView{

    companion object{
        //检测控件包名
        val PACKAGE_NAME="com.cz.layout2code.inflate.impl"
        val ANDROID_PACKAGE="android.widget"
        //当前控件树根节点
        var root=ClassNode("View")
        //当前缓存所有节点
        val viewNodes = mutableListOf(root)
        init {
            //通过views.properties构建系统控件的继承树关系,这一步非常关键
            val classLoader = CustomView::class.java.classLoader
            val resourceAsStream = classLoader.getResourceAsStream("views.properties")
            val properties= Properties()
            properties.load(resourceAsStream)
            for (className in properties.stringPropertyNames()) {
                val superClass = properties[className].toString()
                //当前节点
                var node = getClassNode(className)
                if(superClass.isNotBlank()){
                    //获得父节点
                    var parentNode = getClassNode(superClass)
                    //记录父节点
                    node.parent=parentNode
                    //父节点记录子节点
                    parentNode.children.add(node)
                }
            }
        }

        /**
         * 获取一个class节点.
         */
        private fun getClassNode(superClass: String): ClassNode {
            var parentNode = viewNodes.find { it.className == superClass }
            if (null == parentNode) {
                parentNode = ClassNode(superClass)
                viewNodes.add(parentNode)
            }
            return parentNode
        }
        /**
         * 创建一个自定义引用
         */
        @Throws
        fun create(project: Project, node: ViewNode):CustomView?{
            //查找此类是否存在,并一直追查到类的继承到父级
            var result:CustomView?=null
            var findClass = JavaPsiFacade.getInstance(project).findClass(node.name, GlobalSearchScope.everythingScope(project))
            while(null!=findClass){
                val qualifiedName = findClass.qualifiedName
                if(null!=qualifiedName){
                    val index = qualifiedName.lastIndexOf(".")
                    val packageName=qualifiedName.substring(0,index)
                    val className=qualifiedName.substring(index+1)
                    //代表为系统控件
                    if(ANDROID_PACKAGE==packageName){
                        val classNode = viewNodes.find { className == it.className }
                        if(null==classNode){
                            throw IllegalArgumentException("$className can't find!")
                        } else {
                            result=CustomView()
                        }
                        break
                    }
                }
                findClass=findClass.superClass
            }
            return null
        }

        /**
         * 控件的继承层级节点
         */
        class ClassNode(val className:String){
            //父级节点
            var parent:ClassNode?=null
            //子节点
            val children= mutableListOf<ClassNode>()

            override fun equals(other: Any?): Boolean {
                return className==(other as? ClassNode)?.className
            }
            override fun toString()=className
        }
    }
}