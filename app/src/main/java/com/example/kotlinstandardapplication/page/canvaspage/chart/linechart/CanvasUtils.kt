package com.example.kotlinstandardapplication.page.canvaspage.chart.linechart

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

class CanvasUtils {
    companion object{
        val Number.toPx
            get() = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics
            )

        fun dpToPx(dp: Int = 16, context: Context): Int = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics
        ).toInt()
    }
}