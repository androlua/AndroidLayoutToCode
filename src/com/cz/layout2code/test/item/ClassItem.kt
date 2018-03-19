package cz.sample.kotlin.parse

/**
 * Created by Administrator on 2017/12/16.
 */
class ClassItem{
    //类名
    var classDef =String()
    //文档注释
    var document= String()
    //内部类
    val innerClasses= mutableListOf<ClassItem>()

    override fun toString(): String {
        return classDef
    }
}