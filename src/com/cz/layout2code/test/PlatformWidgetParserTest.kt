package parser

import cz.sample.kotlin.parse.ClassItem
import cz.sample.kotlin.parse.ClassRefItem
import org.junit.Test
import java.io.File

/**
 * Created by cz on 2017/12/15.
 * 工具扫描测试,自动扫描本机内最新的sdk版本源码,然后分析出所有的控件attr
 */
class PlatformWidgetParserTest {

    //空状态
    val NONE=0x00
    //单行注释模式,单行模式是为了防止注释内的class关键字等信息,出现解析错误
    val SINGLE_DOC_SPACE =0x01
    //进入注释文档区域
    val DOC_SPACE =0x02
    //进入类作用域
    val CLASS_SPACE=0x04
    //类声明阶段
    val DEF_CLASS=0x08

    /**
     * 扫描
     */
    @Test
    fun parse(){
        //TODO 此处动态取用户目录下的adb path
//        val folder= File("/Users/cz/Library/Android/sdk/sources/android-26/android")
        val folder=File("D:\\Android\\Sdk\\sources\\android-25\\android")
        //合并目录内所有的类信息
        val classItems = mergeFolder(File(folder,"/view/"), File(folder,"/widget/"))
        val classRefItems= mutableListOf<ClassRefItem>()
        //解析出所有的类文档类引用
        classItems.forEach { parseClassItem(it,null,classRefItems) }
        //移除无用类,只保留类为View的无基类的class
        classRefItems.removeAll { !isValid(it,classRefItems) }
        //移除其他无用的内部类
        classRefItems.forEach { it.innerClassRefItems.removeAll { it.className!="LayoutParams"&&it.className!="MarginLayoutParams" } }
        //生成实现类
        generateViewClass(classRefItems)

    }

    /**
     * 生成控件类
     * 生成代码类,可改为由freemark的框架生成,此处比较简单,所以直接拼出
     */
    private fun generateViewClass(classRefItems: MutableList<ClassRefItem>) {
        classRefItems.forEach { item->
            val file=File("src/com.cz.layout2code/inflate/impl/${item.className}.kt")
            val packageName="com.cz.layout2code.inflate.impl"
            val out=StringBuilder()
            out.append("package $packageName\n\n")
            //导包信息
            out.append("import org.jdom.Element\n")
            buildClass(out,item)
            file.createNewFile()
            file.writeText(out.toString())
        }
    }

    private fun buildClass(out: StringBuilder, classRefItem:ClassRefItem) {
        //注释信息
        out.append("/**\n")
        out.append(" * Created by cz on 2017/12/19.\n")
        out.append(" * \n")
        out.append(" * ---------------${classRefItem.className} all expressions---------------\n")
        classRefItem.refItems.forEach { out.append(" * $it\n") }
        out.append(" *\n")
        out.append(" */\n")

        //class信息
        if (null != classRefItem.superClass) {
            out.append("open class ${classRefItem.className} : ${classRefItem.superClass}() {\n")
        } else {
            out.append("open class ${classRefItem.className} {\n")
        }
        //声明属性
        if(null==classRefItem.superClass){
            out.append("\n\tval expressions= mutableListOf<String>()\n\n")
        }

        out.append("\t\n")
        out.append("\t/**\n")
        out.append("\t * 解析${classRefItem.className}属性集,并返回解析后的anko代码\n")
        out.append("\t */\n")

        var modifier=if(classRefItem.superClass==null) "open" else "override"
        //根据属性生成引用判断列
        out.append("\t$modifier fun inflateLayoutAttributes(element:Element){\n")
        if(null!=classRefItem.superClass){
            out.append("\t\tsuper.inflateLayoutAttributes(element)\n")
        }

        if(classRefItem.attributes.isNotEmpty()){
            out.append("\t\telement.expressions.forEach {\n")
            out.append("\t\t\tval name=it.name\n")
            out.append("\t\t\twhen(name){\n")
            //生成属性集
            classRefItem.attributes.forEach {
                out.append("\t\t\t\t\"$it\"->{\n")
                out.append("\t\t\t\t\n")
                out.append("\t\t\t\t}\n")
            }
            out.append("\t\t\t}\n")
            out.append("\t\t}\n")
        }


        out.append("\t}\n\n")

        if(classRefItem.innerClassRefItems.isNotEmpty()){
            modifier=if(classRefItem.className=="ViewGroup") "open"
            else if(classRefItem.superClass=="ViewGroup") "override" else ""
            out.append("\t$modifier fun getLayoutParams()=LayoutParams()\n\n")
        }

        //生成内部内
        classRefItem.innerClassRefItems.forEach {
            buildLayoutParamsClass(out,it)
        }
        //结束class
        out.append("}")
        out.append("\n")
    }

