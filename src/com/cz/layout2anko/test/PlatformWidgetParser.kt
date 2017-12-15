package parser

import org.junit.Test
import java.io.File
import java.util.regex.Pattern

/**
 * Created by cz on 2017/12/15.
 * 工具扫描类,自动扫描本机内最新的sdk版本源码,然后分析出所有的控件attr
 */
class PlatformWidgetParser{
    /**
     * 扫描
     */
    @Test
    fun parse(){
        //此处动态取用户目录下的adb path
        val folder= File("/Users/cz/Library/Android/sdk/sources/android-26/android")
        val viewFolder=File(folder,"/view/")
        val widgetFolder=File(folder,"/widget/")
        val items= mutableMapOf<String,HashSet<String>>()
        //解析view目录
        parseWidgetSourceFolder(viewFolder, items)
        //解析widget目录
        parseWidgetSourceFolder(widgetFolder, items)

        //使用收集的控件,以及属性.生成一批解析class

        println(items)

    }

    /**
     * 解析目录下,所有java源码中带ref 属性定义类
     */
    private fun parseWidgetSourceFolder(viewFolder: File, items: MutableMap<String, HashSet<String>>) {
        //分析所有文件 记录带 @attr ref android.R.styleable#View_alpha 此类属性控件
        //分析步骤,提取类文本所有/* */ /** */范围内文档注意,分析文档注释
        //步骤2:分析所有文本下,class类名  public static class LayoutParams { 此类
        val refPattern1="@attr\\s+ref\\s+android.R.styleable#(\\w+)".toPattern()
        //采用正则环神,匹配出LayoutParams layout_marginTop等属性
        val stylePattern1="(?:(?=(\\w+))layout)".toPattern()
        val stylePattern2="(?:_(\\w+))$".toPattern()
        viewFolder.listFiles().filter { it.isFile }.forEach { file ->
            val text = file.readText()
            //以单行模式,直接提取所有文档注释
            val findItems = "(?s)(/\\*\\*?(.+?)\\*/)".toRegex().findAll(text)
            findItems.forEach {
                val value = it.value
                //提取 @attr ref android.R.styleable#View_scaleY
                //@attr ref android.R.styleable#ViewGroup_Layout_layout_height
                value.lines().forEach {
                    val matcher = refPattern1.matcher(it)
                    if (matcher.find()) {
                        val ref = matcher.group(1)
                        val matcher1 = stylePattern1.matcher(ref)
                        val matcher2 = stylePattern2.matcher(ref)
                        val text = if (matcher1.find())
                            matcher1.group(1) else if (matcher2.find())
                            matcher2.group(1) else null
                        val hashSet = items.getOrPut(file.name) { HashSet<String>() }
                        if (null == text) {
                            println("Ref:$ref attribute is null!")
                        } else {
                            hashSet.add(text)
                        }
                    }
                }
            }
        }
    }

}