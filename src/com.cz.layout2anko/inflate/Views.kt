package com.cz.layout2anko.inflate

import com.cz.layout2anko.inflate.impl.View
import com.cz.layout2anko.inflate.impl.ViewGroup

/**
 * Created by cz on 2017/12/20.
 */
val DIMEN_PATTERN="(?:(?<float>\\d+\\.\\d+)|(?<int>\\d+))(?<unit>(?:di?p)|(?:sp)|(?:px))?".toPattern()
//?android:attr/actionBarDivider   ?attr/actionBarDivider  ?actionBarDivider 三种引用方式
val RESOURCE_PATTERN="(@\\+?(?<type>\\w+)/(?<res>.+))|(\\?(?<android>android:)?(attr/)?(?<attr>\\w+))".toPattern()

/**
 * 转换尺寸
 */
fun View.dimen(value:String):String=dimen(value)
fun ViewGroup.LayoutParams.dimen(value:String):String=dimen(value)

/**
 * 转换string
 */
fun View.string(value:String):String=string(value)
fun ViewGroup.LayoutParams.string(value:String):String=string(value)


fun View.int(value:String):String=int(value)
fun ViewGroup.LayoutParams.int(value:String):String=int(value)

fun View.float(value:String):String=float(value)
fun ViewGroup.LayoutParams.float(value:String):String=float(value)


fun View.long(value:String):String=long(value)
fun ViewGroup.LayoutParams.long(value:String):String=long(value)

fun View.bool(value:String):String=bool(value)
fun ViewGroup.LayoutParams.bool(value:String):String=bool(value)

fun View.color(value:String):String=color(value)
fun ViewGroup.LayoutParams.color(value:String):String=color(value)

fun View.id(value:String):String=id(value)
fun ViewGroup.LayoutParams.id(value:String):String=id(value)

fun View.colorStateList(value:String):String=colorStateList(value)
fun ViewGroup.LayoutParams.colorStateList(value:String):String=colorStateList(value)

fun View.resource(value:String):String=resource(value)
fun ViewGroup.LayoutParams.resource(value:String):String=resource(value)

fun View.resourceRef(value:String):String=resourceRef(value)
fun ViewGroup.LayoutParams.resourceRef(value:String):String=resourceRef(value)

fun View.drawingCacheQuality(quality:String)=drawingCacheQuality(quality)
fun View.layerType(type:String)=layerType(type)
fun View.layoutDirection(direction:String)=layoutDirection(direction)
fun View.scrollBarStyle(style:String)=scrollBarStyle(style)
fun View.textDirection(direction:String)=textDirection(direction)
fun View.visibility(visible:String)=visibility(visible)
fun View.textAlignment(alignment:String)=textAlignment(alignment)
fun View.gravity(gravity:String)=gravity(gravity)

/**
 * LayoutParams的布局方位
 */
fun ViewGroup.LayoutParams.gravity(gravity:String)=gravity(gravity)
/**
 * LayoutParams的布局属性
 * 兼容 wrap_content/fill_parent or (数值sp/dp/dip) or 引用 @dimen/actionBarSize 以及?actionBarSize/?attrs/actionBarSize/?android:attrs/actionBarSize
 */
fun ViewGroup.LayoutParams.layoutDimension(value:String):String=layoutDimension(value)

fun dimen(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return dimensionValue(value)
    }
}

fun string(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return value
    }
}


fun layout(value: String)=when(value){
    "match_parent"->"ViewGroup.LayoutParams.MATCH_PARENT"
    "fill_parent"->"ViewGroup.LayoutParams.FILL_PARENT"
    else->"ViewGroup.LayoutParams.WRAP_CONTENT"
}

private fun layoutDimension(value:String):String{
    if(value.startsWith("@")){
        //引用
        return resource(value)
    } else if(DIMEN_PATTERN.matcher(value).matches()){
        //数值
        return dimensionValue(value)
    } else {
        //布局数值
        return layout(value)
    }
}

private fun dimensionValue(value: String): String {
    //数值分为
    // float:浮点型,后加f
    // sp/dp/dip/px
    // 固定值
    var result = String()
    val matcher = DIMEN_PATTERN.matcher(value)
    if (matcher.find()) {
        val int = matcher.group("int")
        var float = matcher.group("float")
        if(null!=float) float+="f"
        val unit = matcher.group("unit")
        val number = int?:float?:"0"
        if (null == unit) {
            result = number
        } else {
            when (unit) {
                "sp" -> result = "sp($number)"
                "dip", "dp" -> result = "dp($number)"
                else ->result = number
            }
        }
    }
    return result
}

