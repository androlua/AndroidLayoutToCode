package com.cz.layout2code.convert

/**
 * Created by cz on 2018/3/1.
 */
class KotlinConvert:ElementConvert {

    override fun convert(): String {
        val out=StringBuilder()
//        try {
//            val clazz = Class.forName(PACKAGE_NAME + "." + name)
//            val view = clazz.newInstance() as View
//            val tab="".padEnd(level,'\t')
//            out.append("$tab${view.getViewName()}{\n")
//            val value = view.convert(element, level,toAnko)
//            out.append(value)
//            element.children.forEach{
//                val layoutParams=(view as ViewGroup).getLayoutParams()
//                inflateElement(it,out,layoutParams,level+1)
//            }
//            out.append("$tab}")
//            //收集layoutParams属性
//            layoutParams.inflateAttributes(element)
//            val layoutParamsItem=layoutParams.attributes.find { it is LayoutParamsConvertItem }
//            if(null!=layoutParamsItem){
//                layoutParams.attributes.remove(layoutParamsItem)
//                out.append(".lparams(")
//                out.append(layoutParamsItem.toKotlinString())
//                out.append(")")
//            }
//            if(layoutParams.attributes.isNotEmpty()){
//                out.append("{\n")
//                val paramsTab="".padEnd(level+1,'\t')
//                layoutParams.attributes.forEach {
//                    if(toAnko){
//                        it.toKotlinString()?.lines()?.forEach {
//                            out.append("$paramsTab$it\n")
//                        }
//                    } else {
//                        out.append(it.toJavaString()+"\n")
//                    }
//                }
//                out.append("$tab}")
//            }
//            out.append("\n")
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        return out.toString()
    }
}