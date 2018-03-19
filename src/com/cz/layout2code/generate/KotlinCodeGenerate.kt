package com.cz.layout2code.generate

import com.cz.layout2code.inflate.expression.value.DefineClassExpression
import com.cz.layout2code.inflate.impl.ViewGroup
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.context.BaseContext
import com.intellij.openapi.project.Project
import com.intellij.util.containers.isNullOrEmpty

/**
 * Created by cz on 2018/3/1.
 */
class KotlinCodeGenerate : BaseCodeGenerate() {
    private val out=StringBuilder()
    /**
     * 转换代码为kotlin 输入
     * 自定义控件dsl扩展为
     * inline fun ViewManager.newViewPager(theme: Int = 0, init: NewViewPager.() -> Unit) = ankoView({ NewViewPager(it) }, theme, init)
     */
    override fun generate(project: Project, baseMatcher: BaseContext, node: ViewNode, layoutParams:ViewGroup.LayoutParams?) {
        var layoutParams=layoutParams
        //从节点获取view
        val view = getViewFromNode(project, node)
        if(null==view){
            //遍历子孩子节点
            node.children.forEach {
                //遍历子节点
                generate(project,baseMatcher,it,layoutParams)
            }
        } else {
            //装载属性
            view.inflateAttributes(node)
            //控件声明
            val viewDefineItem=DefineClassExpression(view,node.name)
            out.append("${"".padEnd(node.level,'\t')}${viewDefineItem.getKotlinExpression(baseMatcher)}{\n")
            //父容器
            var layoutDimension = layoutParams?.inflateLayoutDimension(node,false)
            var layoutAttributes =layoutParams?.inflateLayoutAttributes(node)
            if(view is ViewGroup){
                layoutParams = view.getLayoutParams()
            }
            val tab="".padEnd(node.level+1,'\t')
            //开始正常属性组合
            view.expressions.forEach {
                out.append("$tab${it.getKotlinExpression(baseMatcher)}\n")
            }
            //未知的属性引用集
            val unknownAttributes = node.getUnknownAttributeExpressions()
            unknownAttributes.forEach {
                out.append("$tab${it.getKotlinExpression(baseMatcher)}\n")
            }
            //遍历子孩子节点
            node.children.forEach {
                //遍历子节点
                generate(project,baseMatcher,it,layoutParams)
            }
            //闭合节点
            out.append("${"".padEnd(node.level,'\t')}}")
            //layout属性设定
            val layoutAttributesEmpty=layoutAttributes.isNullOrEmpty()
            if(null!=layoutDimension){
                out.append(".${layoutDimension.getKotlinExpression(baseMatcher)}").
                        append(if(layoutAttributesEmpty)"\n" else "{\n")
            }
            //添加layout属性
            layoutAttributes?.forEach {
                out.append("$tab${it.getKotlinExpression(baseMatcher)}\n")
            }
            //闭合
            if(!layoutAttributesEmpty){
                out.append("${"".padEnd(node.level,'\t')}}\n")
            }
        }
    }
}