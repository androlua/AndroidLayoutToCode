package com.cz.layout2code.context

import com.cz.layout2code.inflate.expression.value.StringValueExpression
import com.cz.layout2code.inflate.item.ClassItem
import com.cz.layout2code.inflate.item.ImportItem
import com.intellij.openapi.project.Project

class ViewContext(project: Project) : BaseContext(project) {

    override fun getJavaField(field: String): String {
        return when(field){
            "context"->{
                defineClass("Context", field) {
                    StringValueExpression("getContext()")
                }
                field
            }
            "resources"->{
                //Resources resources = getResources();
                defineClass("Resources", field) {
                    StringValueExpression("getResources()")
                }
                field
            }
            else -> field
        }
    }

    override fun getKotlinField(field: String): String {
        return field
    }

    override fun getJavaMethod(method: String): String {
        return when(method){
            "dp"->{
                methodExpression(method){
                    import { mutableListOf(ImportItem("android.util.TypedValue")) }
                    javaExpression {
                        "private float dp(float value){\n" +
                                "\treturn TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,value,getResources().getDisplayMetrics());\n" +
                                "}"
                    }
                }
                method
            }
            "sp"->{
                methodExpression(method){
                    import { mutableListOf(ImportItem("android.util.TypedValue")) }
                    javaExpression {
                        "private float sp(float value){\n" +
                                "\treturn TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,value,getResources().getDisplayMetrics());\n" +
                                "}"
                    }
                }
                method
            }
            else ->method
        }
    }

    override fun getKotlinMethod(method: String): String {
        return method
    }

}