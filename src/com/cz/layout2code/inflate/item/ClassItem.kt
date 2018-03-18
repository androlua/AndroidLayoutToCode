package com.cz.layout2code.inflate.item

/**
 * class引用信息
 */
class ClassItem(val referenceName:String){
    //导包信息
    val importItem: ImportItem = ImportItem(referenceName)
    //类名称
    val className=referenceName.substringAfterLast(".")
}