fun int(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return dimen(value)
    }
}

fun float(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return value+"f"
    }
}

fun long(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return value+"l"
    }
}

fun bool(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return value
    }
}

fun color(value:String):String{
    if(value.startsWith("@")){
        return resource(value)
    } else {
        return value
    }
}

fun id(value:String)=resource(value)

fun colorStateList(value:String):String{
    var result=String()
    val matcher= View.RESOURCE_PATTERN.matcher(value)
    if(matcher.find()) {
        val type = matcher.group("type")
        val ref = matcher.group("ref")
        val attr=matcher.group("attr")
        if(null!=attr){
            //资源引用
            return "R.attr.$attr"
        } else if("drawable"==type){
            result = "ContextCompat.getColorStateList(context,R.drawable.$ref)"
        } else {
            result = "//$ref is not a drawable ref! Can't reverse it!"
        }
    }
    return result
}

/**
 * 资源引用
 */
fun resource(value:String):String{
    //资源引用
    var result=String()
    val matcher= View.RESOURCE_PATTERN.matcher(value)
    if(matcher.find()){
        val type = matcher.group("type")
        val ref = matcher.group("ref")
        val attr=matcher.group("attr")
        if(null!=attr){
            //资源引用
            return "R.attr.$attr"
        } else {
            when(type){
                "id"->result="R.id.$ref"
                "string"->result="resources.getString(R.string.$ref)"
                "dimen"->result="resources.getDimension(R.dimen.$ref)"
                "integer"->result="resources.getInteger(R.integer.$ref)"
                "color"->result="ContextCompat.getColor(context,R.color.$ref)"
                "drawable"->result="ContextCompat.getDrawable(R.drawable.$ref)"
                "mipmap"->result="ContextCompat.getDrawable(R.mipmap.$ref)"
                "string"->result="context.getString(R.string.$ref)"
                "array"->result="resources.getStringArray(R.array.$ref)"
                "theme"->result="setTheme(R.style.$ref)"
                "anim"->result="AnimationUtils.loadAnimation(context,R.anim.$ref)"
                "bool"->result="resources.getBoolean(R.bool.$ref)"
                "animator"->result="AnimatorInflater.loadAnimator(context,R.animator.$ref)"
            }
        }
    }
    return result
}

/**
 * 资源引用
 */
fun resourceRef(value:String):String{
    //资源引用
    var result=String()
    val matcher= View.RESOURCE_PATTERN.matcher(value)
    if(matcher.find()){
        val type = matcher.group("type")
        val ref = matcher.group("ref")
        val attr=matcher.group("attr")
        if(null!=attr){
            //资源引用
            return "R.attr.$attr"
        } else {
            when(type){
                "id"->result="R.id.$ref"
                "dimen"->result="R.dimen.$ref"
                "integer"->result="R.integer.$ref"
                "color"->result="context,R.color.$ref"
                "drawable"->result="R.drawable.$ref"
                "mipmap"->result="R.mipmap.$ref"
                "string"->result="R.string.$ref"
                "array"->result="R.array.$ref"
                "theme"->result="R.style.$ref"
                "anim"->result="R.anim.$ref"
                "animator"->result="R.animator.$ref"
            }
        }
    }
    return result
}

fun drawingCacheQuality(quality:String)=when(quality){
    "low"->"View.DRAWING_CACHE_QUALITY_LOW"
    "high"->"View.DRAWING_CACHE_QUALITY_HIGH"
    else ->"View.DRAWING_CACHE_QUALITY_AUTO"
}

/**
 * View.LAYER_TYPE_NONE
 * View.LAYER_TYPE_HARDWARE
 * View.LAYER_TYPE_SOFTWARE
 */
fun layerType(type:String)=when(type){
    "hardware"->"View.LAYER_TYPE_HARDWARE"
    "software"->"View.LAYER_TYPE_SOFTWARE"
    else->"View.LAYER_TYPE_NONE"
}
/**
 * {@link #LAYOUT_DIRECTION_LTR},
 * {@link #LAYOUT_DIRECTION_RTL},
 * {@link #LAYOUT_DIRECTION_INHERIT},
 * {@link #LAYOUT_DIRECTION_LOCALE}.
 */
fun layoutDirection(direction:String)=when(direction){
    "rtl"->"View.LAYOUT_DIRECTION_RTL"
    "inherit"->"View.LAYOUT_DIRECTION_INHERIT"
    "locale"->"View.LAYOUT_DIRECTION_LOCALE"
    else ->"View.LAYOUT_DIRECTION_LTR"
}

