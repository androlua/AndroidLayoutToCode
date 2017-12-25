package com.cz.layout2anko.inflate

import com.cz.layout2anko.inflate.impl.*
import com.cz.layout2anko.inflate.prefs.ViewStyle

/**
 * Created by cz on 2017/12/20.
 */
val VERSIONS= arrayOf("#","CUR_DEVELOPMENT","BASE","BASE_1_1","CUPCAKE","DONUT","ECLAIR","ECLAIR_0_1","ECLAIR_MR1","FROYO","GINGERBREAD","GINGERBREAD_MR1","HONEYCOMB","HONEYCOMB_MR1","HONEYCOMB_MR2","ICE_CREAM_SANDWICH","ICE_CREAM_SANDWICH_MR1","JELLY_BEAN","JELLY_BEAN_MR1","JELLY_BEAN_MR2","KITKAT","KITKAT_WATCH","L","LOLLIPOP","LOLLIPOP_MR1","M","N","N_MR1")
val DIMEN_PATTERN="(?:(?<float>\\d+\\.\\d+)|(?<int>\\d+))(?<unit>(?:di?p)|(?:sp)|(?:px))?".toPattern()
//?android:attr/actionBarDivider   ?attr/actionBarDivider  ?actionBarDivider 三种引用方式
val RESOURCE_PATTERN="(@\\+?(android:)?(?<type>\\w+)/(?<ref>.+))|(\\?(?<android>android:)?(attr/)?(?<attr>\\w+))".toPattern()

/**
 * 转换尺寸
 */
fun View.dimen(value:String):String=dimenInner(value)
fun ViewGroup.LayoutParams.dimen(value:String):String=dimenInner(value)

/**
 * 转换string
 */
fun View.string(value:String):String=stringInner(value)
fun ViewGroup.LayoutParams.string(value:String):String=stringInner(value)


fun View.int(value:String):String=intInner(value)
fun ViewGroup.LayoutParams.int(value:String):String=intInner(value)

fun View.float(value:String):String=floatInner(value)
fun ViewGroup.LayoutParams.float(value:String):String=floatInner(value)


fun View.long(value:String):String=longInner(value)
fun ViewGroup.LayoutParams.long(value:String):String=longInner(value)

fun View.bool(value:String):String=boolInner(value)
fun ViewGroup.LayoutParams.bool(value:String):String=boolInner(value)

fun View.color(value:String):String= colorInner(value)
fun ViewGroup.LayoutParams.color(value:String):String=colorInner(value)

fun View.id(value:String):String=idInner(value)
fun ViewGroup.LayoutParams.id(value:String):String=idInner(value)

fun View.colorStateList(value:String):String=colorStateListInner(value)
fun ViewGroup.LayoutParams.colorStateList(value:String):String=colorStateListInner(value)

fun View.resource(value:String):String=resourceInner(value)
fun ViewGroup.LayoutParams.resource(value:String):String=resourceInner(value)

fun View.resourceRef(value:String):String=resourceRefInner(value)
fun ViewGroup.LayoutParams.resourceRef(value:String):String=resourceRefInner(value)

fun View.drawingCacheQuality(quality:String)=drawingCacheQualityInner(quality)
fun View.layerType(type:String)=layerTypeInner(type)
fun View.layoutDirection(direction:String)=layoutDirectionInner(direction)
fun View.scrollBarStyle(style:String)=scrollBarStyleInner(style)
fun View.textDirection(direction:String)=textDirectionInner(direction)
fun View.visibility(visible:String)=visibilityInner(visible)
fun View.textAlignment(alignment:String)=textAlignmentInner(alignment)
fun View.gravity(gravity:String)=gravityInner(gravity)
fun View.ellipsize(ellipsize:String)=ellipsizeInner(ellipsize)
fun View.layoutMode(layoutMode:String)=layoutModeInner(layoutMode)
fun ImageView.scaleType(gravity:String)= scaleTypeInner(gravity)

fun RelativeLayout.LayoutParams.relativeRule(gravity:String)= rule(gravity)
/**
 * LayoutParams的布局方位
 */
fun ViewGroup.LayoutParams.gravity(gravity:String)=gravityInner(gravity)
/**
 * LayoutParams的布局属性
 * 兼容 wrap_content/fill_parent or (数值sp/dp/dip) or 引用 @dimen/actionBarSize 以及?actionBarSize/?attrs/actionBarSize/?android:attrs/actionBarSize
 */
