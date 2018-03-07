package com.cz.layout2code.inflate.impl.custom

import com.cz.layout2code.inflate.element.CustomViewAttributeItem
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.search.GlobalSearchScope
import java.util.*

/**
 * Created by cz on 2018/2/27.
 * 标记自定义控件包装器对象,用于动态化的控件解析
 * 自定义控件,在包装系统控件时,还要形成一个自定义控件继承自定义控件的继承层级解析过程.所以,还会有一个调用链,一直链到系统控件
 */
class CustomViewWrapper(val parent:View,val node: ViewNode) :View(){
    companion object{
        //检测控件包名
        val PACKAGE_NAME="com.cz.layout2code.inflate.impl"
        val androidPackages= mutableListOf("android.widget","android.view")
        //当前系统控件树根节点
        var root= ClassNode("View")
        //当前缓存所有节点
        val viewNodes = mutableListOf(root)
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
         * 查找到系统基类view
         */
        private fun getParentView(project: Project, node: ViewNode):View? {
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
                                Class.forName(PACKAGE_NAME+"."+classNode.className).newInstance() as? View
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

        /**
         * 通过包装扩展自定义控件列
         */
        fun wrapper(project: Project, node:ViewNode): CustomViewWrapper? {
            //查找此类是否存在,并一直追查到类的继承到父级
            var result: CustomViewWrapper?=null
            //设定代理view
            val parentView = getParentView(project, node)
            if(null!=parentView) {
                //父级对象,增加自定义控件属性
                result=CustomViewWrapper(parentView,node)
            }
            return result
        }
    }

    override fun getViewName(): String=node.name

    override fun inflateAttributes(viewNode: ViewNode) {
        //先装载被包装父属性
        parent.inflateAttributes(viewNode)
        //自定义属性
        val customAttributes = node.attributes.filter { "android" != it.nameSpace }
        //包装自定义属性
        customAttributes.forEach {
            applyAttributes(it)
            attributes.add(CustomViewAttributeItem(it.name,it.value))
        }
    }
}