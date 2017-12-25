package com.cz.layout2anko.inflate.item

/**
 * Created by cz on 2017/12/23.
 */
class ImportItem(val import:String,val defaultImport:Boolean=true):AttributeConvert,Comparable<ImportItem>{

    override fun toAnkoString(): String = "import $import"
    override fun toJavaString(): String = "import $import;"

    override fun compareTo(other: ImportItem)=import.compareTo(other.import)

}