package com.example.kotlinstandardapplication.canvaspage.chart.linechart

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.LayoutLineChartContainerBinding

/**
 * @since 01-07-2023
 * @JvmOverloads Instructs the Kotlin compiler to generate overloads for this function that substitute default parameter values.
 * If a method has N parameters and M of which have default values, M overloads are generated: the first one
 * takes N-1 parameters (all but the last one that takes a default value), the second takes N-2 parameters, and so on.
 *
 * @AttributeSet https://stackoverflow.com/questions/5316686/what-is-attributeset-and-how-can-i-use-it
 */
class LineChartContainer
@JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defaultStyleAttribute: Int = 0
) :
    FrameLayout(context, attributeSet, defaultStyleAttribute) {
    private var binding: LayoutLineChartContainerBinding

    var maxValue: Int = 1000
        set(value) {
            field = value
            /*binding.lineChart.maxValue = value*/
            binding.axisY.maxValue = value
        }

    var coordinates: MutableList<LineChartItem> = mutableListOf()
        set(value) {
            if (value.size == 1) {
                value.add(LineChartItem(0f, "", 0, 0))
                value.add(LineChartItem(0f, "", 0, 0))
            }
            field = value
            binding.lineChart.items = coordinates
            invalidate()
        }

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_line_chart_container,
            null,
            false
        )
        addView(binding.root)

        binding.lineChart.maxValue = maxValue
        binding.axisY.maxValue = maxValue

        val layoutParamsStart = binding.axisY.layoutParams
        layoutParamsStart?.width = CanvasUtils.dpToPx(80, context)
    }
}