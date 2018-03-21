package com.cz.layout2code.context

import com.cz.layout2code.inflate.item.ClassItem
import com.intellij.openapi.project.Project

class OtherContext(project: Project) : BaseContext(project) {

    override fun getJavaField(field: String): String {
        return field
    }

    override fun getKotlinField(field: String): String {
        return field
    }

    override fun getJavaMethod(method: String): String {
        return method
    }

    override fun getKotlinMethod(method: String): String {
        return method
    }

}