fun ViewGroup.LayoutParams.layoutDimension(value:String?)= layoutDimensionInner(value)

fun View.inputType(type:String)=inputTypeFlag(type)
fun View.bufferType(bufferType:String)= bufferTypeInner(bufferType)
fun View.typeface(typeface:String)= typefaceInner(typeface)
fun View.textStyle(textStyle:String)= textStyleInner(textStyle)
fun View.autoLink(autoLink:String)= autoLinkInner(autoLink)
fun View.numeric(numeric:String)= numericFlag(numeric)
fun View.imeOptions(imeOptions:String)=imeOptionsInner(imeOptions)
fun View.drawableTintMode(mode:String)=drawableTintModeInner(mode)
fun View.breakStrategy(strategy:String)=breakStrategyInner(strategy)
fun View.hyphenationFrequency(hyphenationFrequency:String)=hyphenationFrequencyInner(hyphenationFrequency)
fun View.persistentDrawingCache(persistentDrawingCache:String)=persistentDrawingCacheInner(persistentDrawingCache)
fun View.descendantFocusability(descendantFocusability:String)=descendantFocusabilityInner(descendantFocusability)



fun ViewStyle.dimen(value:String):String=dimenInner(value)
fun ViewStyle.string(value:String):String=stringInner(value)
fun ViewStyle.int(value:String):String=intInner(value)
fun ViewStyle.float(value:String):String=floatInner(value)
fun ViewStyle.long(value:String):String=longInner(value)
fun ViewStyle.bool(value:String):String=boolInner(value)
fun ViewStyle.color(value:String):String= colorInner(value)
fun ViewStyle.id(value:String):String=idInner(value)
fun ViewStyle.colorStateList(value:String):String=colorStateListInner(value)
fun ViewStyle.resource(value:String):String=resourceInner(value)
fun ViewStyle.resourceRef(value:String):String=resourceRefInner(value)
fun ViewStyle.drawingCacheQuality(quality:String)=drawingCacheQualityInner(quality)
fun ViewStyle.layerType(type:String)=layerTypeInner(type)
fun ViewStyle.layoutDirection(direction:String)=layoutDirectionInner(direction)
fun ViewStyle.scrollBarStyle(style:String)=scrollBarStyleInner(style)
fun ViewStyle.textDirection(direction:String)=textDirectionInner(direction)
fun ViewStyle.visibility(visible:String)=visibilityInner(visible)
fun ViewStyle.textAlignment(alignment:String)=textAlignmentInner(alignment)
fun ViewStyle.gravity(gravity:String)=gravityInner(gravity)
fun ViewStyle.ellipsize(ellipsize:String)=ellipsizeInner(ellipsize)
fun ViewStyle.layoutMode(layoutMode:String)=layoutModeInner(layoutMode)
fun ViewStyle.scaleType(gravity:String)= scaleTypeInner(gravity)
fun ViewStyle.relativeRule(gravity:String)= rule(gravity)
/**
 * LayoutParams的布局属性
 * 兼容 wrap_content/fill_parent or (数值sp/dp/dip) or 引用 @dimen/actionBarSize 以及?actionBarSize/?attrs/actionBarSize/?android:attrs/actionBarSize
 */
fun ViewStyle.layoutDimension(value:String?)= layoutDimensionInner(value)

fun ViewStyle.inputType(type:String)=inputTypeFlag(type)
fun ViewStyle.bufferType(bufferType:String)= bufferTypeInner(bufferType)
fun ViewStyle.typeface(typeface:String)= typefaceInner(typeface)
fun ViewStyle.textStyle(textStyle:String)= textStyleInner(textStyle)
fun ViewStyle.autoLink(autoLink:String)= autoLinkInner(autoLink)
fun ViewStyle.numeric(numeric:String)= numericFlag(numeric)
fun ViewStyle.imeOptions(imeOptions:String)=imeOptionsInner(imeOptions)
fun ViewStyle.drawableTintMode(mode:String)=drawableTintModeInner(mode)
fun ViewStyle.breakStrategy(strategy:String)=breakStrategyInner(strategy)
fun ViewStyle.hyphenationFrequency(hyphenationFrequency:String)=hyphenationFrequencyInner(hyphenationFrequency)
fun ViewStyle.persistentDrawingCache(persistentDrawingCache:String)=persistentDrawingCacheInner(persistentDrawingCache)
fun ViewStyle.descendantFocusability(descendantFocusability:String)=descendantFocusabilityInner(descendantFocusability)

