package com.cz.layout2code.inflate.impl

import com.cz.layout2code.inflate.dimen
import com.cz.layout2code.inflate.expression.AttributeMethodMultiParamsExpression
import com.cz.layout2code.inflate.expression.value.CastIntFieldExpression
import com.cz.layout2code.inflate.expression.value.ClassFieldExpression
import com.cz.layout2code.inflate.expression.value.IntFieldExpression
import com.cz.layout2code.inflate.expression.value.StringValueExpression
import com.cz.layout2code.inflate.item.ViewNode
import com.cz.layout2code.inflate.prefs.AttrType
import com.cz.layout2code.inflate.resource

/**
 * Created by cz on 2018/3/19.
 * 
 * ---------------ExpandableListView all expressions---------------
 * @attr ref android.R.styleable#ExpandableListView_groupIndicator
 * @attr ref android.R.styleable#ExpandableListView_indicatorLeft
 * @attr ref android.R.styleable#ExpandableListView_indicatorRight
 * @attr ref android.R.styleable#ExpandableListView_childIndicator
 * @attr ref android.R.styleable#ExpandableListView_childIndicatorLeft
 * @attr ref android.R.styleable#ExpandableListView_childIndicatorRight
 * @attr ref android.R.styleable#ExpandableListView_childDivider
 * @attr ref android.R.styleable#ExpandableListView_indicatorStart
 * @attr ref android.R.styleable#ExpandableListView_indicatorEnd
 * @attr ref android.R.styleable#ExpandableListView_childIndicatorStart
 * @attr ref android.R.styleable#ExpandableListView_childIndicatorEnd
 *
 */
open class ExpandableListView : ListView() {
	init {
		attribute{
			field = "groupIndicator"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setGroupIndicator") { resource(it) }
		}
		attribute{
			field = "childIndicator"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setChildIndicator") { resource(it) }
		}
		attribute{
			field = "childDivider"
			attrType = arrayOf(AttrType.REFERENCE)
			method("setChildDivider") { resource(it) }
		}
		uselessAttribute("indicatorStart")
		uselessAttribute("indicatorEnd")
	}

	override fun inflateAttributes(viewNode: ViewNode) {
		super.inflateAttributes(viewNode)
		val childIndicatorLeft = viewNode.attributes.find { it.name == "childIndicatorLeft" }
		var childIndicatorRight = viewNode.attributes.find { it.name == "childIndicatorRight" }
		if(null!=childIndicatorLeft||null!=childIndicatorRight){
			applyAttributes(childIndicatorLeft,childIndicatorRight)
			val expression= AttributeMethodMultiParamsExpression("setChildIndicatorBounds"){
				mutableListOf(dimenPadding(childIndicatorLeft?.value),
						dimenPadding(childIndicatorRight?.value))
			}
			expressions.add(expression.callback(String()))
		}

		val indicatorStart = viewNode.attributes.find { it.name == "indicatorStart" }
		var indicatorEnd = viewNode.attributes.find { it.name == "indicatorEnd" }
		if(null!=indicatorStart||null!=indicatorEnd){
			applyAttributes(indicatorStart,indicatorEnd)
			val expression= AttributeMethodMultiParamsExpression("setIndicatorBoundsRelative"){
				mutableListOf(dimenPadding(indicatorStart?.value),
						dimenPadding(indicatorEnd?.value))
			}
			expressions.add(expression.callback(String()))
		}

		val childIndicatorStart = viewNode.attributes.find { it.name == "childIndicatorStart" }
		var childIndicatorEnd = viewNode.attributes.find { it.name == "childIndicatorEnd" }
		if(null!=childIndicatorStart||null!=childIndicatorEnd){
			applyAttributes(childIndicatorStart,childIndicatorEnd)
			val expression= AttributeMethodMultiParamsExpression("setChildIndicatorBoundsRelative"){
				mutableListOf(dimenPadding(childIndicatorStart?.value),
						dimenPadding(childIndicatorEnd?.value))
			}
			expressions.add(expression.callback(String()))
		}
	}

	private inline fun dimenPadding(value:String?): CastIntFieldExpression {
		val expression=if(null==value) IntFieldExpression("0") else dimen(value)
		return CastIntFieldExpression(expression)
	}

}
