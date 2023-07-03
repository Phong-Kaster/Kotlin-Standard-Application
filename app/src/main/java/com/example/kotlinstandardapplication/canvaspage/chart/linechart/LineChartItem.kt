package com.example.kotlinstandardapplication.canvaspage.chart.linechart

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class LineChartItem(val value: Float,
                         val time: String,
                         @ColorRes val colorId: Int,
                         @DrawableRes val icon: Int)