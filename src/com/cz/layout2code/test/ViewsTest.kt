import com.cz.layout2code.inflate.impl.Button
import com.cz.layout2code.inflate.impl.custom.CustomViewWrapper
import org.junit.Test
import java.util.*

/**
 * Created by cz on 2017/12/20.
 */
class ViewsTest{
    /**
     * 测试引用资源输出
     */
    @Test
    fun testResource(){
//    //测试资源引用
//    println(resourceRef("@string/stringText"))
//    println(resourceRef("@anim/animText"))
//    println(resourceRef("@drawable/drawableText"))
//
//    //测试资源转换
//    println(resource("@+id/loginButton"))
//    println(resource("@string/login"))
//    println(resource("?attr/actionBarSize"))
//    println(resource("?actionBarSize"))
//    println(resource("?android:attr/actionBarSize"))
//    println(colorStateList("@drawable/text_selector"))
//
//    //测试颜色
//    println(color("@color/white"))
//    println(color("#FFFF0000"))

        //测试数值
//    println(dimensionValue("82"))
//    println(dimensionValue("82dp"))
//    println(dimensionValue("82sp"))

        //测试布局属性
//    println(layoutDimension("82"))
//    println(layoutDimension("82dp"))
//    println(layoutDimension("wrap_content"))
//    println(layoutDimension("@dimen/view_height"))
        val button=Button()
        println(button)
//      测试资源控件读取,并生成控件继承树
        val classLoader = CustomViewWrapper::class.java.classLoader
        val resourceAsStream = classLoader.getResourceAsStream("views.properties")
        val properties= Properties()
        properties.load(resourceAsStream)
        var root:ClassNode?=null
        //所有缓存根节点
        val allNodes= mutableListOf<ClassNode>()
        val cacheNodes= mutableListOf<ClassNode>()
        println("总个数:${properties.size}")
        for (className in properties.stringPropertyNames()) {
            val superClass = properties[className].toString()
            //当前节点
            var node = getClassNode(cacheNodes, className)
            allNodes.add(node)
            if(superClass.isEmpty()){
                //记录根节点
                root=node
            } else {
                //获得父节点
                var parentNode = getClassNode(cacheNodes, superClass)
                //记录父节点
                node.parent=parentNode
                //父节点记录子节点
                parentNode.children.add(node)
            }
        }
        if(null!=root){
            val nodes= mutableListOf<ClassNode>()
            printNode(nodes,root,0)
            allNodes.removeAll(nodes)
            println("总节点数:${nodes.size} 缺少:${properties.size-nodes.size}")
        }

    }

    private fun getClassNode(cacheNodes: MutableList<ClassNode>, superClass: String): ClassNode {
        var parentNode = cacheNodes.find { it.className == superClass }
        if (null == parentNode) {
            parentNode = ClassNode(superClass)
            cacheNodes.add(parentNode)
        }
        return parentNode
    }

    /**
     * 打印节点信息
     */
    private fun printNode(nodes:MutableList<ClassNode>,root: ClassNode, level: Int) {
        val tab="".padStart(level*4,'-')
        nodes.add(root)
        println("$tab${root.className}")
        root.children.forEach { printNode(nodes,it,level+1) }
    }


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