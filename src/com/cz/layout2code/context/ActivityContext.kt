package com.cz.layout2code.context

import com.cz.layout2code.inflate.expression.value.DefineClassExpression
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class ActivityContext(project: Project) : BaseContext(project) {

    override fun getJavaField(field: String): String {
        return when(field){
            "context"->"this"
            "resources"->{
                //Resources resources = getResources();
                addPreExpression(DefineClassExpression("Resources",field))
                field
            }
            else -> field
        }
    }

    override fun getKotlinField(field: String): String {
        return when(field){
            "context"->"this"
            "resources"->"resources"
            else -> field
        }
    }

}