package com.example.kotlinstandardapplication.canvaspage.chart.linechart

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import android.util.AttributeSet
import android.view.View
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

    var paddingLeftChart: Float = 12.toPx
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
    private val paintItemText = Paint()

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

        /*to draw number on item*/
        paintItemText.flags = Paint.ANTI_ALIAS_FLAG
        paintItemText.isAntiAlias = true
        paintItemText.color = -16777216
        paintItemText.textSize = dpToPx(12, context).toFloat()
        paintItemText.color = ContextCompat.getColor(context, R.color.white)
        /*val typeface = Typeface.createFromAsset(context.assets, "fonts/montserrat_500.ttf")
        paintMarkText.typeface = typeface*/
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var newWidth = if (coordinates.size > 1) {
            ((coordinates.size) * spaceItem + paddingLeftChart * 2).toInt()
        } else {
            0
        }
        if (widthMeasureSpec > newWidth) {
            newWidth = widthMeasureSpec
        }
        super.onMeasure(newWidth, heightMeasureSpec)
        layoutParams?.width = newWidth
    }



    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBaseline(canvas)
        drawAxisX(canvas)
        drawItem(canvas)
        drawItemMark(canvas)
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
            val startX = 0F
            val startY = y
            val stopX = this.width.toPx
            val stopY = y
            canvas?.drawLine(startX, startY, stopX, stopY, this.paintBaseline)
            ++i
        }
    }

    private fun drawAxisX(canvas: Canvas?){
        val content = maxValue.toString()
        val rect = Rect()
        this.paintBaseline.getTextBounds(content, 0, content.length, rect)

        val startX = 0F
        val startY = this.height - marginBottomChart
        val stopX = this.width.toPx
        val stopY = this.height - marginBottomChart
        canvas?.drawLine(startX, startY, stopX, stopY, this.paintBaseline)
    }

    private fun drawItem(canvas: Canvas?){

        val x: FloatArray = this.setAxisX()
        val y: FloatArray = this.setAxisY()

        coordinates.forEachIndexed { index, coordinate ->
            val pointY: Float = this.length * (coordinate.value / maxValue)
            val (value, timeStr, colorId, icon) = coordinate
            if ("" != timeStr) {
                /*draw circle*/
                paintItem.color = ContextCompat.getColor(context, colorId)
                canvas?.drawCircle(paddingLeftChart + spaceItem * index, height - pointY - marginBottomChart, dpToPx(4, context).toFloat(), this.paintItem)
                drawTextValue(canvas, value.toString(), paddingLeftChart + spaceItem * index, height - y[index] - 30.0f - marginBottomChart)

                /*draw icon*/
               if (icon != 0) {
                    val iconBitmap = getIconBitmap(context, icon)
                    if (iconBitmap != null) {
                        canvas?.drawBitmap(iconBitmap, paddingLeftChart + spaceItem * index - (12.toPx / 2), height - y[index] + 5.toPx - marginBottomChart, null as Paint?)
                    }
                }
            }
        }
    }

    private fun drawItemMark(canvas: Canvas?){
        val x: FloatArray = this.setAxisX()
        val y: FloatArray = this.setAxisY()

        coordinates.forEachIndexed { index, coordinate ->
            val pointY: Float = this.length * (coordinate.value / maxValue)
            val (value, timeStr, colorId, icon) = coordinate
            if ("" != timeStr) {
                /*draw circle*/
                paintItem.color = ContextCompat.getColor(context, R.color.colorYellow)
                /*canvas?.drawCircle(paddingLeftChart + spaceItem * index, height - pointY - marginBottomChart, dpToPx(4, context).toFloat(), this.paintItem)
                drawTextValue(canvas, value.toString(), paddingLeftChart + spaceItem * index, height - y[index] - 30.0f - marginBottomChart)*/

                canvas?.drawLine(
                    paddingLeftChart + spaceItem * index,
                    height - marginBottomChart,
                    paddingLeftChart + spaceItem * index,
                    height - marginBottomChart + 10.toPx,
                    paintItem)

                canvas?.drawText(timeStr, paddingLeftChart + spaceItem * index - 10.toPx, height - marginBottomChart + 20.toPx, paintItemText)
            }
        }
    }

    private fun drawTextValue(canvas: Canvas?, value: String, x: Float, y: Float) {
        this.paintItemText.measureText(value)
        val rect = Rect()
        this.paintItemText.getTextBounds(value, 0, value.length, rect)
        canvas?.drawText(value, x - (rect.width() / 2).toFloat(), y - rect.height().toFloat() / 4, this.paintItemText)
    }

    private fun setAxisX(): FloatArray {
        val axisX = FloatArray(coordinates.size)
        for (i in coordinates.indices) {
            axisX[i] = spaceItem * i.toFloat()
        }
        return axisX
    }

    private fun setAxisY(): FloatArray {
        val axisY = FloatArray(coordinates.size)
        for (i in coordinates.indices) {
            axisY[i] = length * (coordinates[i].value / maxValue)
        }
        return axisY
    }

    /*return bitmap*/
    private fun getIconBitmap(context: Context, drawableId: Int): Bitmap? {
        return when (val drawable = ContextCompat.getDrawable(context, drawableId)) {
            is BitmapDrawable -> {
                BitmapFactory.decodeResource(context.resources, drawableId)
            }

            is VectorDrawable -> {
                getBitmap(drawable)
            }

            else -> {
                throw IllegalArgumentException("unsupported drawable type")
            }
        }
    }

    /*return a bitmap from resource/drawable*/
    private fun getBitmap(vectorDrawable: VectorDrawable): Bitmap? {
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }


    companion object{
        fun colorBaseLine(input: Int): Int {
            return if (input > 800) R.color.colorRed
            else if( input in 400..800) R.color.colorYellow
            else R.color.colorBlue
        }
    }
}