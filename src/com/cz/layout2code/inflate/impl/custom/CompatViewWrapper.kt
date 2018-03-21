package com.cz.layout2code.inflate.impl.custom

import com.cz.layout2code.inflate.impl.LinearLayout
import com.cz.layout2code.inflate.impl.View
import com.cz.layout2code.inflate.item.ViewNode
import com.intellij.openapi.project.Project

class CompatViewWrapper(private val wrapperView: View, val node:ViewNode): ViewWrapper() {
    companion object {
        //兼容的特殊条目,如LinearLayoutCompat为单独实现
        private val compatItems= mutableListOf<CompatItem>()

        init {
            compatItem{
                className="LinearLayoutCompat"
                wrapperView= LinearLayout()
            }
        }
        /**
         * 包装appCompat View控件列
         */
        fun wrapper(project: Project, node:ViewNode): ViewWrapper? {
            //查找此类是否存在,并一直追查到类的继承到父级
            var result: CompatViewWrapper?=null
            val compatItem = compatItems.find { it.className == node.simpleName }
            if(null!=compatItem){
                //转换条目
                result=CompatViewWrapper(compatItem.wrapperView,node)
            } else {
                val parentView = getParentView(project, node)
                if(null!=parentView) {
                    //父级对象,增加自定义控件属性
                    result=CompatViewWrapper(parentView,node)
                }
            }
            return result
        }

        /**
         * 兼容条目
         */
        class CompatItem{
            //view名称
            lateinit var className:String
            //兼容View
            lateinit var wrapperView:View
        }

        private fun compatItem(action:CompatItem.()->Unit){
            val item= CompatItem().apply(action)
            compatItems.add(item)
        }
    }

    override fun getViewName(): String=wrapperView.getViewName()

    override fun getClassPath(): String =wrapperView.getClassPath()

    override fun getClassName(): String =wrapperView.getClassName()

    override fun getThemeViewName(): String =wrapperView.getThemeViewName()

    override fun inflateAttributes(viewNode: ViewNode) {
        //先装载被包装父属性
        wrapperView.inflateAttributes(viewNode)
    }

    override fun getWrapperView(): View =wrapperView
}