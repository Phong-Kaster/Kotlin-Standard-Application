package com.example.kotlinstandardapplication.canvaspage.chart.linechart

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.LayoutLineChartBinding

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
    private var binding: LayoutLineChartBinding

    var maxValue: Int = 500
        set(value) {
            field = value
            /*binding.lineChart.maxValue = value*/
            binding.axisY.maxValue = value
        }

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.layout_line_chart,
            null,
            false
        )
        addView(binding.root)

        /*binding.lineChart.maxValue = maxValue*/
    }
}