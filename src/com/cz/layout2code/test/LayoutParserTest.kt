import com.cz.layout2code.inflate.AndroidLayoutInflater
import org.junit.Test
import java.io.File
import org.jdom.Element


/**
 * Created by cz on 2017/12/18.
 */
class LayoutParserTest{

    /**
     * 测试解析layout布局
     */
    @Test
    fun testParser(){
        val file= File("src/com/cz/layout2code/test/activity_test.xml")
//        AndroidLayoutInflater.inflater(file)
//        val builder = SAXBuilder()//实例JDOM解析器
//        val document = builder.build(file)//读取xml文件
//        printXmlNode(document.rootElement,0)
    }

    /**
     * 打印xml节点
     */
    fun printXmlNode(element: Element,level:Int){
        //打印属性
        println("".padEnd(level,'\t')+element.name)
        for(attribute in element.attributes){
            println("".padEnd(level+1,'\t')+"${attribute.namespace.prefix}:${attribute.name} ${attribute.value}")
        }
        element.children.forEach { printXmlNode(it,level+1) }
    }
}