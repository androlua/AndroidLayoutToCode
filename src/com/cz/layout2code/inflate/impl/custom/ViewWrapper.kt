package com.cz.layout2code.inflate.impl.custom

import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.impl.WIDGET_PACKAGE
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.search.GlobalSearchScope
import java.util.*

abstract class ViewWrapper: View() {
    companion object{
        //检测控件包名
        private val androidPackages= mutableListOf("android.widget","android.view")
        //当前系统控件树根节点
        var root= ClassNode("View")
        private val viewNodes = mutableListOf(root)
        //当前缓存所有节点
        init {
            //通过views.properties构建系统控件的继承树关系,这一步非常关键
            val classLoader = CustomViewWrapper::class.java.classLoader
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
         * 控件的继承层级节点
         */
        class ClassNode(val className:String){
            //父级节点
            var parent: ClassNode?=null
            //子节点
            val children= mutableListOf<ClassNode>()

            override fun equals(other: Any?): Boolean {
                return className==(other as? ClassNode)?.className
            }
            override fun toString()=className
        }

        /**
         * 查找到节点的系统基类view
         */
        fun getParentView(project: Project, node: ViewNode):View? {
            var findClass = JavaPsiFacade.getInstance(project).findClass(node.name, GlobalSearchScope.everythingScope(project))
            while (null != findClass) {
                val qualifiedName = findClass.qualifiedName
                if (null != qualifiedName) {
                    val index = qualifiedName.lastIndexOf(".")
                    val packageName = qualifiedName.substring(0, index)
                    val className = qualifiedName.substring(index + 1)
                    //代表为系统控件
                    if (androidPackages.any{it==packageName}) {
                        val classNode = viewNodes.find { className == it.className }
                        return if(null!=classNode){
                            try{
                                val clazz=Class.forName(WIDGET_PACKAGE +"."+classNode.className)
                                clazz.newInstance() as View
                            } catch (e:Exception){
                                null
                            }
                        } else null
                    }
                }
                findClass = findClass.superClass
            }
            return null
        }
    }

    /**
     * 获得包装控件
     */
    abstract fun getWrapperView():View


}