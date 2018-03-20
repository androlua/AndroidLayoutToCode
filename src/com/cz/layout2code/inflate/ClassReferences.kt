package com.cz.layout2code.inflate

import com.cz.layout2code.inflate.item.ClassItem

/**
 * 常用类引用
 */
object ClassReferences {
    private val references= mutableMapOf<String, ClassItem>()
    init {
        //其他操作类
        item{ "android.os.Build" }
        item{ "android.animation.LayoutTransition"}
        item{ "android.graphics.Typeface" }
        item{ "android.graphics.PorterDuff" }
        item{ "android.view.Gravity" }
        item{ "android.view.PointerIcon"}
        item{ "android.text.InputFilter"}
        item{ "android.view.inputmethod.EditorInfo"}
        item{ "android.text.TextUtils"}
        item{ "android.text.InputType"}
        item{ "android.text.util.Linkify"}
        item{ "android.text.Layout"}
        item{ "android.content.res.Resources" }
        item{ "android.support.v4.content.ContextCompat"}

        //所有系统控件
        item{ "android.view.TextureView" }
        item{ "android.view.ViewGroup" }
        item{ "android.view.GhostView" }
        item{ "android.view.NotificationHeaderView" }
        item{ "android.view.View" }
        item{ "android.view.ViewStub" }
        item{ "android.view.SurfaceView" }
        item{ "android.widget.ListView" }
        item{ "android.widget.DigitalClock" }
        item{ "android.widget.SeekBar" }
        item{ "android.widget.DatePickerCalendarDelegate" }
        item{ "android.widget.MultiAutoCompleteTextView" }
        item{ "android.widget.AnalogClock" }
        item{ "android.widget.SlidingDrawer" }
        item{ "android.widget.AdapterViewFlipper" }
        item{ "android.widget.LinearLayout" }
        item{ "android.widget.ScrollView" }
        item{ "android.widget.DropDownListView" }
        item{ "android.widget.CalendarViewLegacyDelegate" }
        item{ "android.widget.EditText" }
        item{ "android.widget.ExpandableListView" }
        item{ "android.widget.YearPickerView" }
        item{ "android.widget.RatingBar" }
        item{ "android.widget.AdapterView" }
        item{ "android.widget.ViewSwitcher" }
        item{ "android.widget.ActionMenuView" }
        item{ "android.widget.DialerFilter" }
        item{ "android.widget.AbsSpinner" }
        item{ "android.widget.ImageView" }
        item{ "android.widget.ViewAnimator" }
        item{ "android.widget.RadialTimePickerView" }
        item{ "android.widget.HorizontalScrollView" }
        item{ "android.widget.ZoomButton" }
        item{ "android.widget.MediaController" }
        item{ "android.widget.RelativeLayout" }
        item{ "android.widget.AdapterViewAnimator" }
        item{ "android.widget.TextClock" }
        item{ "android.widget.Button" }
        item{ "android.widget.AutoCompleteTextView" }
        item{ "android.widget.TwoLineListItem" }
        item{ "android.widget.CalendarView" }
        item{ "android.widget.ToggleButton" }
        item{ "android.widget.ZoomControls" }
        item{ "android.widget.TextView" }
        item{ "android.widget.CheckBox" }
        item{ "android.widget.RadioGroup" }
        item{ "android.widget.VideoView" }
        item{ "android.widget.Gallery" }
        item{ "android.widget.GridView" }
        item{ "android.widget.AbsSeekBar" }
        item{ "android.widget.QuickContactBadge" }
        item{ "android.widget.TableLayout" }
        item{ "android.widget.NumberPicker" }
        item{ "android.widget.Toolbar" }
        item{ "android.widget.ViewFlipper" }
        item{ "android.widget.Chronometer" }
        item{ "android.widget.TextInputTimePickerView" }
        item{ "android.widget.StackView" }
        item{ "android.widget.ImageSwitcher" }
        item{ "android.widget.CompoundButton" }
        item{ "android.widget.AbsListView" }
        item{ "android.widget.TabWidget" }
        item{ "android.widget.TabHost" }
        item{ "android.widget.SearchView" }
        item{ "android.widget.ActivityChooserView" }
        item{ "android.widget.TimePickerClockDelegate" }
        item{ "android.widget.Spinner" }
        item{ "android.widget.TimePicker" }
        item{ "android.widget.ImageButton" }
        item{ "android.widget.TimePickerSpinnerDelegate" }
        item{ "android.widget.TextSwitcher" }
        item{ "android.widget.DatePicker" }
        item{ "android.widget.RadioButton" }
        item{ "android.widget.CheckedTextView" }
        item{ "android.widget.FrameLayout" }
        item{ "android.widget.DateTimeView" }
        item{ "android.widget.Space" }
        item{ "android.widget.GridLayout" }
        item{ "android.widget.SimpleMonthView" }
        item{ "android.widget.Switch" }
        item{ "android.widget.ProgressBar" }
        item{ "android.widget.TableRow" }
        item{ "android.widget.AbsoluteLayout" }
    }

    private inline fun item(action:()->String){
        val referenceName=action()
        addClassItem(referenceName)
    }

    fun getClassItem(className:String)=references[className]

    fun addClassItem(referenceName:String){
        if(null!=referenceName){
            val classItem= ClassItem(referenceName)
            references[classItem.className] = classItem
        }
    }

}