private fun dimenInner(value:String):String{
    if(value.startsWith("@")){
        return resourceInner(value)
    } else {
        return dimensionValueInner(value)
    }
}

private fun stringInner(value:String):String{
    if(value.startsWith("@")){
        return resourceInner(value)
    } else {
        return value
    }
}


private fun layoutInner(value: String)=when(value){
    "match_parent"->"ViewGroup.LayoutParams.MATCH_PARENT"
    "fill_parent"->"ViewGroup.LayoutParams.FILL_PARENT"
    else->"ViewGroup.LayoutParams.WRAP_CONTENT"
}

private fun layoutDimensionInner(value:String?):String?{
    val value=value?:return null
    if(value.startsWith("@")){
        //引用
        return resourceInner(value)
    } else if(DIMEN_PATTERN.matcher(value).matches()){
        //数值
        return dimensionValueInner(value)
    } else {
        //布局数值
        return layoutInner(value)
    }
}

private fun dimensionValueInner(value: String): String {
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

private fun intInner(value:String):String{
    if(value.startsWith("@")){
        return resourceInner(value)
    } else {
        return dimenInner(value)
    }
}

private fun floatInner(value:String):String{
    if(value.startsWith("@")){
        return resourceInner(value)
    } else {
        return value+"f"
    }
}

private fun longInner(value:String):String{
    if(value.startsWith("@")){
        return resourceInner(value)
    } else {
        return value+"l"
    }
}

private fun boolInner(value:String):String{
    if(value.startsWith("@")){
        return resourceInner(value)
    } else {
        return value
    }
}

private fun colorInner(value:String):String{
    val matcher= RESOURCE_PATTERN.matcher(value)
    if(!matcher.find()) {
        return value
    } else {
        val type = matcher.group("type")
        val ref = matcher.group("ref")
        val attr = matcher.group("attr")
        if (null != attr) {
            //资源引用
            return "R.attr.$attr"
        } else if("drawable"==type){
            return "ContextCompat.getColorStateList(context,R.drawable.$ref)"
        } else {
            return "ContextCompat.getColor(context,R.color.$ref)"
        }
    }
}

private fun idInner(value:String)=resourceInner(value)

private fun colorStateListInner(value:String):String{
    var result=String()
    val matcher= RESOURCE_PATTERN.matcher(value)
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
private fun resourceInner(value:String):String{
    //资源引用
    var result=String()
    val matcher= RESOURCE_PATTERN.matcher(value)
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
                "interpolator"->result="AnimationUtils.loadInterpolator(context,R.interpolator.$ref)"
            }
        }
    }
    return result
}

/**
 * 资源引用
 */
private fun resourceRefInner(value:String):String{
    //资源引用
    var result=String()
    val matcher= RESOURCE_PATTERN.matcher(value)
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
                "color"->result="R.color.$ref"
                "drawable"->result="R.drawable.$ref"
                "mipmap"->result="R.mipmap.$ref"
                "string"->result="R.string.$ref"
                "array"->result="R.array.$ref"
                "theme"->result="R.style.$ref"
                "anim"->result="R.anim.$ref"
                "animator"->result="R.animator.$ref"
                "interpolator"->result="R.interpolator.$ref"
            }
        }
    }
    return result
}

private fun drawingCacheQualityInner(quality:String)=when(quality){
    "low"->"View.DRAWING_CACHE_QUALITY_LOW"
    "high"->"View.DRAWING_CACHE_QUALITY_HIGH"
    else ->"View.DRAWING_CACHE_QUALITY_AUTO"
}

/**
 * View.LAYER_TYPE_NONE
 * View.LAYER_TYPE_HARDWARE
 * View.LAYER_TYPE_SOFTWARE
 */
