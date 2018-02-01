package com.cz.layout2code.inflate.impl
//------------------------------------------------------------
// 以下控件没有自定义属性,只需要默认复写即可
//------------------------------------------------------------
open class AbsSeekBar : ProgressBar() {
    override fun getViewName()="absoluteLayout"
    override fun getThemeViewName()="themedAbsoluteLayout"
}
open class Button : TextView() {
    override fun getViewName()="button"
    override fun getThemeViewName()="themedButton"
}
open class CheckBox : CompoundButton() {
    override fun getViewName()="checkBox"
    override fun getThemeViewName()="themedCheckBox"
}
open class AbsoluteLayout : ViewGroup() {
    override fun getViewName()="absoluteLayout"
    override fun getThemeViewName()="themedAbsoluteLayout"
    override fun getLayoutParams()=LayoutParams()
    open class LayoutParams : ViewGroup.LayoutParams()
}
open class ActionMenuView : LinearLayout() {
    override fun getViewName()="actionMenuView"
    override fun getThemeViewName()="themedActionMenuView"
    override fun getLayoutParams()=LayoutParams()
    open class LayoutParams : LinearLayout.LayoutParams()
}
open class DialerFilter : RelativeLayout() {
    override fun getViewName()="dialerFilter"
    override fun getThemeViewName()="themedDialerFilter"
}
open class DigitalClock : TextView() {
    override fun getViewName()="digitalClock"
    override fun getThemeViewName()="themedDigitalClock"
}
open class EditText : TextView() {
    override fun getViewName()="editText"
    override fun getThemeViewName()="themedEditText"
}
open class GhostView : View() {
    override fun getViewName()="ghostView"
    override fun getThemeViewName()="themedGhostView"
}
open class ImageButton : ImageView() {
    override fun getViewName()="imageButton"
    override fun getThemeViewName()="themedImageButton"
}
open class ImageSwitcher : ViewSwitcher() {
    override fun getViewName()="imageSwitcher"
    override fun getThemeViewName()="themedImageSwitcher"
}
open class MediaController : FrameLayout() {
    override fun getViewName()="mediaController"
    override fun getThemeViewName()="themedMediaController"
}
open class MultiAutoCompleteTextView : AutoCompleteTextView() {
    override fun getViewName()="multiAutoCompleteTextView"
    override fun getThemeViewName()="themedMultiAutoCompleteTextView"
}
open class NotificationHeaderView : ViewGroup() {
    override fun getViewName()="notificationHeaderView"
    override fun getThemeViewName()="themedNotificationHeaderView"
}
open class RadialTimePickerView : View() {
    override fun getViewName()="radialTimePickerView"
    override fun getThemeViewName()="themedRadialTimePickerView"
}
open class RadioButton : CompoundButton() {
    override fun getViewName()="radioButton"
    override fun getThemeViewName()="themedRadioButton"
}
open class SimpleMonthView : View() {
    override fun getViewName()="simpleMonthView"
    override fun getThemeViewName()="themedSimpleMonthView"
}
open class Space : View() {
    override fun getViewName()="space"
    override fun getThemeViewName()="themedSpace"
}
open class SurfaceView : View() {
    override fun getViewName()="surfaceView"
    override fun getThemeViewName()="themedSurfaceView"
}
open class TabHost : FrameLayout() {
    override fun getViewName()="tabHost"
    override fun getThemeViewName()="themedTabHost"
}
open class TextInputTimePickerView : RelativeLayout() {
    override fun getViewName()="textInputTimePickerView"
    override fun getThemeViewName()="themedTextInputTimePickerView"
}
open class TextSwitcher : ViewSwitcher() {
    override fun getViewName()="textSwitcher"
    override fun getThemeViewName()="themedTextSwitcher"
}
open class TextureView : View() {
    override fun getViewName()="textureView"
    override fun getThemeViewName()="themedTextureView"
}
open class VideoView : SurfaceView() {
    override fun getViewName()="videoView"
    override fun getThemeViewName()="themedVideoView"
}
open class ViewSwitcher : ViewAnimator() {
    override fun getViewName()="viewSwitcher"
    override fun getThemeViewName()="themedViewSwitcher"
}
open class ZoomButton : ImageButton() {
    override fun getViewName()="zoomButton"
    override fun getThemeViewName()="themedZoomButton"
}
open class ZoomControls : LinearLayout() {
    override fun getViewName()="zoomControls"
    override fun getThemeViewName()="themedZoomControls"
}
