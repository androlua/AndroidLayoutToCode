package com.cz.layout2anko.inflate.prefs

/**
 * Created by cz on 2017/12/25.
 */
class ViewConfiguration{

    companion object{
        inline fun declareStyleable(items:MutableList<ViewStyle>,action:StyleItem.()->Unit){
            StyleItem(items).apply(action)
        }
    }

    class StyleItem(val items:MutableList<ViewStyle>){

        inline fun item(action:ViewStyle.()->Unit){
            items.add(ViewStyle().apply(action))
        }
    }
}