/**
 * @see #SCROLLBARS_INSIDE_OVERLAY
 * @see #SCROLLBARS_INSIDE_INSET
 * @see #SCROLLBARS_OUTSIDE_OVERLAY
 * @see #SCROLLBARS_OUTSIDE_INSET
 */
fun scrollBarStyle(style:String)=when(style){
    "overlay"->"View.SCROLLBARS_INSIDE_OVERLAY"
    "inset"->"View.SCROLLBARS_INSIDE_INSET"
    "overlay"->"View.SCROLLBARS_OUTSIDE_OVERLAY"
    else ->"View.SCROLLBARS_OUTSIDE_INSET"
}

/**
 * {@link #TEXT_DIRECTION_INHERIT},
 * {@link #TEXT_DIRECTION_FIRST_STRONG},
 * {@link #TEXT_DIRECTION_ANY_RTL},
 * {@link #TEXT_DIRECTION_LTR},
 * {@link #TEXT_DIRECTION_RTL},
 * {@link #TEXT_DIRECTION_LOCALE}
 * {@link #TEXT_DIRECTION_FIRST_STRONG_LTR},
 * {@link #TEXT_DIRECTION_FIRST_STRONG_RTL},
 */
fun textDirection(direction:String)=when(direction){
    "inherit"->"View.TEXT_DIRECTION_INHERIT"
    "firstStrong"->"View.TEXT_DIRECTION_FIRST_STRONG"
    "anyRtl"->"View.TEXT_DIRECTION_ANY_RTL"
    "rtl"->"View.TEXT_DIRECTION_RTL"
    "locale"->"View.TEXT_DIRECTION_LOCALE"
    "firstStrongLtr"->"View.TEXT_DIRECTION_FIRST_STRONG_LTR"
    "firstStrongRtl"->"View.TEXT_DIRECTION_FIRST_STRONG_RTL"
    else->"View.TEXT_DIRECTION_LTR"
}

/**
 * {@link #VISIBLE}
 * {@link #INVISIBLE}
 * {@link #GONE}.
 */
fun visibility(visible:String)=when(visible){
    "invisible"->"View.INVISIBLE"
    "gone"->"View.GONE"
    else->"View.VISIBLE"
}

/**
 * {@link #TEXT_ALIGNMENT_INHERIT},
 * {@link #TEXT_ALIGNMENT_GRAVITY},
 * {@link #TEXT_ALIGNMENT_CENTER},
 * {@link #TEXT_ALIGNMENT_TEXT_START},
 * {@link #TEXT_ALIGNMENT_TEXT_END},
 * {@link #TEXT_ALIGNMENT_VIEW_START},
 * {@link #TEXT_ALIGNMENT_VIEW_END}
 */
fun textAlignment(alignment:String)=when(alignment){
    "inherit"->"View.TEXT_ALIGNMENT_INHERIT"
    "center"->"View.TEXT_ALIGNMENT_CENTER"
    "textStart"->"View.TEXT_ALIGNMENT_TEXT_START"
    "textEnd"->"View.TEXT_ALIGNMENT_TEXT_END"
    "viewStart"->"View.TEXT_ALIGNMENT_VIEW_START"
    "viewEnd"->"View.TEXT_ALIGNMENT_VIEW_END"
    else->"View.TEXT_ALIGNMENT_GRAVITY"
}

/**
 * Gravity.BOTTOM
 * Gravity.CENTER
 * Gravity.CENTER_HORIZONTAL
 * Gravity.CENTER_VERTICAL
 * Gravity.CLIP_HORIZONTAL
 * Gravity.CLIP_VERTICAL
 * Gravity.END
 * Gravity.FILL
 * Gravity.FILL_HORIZONTAL
 * Gravity.FILL_VERTICAL
 * Gravity.LEFT
 * Gravity.RIGHT
 * Gravity.START
 * Gravity.TOP
 */
fun gravity(gravity:String)=when(gravity){
    "bottom"->"Gravity.BOTTOM"
    "center"->"Gravity.CENTER"
    "center_horizontal"->"Gravity.CENTER_HORIZONTAL"
    "center_vertical"->"Gravity.CENTER_VERTICAL"
    "clip_horizontal"->"Gravity.CLIP_HORIZONTAL"
    "clip_vertical"->"Gravity.CLIP_VERTICAL"
    "end"->"Gravity.END"
    "fill"->"Gravity.FILL"
    "fill_horizontal"->"Gravity.FILL_HORIZONTAL"
    "fill_vertical"->"Gravity.FILL_VERTICAL"
    "right"->"Gravity.RIGHT"
    "start"->"Gravity.START"
    "top"->"Gravity.TOP"
    else->"Gravity.LEFT"
}