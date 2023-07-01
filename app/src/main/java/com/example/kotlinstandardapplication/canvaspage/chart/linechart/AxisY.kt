package com.example.kotlinstandardapplication.canvaspage.chart.linechart

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.canvaspage.chart.linechart.CanvasUtils.Companion.dpToPx
import com.example.kotlinstandardapplication.canvaspage.chart.linechart.CanvasUtils.Companion.toPx

class AxisY
@JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defaultStyleAttribute: Int = 0):
    View(context, attributeSet, defaultStyleAttribute)
{
    private val TAG = "AxisY"
    var marginBottomChart: Float = 30.toPx
    var marginTopChart: Float = 0f

    var paddingBottomChart: Float = 50.toPx
    var paddingTopChart: Float = 10.toPx

    var jump: Int = 100
    private val paint: Paint = Paint()
    private val paintLine: Paint = Paint()

    var maxValue: Int = 1000
        set(value) {
            field = value
            invalidate()
        }


    private val length: Float
        get() = height - (paddingBottomChart + paddingTopChart + marginTopChart)

    init {
        this.paint.flags = Paint.ANTI_ALIAS_FLAG
        this.paint.isAntiAlias = true
        /*val typeface = Typeface.createFromAsset(getContext().assets, "fonts/montserrat_700.ttf")
        this.paint.typeface = typeface*/
        this.paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        this.paint.color = ContextCompat.getColor(context, R.color.colorYellow)

        this.paintLine.flags = Paint.ANTI_ALIAS_FLAG
        this.paintLine.isAntiAlias = true
        this.paintLine.style = Paint.Style.STROKE
        this.paintLine.color = Color.YELLOW
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        this.drawAxisY(canvas)
        this.drawBoundaryAxisY(canvas)

    }



    private fun drawAxisY(canvas: Canvas?) {
        var i = 0
        while (jump * i <= maxValue) {
            val content = (jump * i).toString()// context = 200
            val y: Float = this.height - (length * jump * i / maxValue)

            this.paint.measureText(content)
            this.paint.textSize = dpToPx(16, context).toFloat()

            val rect = Rect()
            //this.paint.getTextBounds(content, 0, content.length, rect)// return text's 4-coordinates like rectangle

            this.paint.color = ContextCompat.getColor(context, R.color.colorYellow)
            this.paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            canvas?.drawText(content, 70f, y - marginBottomChart + (rect.height() / 2).toFloat(), this.paint)
            ++i
        }

    }

    private fun drawBoundaryAxisY(canvas: Canvas?) {
        /*get boundary of max value because it has largest width*/
        val rect = Rect()
        val content = maxValue.toString()
        this.paint.getTextBounds(content, 0,
            content.length, rect)

        /*axis X: start X is the same as stop X*/
        val startX = rect.width().toPx - 20.toPx
        val stopX = startX

        /*axis Y: */
        val startY = rect.height() + 5.toPx
        val stopY = this.height - 30.toPx
        canvas?.drawLine(startX, startY, stopX, stopY, this.paintLine)
    }
}