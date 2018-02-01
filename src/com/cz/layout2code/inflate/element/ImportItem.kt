package com.cz.layout2code.inflate.element

/**
 * Created by cz on 2017/12/23.
 */
class ImportItem(val import:String): ElementConvert,Comparable<ImportItem>{

    override fun toKotlinString(): String = "import $import"
    override fun toJavaString(): String = "import $import;"

    override fun compareTo(other: ImportItem)=import.compareTo(other.import)

}