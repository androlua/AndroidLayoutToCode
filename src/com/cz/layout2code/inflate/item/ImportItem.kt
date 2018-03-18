package com.cz.layout2code.inflate.item

/**
 * Created by cz on 2017/12/23.
 */
class ImportItem(private val classReference:String): Comparable<ImportItem>{

    fun getJavaExpression()="import $classReference;"

    fun getKotlinExpression()="import $classReference"

    override fun compareTo(other: ImportItem)=classReference.compareTo(other.classReference)

    override fun equals(other: Any?): Boolean {
        if(null==other||other !is ImportItem) return false
        return classReference == other.classReference
    }

    override fun toString(): String=getJavaExpression()

}