    private fun buildLayoutParamsClass(out: StringBuilder, classRefItem:ClassRefItem) {
        //注释信息
        out.append("\t/**\n")
        out.append("\t * Created by cz on 2017/12/19.\n")
        out.append("\t * \n")
        out.append("\t * ---------------${classRefItem.className} all expressions---------------\n")
        classRefItem.refItems.forEach { out.append("\t * $it\n") }
        out.append("\t *\n")
        out.append("\t */\n")

        //class信息
        if (null != classRefItem.superClass) {
            out.append("\topen class ${classRefItem.className} : ${classRefItem.superClass}() {\n")
        } else {
            out.append("\topen class ${classRefItem.className} {\n")
        }
        if(null==classRefItem.superClass){
            out.append("\n\t\tval expressions= mutableListOf<String>()\n")
        }
        out.append("\t\t\n")
        out.append("\t\t/**\n")
        out.append("\t\t * 解析${classRefItem.className}属性集,并返回解析后的anko代码\n")
        out.append("\t\t */\n")
        val modifier=if(classRefItem.superClass==null) "open" else "override"
        out.append("\t\t$modifier fun inflateLayoutAttributes(element:Element){\n")
        if(null!=classRefItem.superClass){
            out.append("\t\t\tsuper.inflateLayoutAttributes(element)\n")
        }
        //根据属性生成引用判断列
        if(classRefItem.attributes.isNotEmpty()){
            out.append("\t\t\telement.expressions.forEach {\n")
            out.append("\t\t\t\tval name=it.name\n")
            out.append("\t\t\t\twhen(name){\n")

            //生成属性集
            classRefItem.attributes.forEach {
                out.append("\t\t\t\t\t\"$it\"->{\n")
                out.append("\t\t\t\t\t\n")
                out.append("\t\t\t\t\t}\n")
            }
            out.append("\t\t\t\t}\n")
            out.append("\t\t\t}\n")
        }
        out.append("\t\t}\n\n")

        //结束class
        out.append("\t}\n")
        out.append("\t\n")
    }

    /**
     * Class引用是否有效
     */
    private fun isValid(classRefItem: ClassRefItem,classRefItems: MutableList<ClassRefItem>):Boolean{
        if(null==classRefItem.superClass){
            return "View"==classRefItem.className
        } else {
            val superClassRef = classRefItems.find { it.className == classRefItem.superClass }
            if(null!=superClassRef){
                return isValid(superClassRef,classRefItems)
            } else {
                return false
            }
        }
    }

    /**
     * 测试正则解析出定声明
     */
    @Test
    fun parseClassDef(){
        val text="AbsListView extends AdapterView<ListAdapter> implements TextWatcher,"+
            "ViewTreeObserver.OnGlobalLayoutListener, Filter.FilterListener,"+
            "ViewTreeObserver.OnTouchModeChangeListener,"+
            "RemoteViewsAdapter.RemoteAdapterConnectionCallback "
        val matcher="(?<class>[^<\\s]+)\\s+(extends\\s+(?<super>[^<\\s]+))?".toPattern().matcher(text)
        if(matcher.find()){
            println(matcher.group("class"))
            println(matcher.group("super"))
        }
    }

    /**
     * 合并文件夹
     */
    fun mergeFolder(vararg folders:File):MutableList<ClassItem>{
        val classItems= mutableListOf<ClassItem>()
        for(folder in folders){
            classItems+=folder.listFiles().
                    filter { it.name.endsWith(".java") }.
                    map{parseSourceFile(it)}.
                    filter { it.classDef.isNotEmpty()&&it.document.isNotEmpty() }//过滤掉没有检索出来信息,如interface
        }
        return classItems
    }

