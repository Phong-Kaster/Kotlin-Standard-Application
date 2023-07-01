package com.example.kotlinstandardapplication.canvaspage.chart.linechart

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.compose.ui.graphics.PaintingStyle
import androidx.core.content.ContextCompat
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.canvaspage.chart.linechart.CanvasUtils.Companion.dpToPx
import com.example.kotlinstandardapplication.canvaspage.chart.linechart.CanvasUtils.Companion.toPx

class LineChart
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs)
{
    private val TAG = "LineChart"
    var marginBottomChart: Float = 30.toPx
    var marginTopChart: Float = 0f

    var paddingTopChart: Float = 10.toPx
    var paddingBottomChart: Float = 50.toPx

    var paddingLeftChart: Float = 25.toPx
    var paddingRightChart: Float = 0f

    var color: Int = ContextCompat.getColor(context, R.color.colorYellow)

    var increment: Int = 100/*jump*/
    var maxValue: Int = 1000

    var spaceItem: Float = 45.toPx/*space between 2 items*/
    var unit: String = "mg/dL"
        set(value) {
            field = value
            invalidate()
        }

    var coordinates: List<Coordinate> = mutableListOf()
        set(value) {
            field = value
            invalidate()
        }

    var isShowBaseLine: Boolean = true
        set(value) {
            field = value
            invalidate()
        }

    private val length: Float
        get() = height - (paddingBottomChart + paddingTopChart + marginTopChart)

    private val paintBaseline = Paint()/*to draw baseline*/
    private val paintAxisX = Paint()/*to draw axis X*/
    private val paintTextAxisX = Paint()/*to draw text on axis X*/
    private val paintItem = Paint()/*to draw item*/

    /*this block code below runs immediately after this class created*/
    init {
        /*to draw baseline*/
        paintBaseline.flags = Paint.ANTI_ALIAS_FLAG
        paintBaseline.isAntiAlias = true
        paintBaseline.isFilterBitmap = true
        paintBaseline.color = ContextCompat.getColor(context, R.color.colorYellow)
        paintBaseline.strokeWidth = dpToPx(1, context).toFloat()
        paintBaseline.style = Paint.Style.STROKE
        paintBaseline.pathEffect = DashPathEffect(floatArrayOf(10.0f, 5.0f), 0.0f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBaseline(canvas)
        drawAxisX(canvas)
        Log.d(TAG, "onDraw: ")
    }

    private fun drawBaseline(canvas: Canvas?){
        /*if (!isShowBaseLine) return*/
        var i = 1
        while (increment * i <= maxValue) {
            /*set coordinate axis Y*/
            val y: Float = height - (length * increment * i / maxValue)
            val content = maxValue.toString()
            val rect = Rect()
            this.paintBaseline.getTextBounds(content, 0, content.length, rect)



            /*set color*/
            val input = increment*i
            this.paintBaseline.color = ContextCompat.getColor(context, colorBaseLine(input))

            /*begin draw*/
            val startX = rect.width() + 63.toPx
            val startY = y - marginBottomChart
            val stopX = this.width.toPx
            val stopY = y - marginBottomChart
            canvas?.drawLine(startX, startY, stopX, stopY, this.paintBaseline)
            ++i
        }
    }

    private fun drawAxisX(canvas: Canvas?){
        val content = maxValue.toString()
        val rect = Rect()
        this.paintBaseline.getTextBounds(content, 0, content.length, rect)

        val startX = rect.width() + 62.toPx
        val startY = this.height - marginBottomChart
        val stopX = this.width.toPx
        val stopY = this.height - marginBottomChart
        canvas?.drawLine(startX, startY, stopX, stopY, this.paintBaseline)
    }

    private fun colorBaseLine(input: Int): Int {
        return if (input > 800) R.color.colorRed
        else if( input in 400..800) R.color.colorYellow
        else R.color.black
    }
}