private fun layerTypeInner(type:String)=when(type){
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
private fun layoutDirectionInner(direction:String)=when(direction){
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
private fun scrollBarStyleInner(style:String)=when(style){
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
private fun textDirectionInner(direction:String)=when(direction){
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
private fun visibilityInner(visible:String)=when(visible){
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
private fun textAlignmentInner(alignment:String)=when(alignment){
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
private fun gravityInner(gravity:String):String{
    return gravity.split("|").map {
        when(gravity){
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
    }.joinToString(" or ")
}

/**
 * ImageView.ScaleType.CENTER
 * ImageView.ScaleType.CENTER_CROP
 * ImageView.ScaleType.CENTER_INSIDE
 * ImageView.ScaleType.FIT_CENTER
 * ImageView.ScaleType.FIT_END
 * ImageView.ScaleType.FIT_START
 * ImageView.ScaleType.FIT_XY
 * ImageView.ScaleType.MATRIX
 */
private fun scaleTypeInner(type:String)=when(type){
    "center"->"ImageView.ScaleType.CENTER"
    "centerCrop"->"ImageView.ScaleType.CENTER_CROP"
    "centerInside"->"ImageView.ScaleType.CENTER_INSIDE"
    "fitCenter"->"ImageView.ScaleType.FIT_CENTER"
    "fitEnd"->"ImageView.ScaleType.FIT_END"
    "fitStart"->"ImageView.ScaleType.FIT_START"
    "fitXY"->"ImageView.ScaleType.FIT_XY"
    else ->"ImageView.ScaleType.MATRIX"
}

/**
 * RelativeLayout.ALIGN_BASELINE
 * RelativeLayout.ALIGN_BOTTOM
 * RelativeLayout.ALIGN_LEFT
 * RelativeLayout.ALIGN_RIGHT
 * RelativeLayout.ALIGN_TOP
 * RelativeLayout.ALIGN_PARENT_BOTTOM
 * RelativeLayout.ALIGN_PARENT_END
 * RelativeLayout.ALIGN_PARENT_LEFT
 * RelativeLayout.ALIGN_PARENT_TOP
 * RelativeLayout.ALIGN_PARENT_START
 * RelativeLayout.ALIGN_PARENT_RIGHT
 * RelativeLayout.ALIGN_END
 * RelativeLayout.ALIGN_START
 * RelativeLayout.ABOVE
 * RelativeLayout.BELOW
 * RelativeLayout.LEFT_OF
 * RelativeLayout.RIGHT_OF
 * RelativeLayout.END_OF
 * RelativeLayout.START_OF
 * RelativeLayout.CENTER_HORIZONTAL
 * RelativeLayout.CENTER_VERTICAL
 * RelativeLayout.CENTER_IN_PARENT
 */
private fun rule(rule:String)=when(rule){
    "layout_alignBaseline"->"RelativeLayout.ALIGN_BASELINE"
    "layout_alignBottom"->"RelativeLayout.ALIGN_BOTTOM"
    "layout_alignRight"->"RelativeLayout.ALIGN_RIGHT"
    "layout_alignTop"->"RelativeLayout.ALIGN_TOP"
    "layout_alignEnd"->"RelativeLayout.ALIGN_END"
    "layout_alignStart"->"RelativeLayout.ALIGN_START"
    "layout_above"->"RelativeLayout.ABOVE"
    "layout_below"->"RelativeLayout.BELOW"
    "layout_leftOf"->"RelativeLayout.LEFT_OF"
    "layout_rightOf"->"RelativeLayout.RIGHT_OF"
    "layout_endOf"->"RelativeLayout.END_OF"
    "layout_startOf"->"RelativeLayout.START_OF"
    "layout_alignParentStart"->"RelativeLayout.ALIGN_PARENT_START"
    "layout_alignParentRight"->"RelativeLayout.ALIGN_PARENT_RIGHT"
    "layout_centerHorizontal"->"RelativeLayout.CENTER_HORIZONTAL"
    "layout_centerVertical"->"RelativeLayout.CENTER_VERTICAL"
    "layout_centerInParent"->"RelativeLayout.CENTER_IN_PARENT"
    "layout_alignParentBottom"->"RelativeLayout.ALIGN_PARENT_BOTTOM"
    "layout_alignParentEnd"->"RelativeLayout.ALIGN_PARENT_END"
    "layout_alignParentLeft"->"RelativeLayout.ALIGN_PARENT_LEFT"
    "layout_alignParentTop"->"RelativeLayout.ALIGN_PARENT_TOP"
    else->"RelativeLayout.ALIGN_LEFT"

}

/**
 * 输入文本类型位运算
 */
private fun inputTypeFlag(type:String)=type.split("|").map { inputTypeInner(it) }.joinToString(" or "){it}


/**
 * InputType.TYPE_NUMBER_FLAG_SIGNED
 * InputType.TYPE_NUMBER_FLAG_DECIMAL
 * InputType.TYPE_CLASS_NUMBER
 */
private fun numericFlag(numeric:String){
    numeric.split("|").map {
        when(it){
            "signed"->"InputType.TYPE_NUMBER_FLAG_SIGNED"
            "decimal"->"InputType.TYPE_NUMBER_FLAG_DECIMAL"
            else->"InputType.TYPE_CLASS_NUMBER"
        }
    }.joinToString(" or ")
}


/**
 * TypeFace
 * Typeface.DEFAULT
 * Typeface.DEFAULT_BOLD
 * Typeface.MONOSPACE
 * Typeface.SERIF
 * Typeface.SANS_SERIF
 */
private fun typefaceInner(typeface:String)=when(typeface){
    "sans"->"Typeface.SANS_SERIF"
    "serif"->"Typeface.SERIF"
    "monospace"->"Typeface.MONOSPACE"
    else->"Typeface.DEFAULT"
}


/**
 * BufferType.NORMAL
 * BufferType.SPANNABLE
 * BufferType.EDITABLE
 */
private fun bufferTypeInner(bufferType:String)=when(bufferType){
    "spannable"->"BufferType.SPANNABLE"
    "editable"->"BufferType.EDITABLE"
    else->"BufferType.NORMAL"
}


/**
 * Typeface.NORMAL
 * Typeface.BOLD
 * Typeface.BOLD_ITALIC
 * Typeface.ITALIC
 */
private fun textStyleInner(textStyle:String)=textStyle.split("|").map {
    when(it){
        "bold"->"Typeface.BOLD"
        "italic"->"Typeface.ITALIC"
        else ->"Typeface.NORMAL"
    }
}.joinToString(" or ")

/**
 * TextUtils.TruncateAt.START
 * TextUtils.TruncateAt.END
 * TextUtils.TruncateAt.MIDDLE
 * TextUtils.TruncateAt.MARQUEE
 */
private fun ellipsizeInner(ellipsize:String)=when(ellipsize){
    "start"->"TextUtils.TruncateAt.START"
    "middle"->"TextUtils.TruncateAt.MIDDLE"
    "marquee"->"TextUtils.TruncateAt.MARQUEE"
    else->"TextUtils.TruncateAt.END"
}


/**
 * Linkify.ALL
 * Linkify.EMAIL_ADDRESSES
 * Linkify.MAP_ADDRESSES
 * Linkify.PHONE_NUMBERS
 * Linkify.WEB_URLS
 */
private fun autoLinkInner(autoLink:String)=autoLink.split("|").map {
    when(it){
        "web"->"Linkify.WEB_URLS"
        "email"->"Linkify.EMAIL_ADDRESSES"
        "phone"->"Linkify.PHONE_NUMBERS"
        "map"->"Linkify.MAP_ADDRESSES"
        "all"->"Linkify.ALL"
        else ->"0"
    }
}.joinToString(" or ")



/**
 * InputType.TYPE_MASK_CLASS
 * InputType.TYPE_MASK_VARIATION
 * InputType.TYPE_MASK_FLAGS
 * InputType.TYPE_NULL
 * InputType.TYPE_CLASS_TEXT
 * InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
 * InputType.TYPE_TEXT_FLAG_CAP_WORDS
 * InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
 * InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
 * InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE
 * InputType.TYPE_TEXT_FLAG_MULTI_LINE
 * InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE
 * InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
 * InputType.TYPE_TEXT_VARIATION_NORMAL
 * InputType.TYPE_TEXT_VARIATION_URI
 * InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
 * InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT
 * InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE
 * InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE
 * InputType.TYPE_TEXT_VARIATION_PERSON_NAME
 * InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS
 * InputType.TYPE_TEXT_VARIATION_PASSWORD
 * InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
 * InputType.TYPE_TEXT_VARIATION_WEB_EDIT_TEXT
 * InputType.TYPE_TEXT_VARIATION_FILTER
 * InputType.TYPE_TEXT_VARIATION_PHONETIC
 * InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
 * InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
 * InputType.TYPE_CLASS_NUMBER
 * InputType.TYPE_NUMBER_FLAG_SIGNED
 * InputType.TYPE_NUMBER_FLAG_DECIMAL
 * InputType.TYPE_NUMBER_VARIATION_NORMAL
 * InputType.TYPE_NUMBER_VARIATION_PASSWORD
 * InputType.TYPE_CLASS_PHONE
 * InputType.TYPE_CLASS_DATETIME
 * InputType.TYPE_DATETIME_VARIATION_NORMAL
 * InputType.TYPE_DATETIME_VARIATION_DATE
 * InputType.TYPE_DATETIME_VARIATION_TIME
 */
private fun inputTypeInner(type:String)=when(type){
    "none"->"InputType.TYPE_NULL"
    "textCapCharacters"->"InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS"
    "textCapWords"->"InputType.TYPE_TEXT_FLAG_CAP_WORDS"
    "textCapSentences"->"InputType.TYPE_TEXT_FLAG_CAP_SENTENCES"
    "textAutoCorrect"->"InputType.TYPE_TEXT_FLAG_AUTO_CORRECT"
    "textAutoComplete"->"InputType.TYPE_TEXT_FLAG_AUTO_COMPLETE"
    "textMultiLine"->"InputType.TYPE_TEXT_FLAG_MULTI_LINE"
    "textImeMultiLine"->"InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE"
    "textNoSuggestions"->"InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS"
    "textUri"->"InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_URI"
    "textEmailAddress"->"InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS"
    "textEmailSubject"->"InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT"
    "textShortMessage"->"InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_SHORT_MESSAGE"
    "textLongMessage"->"InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE"
    "textPersonName"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_PERSON_NAME"
    "textPostalAddress"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_POSTAL_ADDRESS"
    "textPassword"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_PASSWORD"
    "textVisiblePassword"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_VISIBLE_PASSWORD"
    "textWebEditText"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_WEB_EDIT_TEXT"
    "textFilter"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_FILTER"
    "textPhonetic"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_PHONETIC"
    "textWebEmailAddress"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS"
    "textWebPassword"->"InputType#TYPE_CLASS_TEXT|InputType#TYPE_TEXT_VARIATION_WEB_PASSWORD"
    "number"->"InputType#TYPE_CLASS_NUMBER|InputType#TYPE_NUMBER_VARIATION_NORMAL"
    "numberSigned"->"InputType#TYPE_CLASS_NUMBER|InputType#TYPE_NUMBER_FLAG_SIGNED"
    "numberDecimal"->"InputType#TYPE_CLASS_NUMBER|InputType#TYPE_NUMBER_FLAG_DECIMAL"
    "numberPassword"->"InputType#TYPE_CLASS_NUMBER|InputType#TYPE_NUMBER_VARIATION_PASSWORD"
    "phone"->"InputType#TYPE_CLASS_PHONE"
    "datetime"->"InputType#TYPE_CLASS_DATETIME|InputType#TYPE_DATETIME_VARIATION_NORMAL"
    "date"->"InputType#TYPE_CLASS_DATETIME|InputType#TYPE_DATETIME_VARIATION_DATE"
    "time"->"InputType#TYPE_CLASS_DATETIME|InputType#TYPE_DATETIME_VARIATION_TIME"
    else->"InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL"
}


/**
 *EditorInfo.IME_NULL
 */
private fun imeOptionsInner(imeOptions:String):String{
    return imeOptions.split('|').map {
        //?
//        "flagNoPersonalizedLearning"->"EditorInfo.IME_FLAG_NO_ENTER_ACTION"
        when(it){
            "actionNone"->"EditorInfo.IME_ACTION_NONE"
            "actionGo"->"EditorInfo.IME_ACTION_GO"
            "actionSearch"->"EditorInfo.IME_ACTION_SEARCH"
            "actionSend"->"EditorInfo.IME_ACTION_SEND"
            "actionNext"->"EditorInfo.IME_ACTION_NEXT"
            "actionDone"->"EditorInfo.IME_ACTION_DONE"
            "actionPrevious"->"EditorInfo.IME_ACTION_PREVIOUS"
            "flagNoFullscreen"->"EditorInfo.IME_ACTION_NONE"
            "flagNavigateNext"->"EditorInfo.IME_FLAG_NAVIGATE_NEXT"

            "flagNoExtractUi"->"EditorInfo.IME_FLAG_NO_EXTRACT_UI"
            "flagNoAccessoryAction"->"EditorInfo.IME_FLAG_NO_ACCESSORY_ACTION"
            "flagNoEnterAction"->"EditorInfo.IME_FLAG_NO_ENTER_ACTION"

            "flagForceAscii"->"EditorInfo.IME_FLAG_FORCE_ASCII"
            else->"EditorInfo.IME_NULL"
        }
    }.joinToString(" or ")
}

/**
 * PorterDuff.Mode.ADD
 * PorterDuff.Mode.SRC_IN
 * PorterDuff.Mode.SRC_OVER
 * PorterDuff.Mode.SRC_ATOP
 * PorterDuff.Mode.MULTIPLY
 * PorterDuff.Mode.SCREEN
 */
private fun drawableTintModeInner(mode:String):String=when(mode){
    "src_in"->"PorterDuff.Mode.SRC_IN"
    "src_atop"->"PorterDuff.Mode.SRC_ATOP"
    "screen"->"PorterDuff.Mode.SCREEN"
    "multiply"->"PorterDuff.Mode.MULTIPLY"
    "add"->"PorterDuff.Mode.ADD"
    else->"PorterDuff.Mode.SRC_OVER"
}

/**
 * Layout.BREAK_STRATEGY_SIMPLE
 * Layout.BREAK_STRATEGY_HIGH_QUALITY
 * Layout.BREAK_STRATEGY_BALANCED
 */
private fun breakStrategyInner(strategy:String)=when(strategy){
    "high_quality"->"Layout.BREAK_STRATEGY_HIGH_QUALITY"
    "balanced"->"Layout.BREAK_STRATEGY_BALANCED"
    else->"Layout.BREAK_STRATEGY_SIMPLE"
}

/**
 * Layout.HYPHENATION_FREQUENCY_FULL
 * Layout.HYPHENATION_FREQUENCY_NONE
 * Layout.HYPHENATION_FREQUENCY_NORMAL
 */
private fun hyphenationFrequencyInner(hyphenationFrequency:String)=when(hyphenationFrequency){
    "normal"->"Layout.HYPHENATION_FREQUENCY_NORMAL"
    "full"->"Layout.HYPHENATION_FREQUENCY_FULL"
    else->"Layout.HYPHENATION_FREQUENCY_NONE"
}

/**
 * ViewGroup.PERSISTENT_NO_CACHE
 * ViewGroup.PERSISTENT_ANIMATION_CACHE
 * ViewGroup.PERSISTENT_SCROLLING_CACHE
 * ViewGroup.PERSISTENT_ALL_CACHES
 */
private fun persistentDrawingCacheInner(persistentDrawingCache:String)=when(persistentDrawingCache){
    "animation"->"ViewGroup.PERSISTENT_ANIMATION_CACHE"
    "scrolling"->"ViewGroup.PERSISTENT_SCROLLING_CACHE"
    "all"->"ViewGroup.PERSISTENT_ALL_CACHES"
    else->"ViewGroup.PERSISTENT_NO_CACHE"
}

/**
 * ViewGroup.FOCUS_BEFORE_DESCENDANTS
 * ViewGroup.FOCUS_AFTER_DESCENDANTS
 * ViewGroup.FOCUS_BLOCK_DESCENDANTS
 */
private fun descendantFocusabilityInner(descendantFocusability:String)=when(descendantFocusability){
    "afterDescendants"->"ViewGroup.FOCUS_AFTER_DESCENDANTS"
    "blocksDescendants"->"ViewGroup.FOCUS_BLOCK_DESCENDANTS"
    else->"ViewGroup.FOCUS_BEFORE_DESCENDANTS"
}

/**
 * ViewGroup.LAYOUT_MODE_CLIP_BOUNDS
 * ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS
 */
private fun layoutModeInner(layoutMode:String)=when(layoutMode){
    "opticalBounds"->"ViewGroup.LAYOUT_MODE_OPTICAL_BOUNDS"
    else->"ViewGroup.LAYOUT_MODE_CLIP_BOUNDS"
}