    /**
     * 解析源文件,只需要解析出 class对应文档即可,包括内部类,以及文档,并不做更复杂解析
     */
    fun parseSourceFile(file:File): ClassItem {
        //类信息
        val classItem= ClassItem()
        //内部内信息
        var innerClassItem= ClassItem()
        //这里不使用流读字节,不使用缓冲区,速度很慢
        val text = file.readText()
        //注释体信息
        val document=StringBuilder()
        //类名记录操作字符对象
        val classDef=StringBuilder()
        var character=StringBuilder()
        var status=NONE
        for(char in text.toCharArray()){
            if('\n'!=char && ' ' !=char){
                character.append(char)
            } else {
                if(character.isNotBlank()){
                    //判断当前代码所处使用状态
                    val word=character.toString()
                    if("//"==word||word.startsWith("//")){
                        //进入单行模式
                        status = status or SINGLE_DOC_SPACE
                    } else if("/**"==word||"/*"==word){
                        status = status or DOC_SPACE
                        //记录信息
                        document.delete(0,document.length)
                        document.append(character.toString())
                    } else if("*/"==word||word.endsWith("*/")){
                        //离开文档区间
                        status= status xor DOC_SPACE
                    } else if("class"==word){
                        //当类作用域不在注释时,出现类声明
                        if(0==(status and DOC_SPACE)&&0==(status and SINGLE_DOC_SPACE)){
                            status=status or DEF_CLASS
                        }
                    } else if("{"==word||word.endsWith("{")){
                        if(0!=(status and DEF_CLASS)){
                            //正式进入类作用域,为减少代码复杂度,只采集一份文档注释
                            if(0== CLASS_SPACE and status){
                                //外部类作用域
                                classItem.classDef =classDef.substring(0,classDef.indexOf("{"))
                                classItem.document=document.toString()
                            } else {
                                //内部内,只记录LayoutParams
                                innerClassItem.classDef =classDef.substring(0,classDef.indexOf("{"))
                                innerClassItem.document=document.toString()
                                //添加到内部类中
                                classItem.innerClasses.add(innerClassItem)
                                //生成新的内部类
                                innerClassItem= ClassItem()
                            }
                            //清除类声明
                            classDef.delete(0,classDef.length)
                            //离开声明阶段
                            status=status xor DEF_CLASS
                            //进入class作用域
                            status=status or CLASS_SPACE
                        }
                    }
                    //文档注释会因为遇到第一个{(代码类定义) 或者;(字段声明)结束 时被清理
                    if(0==status and DOC_SPACE&&("{"==word||word.endsWith("{")||";"==word||word.endsWith(";"))){
                        document.delete(0,document.length)
                    }
                }
                character.delete(0,character.length)
                //判断单行模式,在换行后退出单行模式作用域
                if('\n'==char&&0!=status and SINGLE_DOC_SPACE){
                    status=status xor SINGLE_DOC_SPACE
                }
            }

            //记录所有信息
            if(0!=(DOC_SPACE and status)){
                //当前注释节点内,采集信息
                document.append(char)
            } else if(0!=(DEF_CLASS and status)){
                //当前为声明类阶段
                classDef.append(char)
            }
        }
        return classItem
    }

    /**
     * 解析Class条目注释内,所有ref 属性定义类
     */
    private fun parseClassItem(classItem: ClassItem, outerRefClass: ClassRefItem?, items: MutableList<ClassRefItem>) {
        //分析所有文件 记录带 @attr ref android.R.styleable#View_alpha 此类属性控件
        //分析步骤,提取类文本所有/* */ /** */范围内文档注意,分析文档注释
        //分析引用信息
        val classRefItem= ClassRefItem()
        //步骤1:分析类声明
        val matcher="(?<class>[^<\\s]+)\\s+(extends\\s+(?<super>[^<\\s]+))?".toPattern().matcher(classItem.classDef)
        if(matcher.find()){
            classRefItem.className=matcher.group("class")
            classRefItem.superClass=matcher.group("super")
        }
        //步骤2:分析所有文本下,class类名  public static class LayoutParams { 此类
        val refPattern1="@attr\\s+ref\\s+android.R.styleable#(\\w+)".toPattern()
        //采用正则环神,匹配出LayoutParams layout_marginTop等属性
        val stylePattern1="(?:(?=(\\w+))layout)".toPattern()
        val stylePattern2="(?:_(\\w+))$".toPattern()
        //提取 @attr ref android.R.styleable#View_scaleY
        //@attr ref android.R.styleable#ViewGroup_Layout_layout_height
        val document=classItem.document
        //记录内部类引用
        if(null==outerRefClass){
            //记录引用,代表其为最外层class
            items.add(classRefItem)
        } else {
            //记录内部类
            outerRefClass.innerClassRefItems.add(classRefItem)
        }
        if(document.isNotEmpty()){
            classItem.document.lines().forEach {
                val matcher = refPattern1.matcher(it)
                if (matcher.find()) {
                    val ref = matcher.group()
                    //添加所有引用设定信息
                    classRefItem.refItems.add(ref)
                    val matcher1 = stylePattern1.matcher(ref)
                    val matcher2 = stylePattern2.matcher(ref)
                    val attribute = if (matcher1.find())
                        matcher1.group(1) else if (matcher2.find())
                        matcher2.group(1) else null
                    if (null == attribute) {
                        println("Ref:$ref attribute is null!")
                    } else {
                        classRefItem.attributes.add(attribute)
                    }
                }
            }
        }
        //记录子分类引用
        classItem.innerClasses.forEach { parseClassItem(it,classRefItem,items) }
    }

}