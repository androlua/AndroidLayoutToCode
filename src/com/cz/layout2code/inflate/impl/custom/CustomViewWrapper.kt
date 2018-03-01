package com.cz.layout2code.inflate.impl.custom

import com.cz.layout2code.inflate.impl.IView
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project
import com.intellij.psi.JavaPsiFacade
import com.intellij.psi.search.GlobalSearchScope
import java.util.*

/**
 * Created by cz on 2018/2/27.
 * 标记自定义控件包装器对象,用于动态化的控件解析
 */
class CustomViewWrapper(val view:View,val node: ViewNode) :View(){
    companion object{
        //检测控件包名
        val PACKAGE_NAME="com.cz.layout2code.inflate.impl"
        val ANDROID_PACKAGE="android.widget"
        //当前控件树根节点
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
        private fun findSystemParentView(project: Project, node: ViewNode):View? {
            var findClass = JavaPsiFacade.getInstance(project).findClass(node.name, GlobalSearchScope.everythingScope(project))
            while (null != findClass) {
                val qualifiedName = findClass.qualifiedName
                if (null != qualifiedName) {
                    val index = qualifiedName.lastIndexOf(".")
                    val packageName = qualifiedName.substring(0, index)
                    val className = qualifiedName.substring(index + 1)
                    //代表为系统控件
                    if (ANDROID_PACKAGE == packageName) {
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
            val findParentView = findSystemParentView(project, node)
            if(null!=findParentView) {
                //父级对象,增加自定义控件属性
                result=CustomViewWrapper(findParentView,node)
            }
            return result
        }
    }

    override fun getClassName(): String =node.name

    override fun convert(viewNode: ViewNode, toJava: Boolean): String {
        val out=StringBuilder()
        val tab="".padEnd(viewNode.level+1,'\t')
        inflateAttributes(viewNode)
        //系统控件属性
        view.attributes.forEach {
            if(toJava){
                out.append("$tab${it.toJavaString()}\n")
            } else {
                out.append("$tab${it.toKotlinString()}\n")
            }
        }
        //自定义控件属性
        attributes.forEach {
            if(toJava){
                out.append("$tab${it.toJavaString()}\n")
            } else {
                out.append("$tab${it.toKotlinString()}\n")
            }
        }
        return out.toString()
    }

    override fun inflateAttributes(viewNode: ViewNode) {
        //先装载被包装父属性
        view.inflateAttributes(viewNode)
        //包装自定义属性
        viewNode.attributes.forEach { addAttributeItems(it.name,it.value) }
    }
}