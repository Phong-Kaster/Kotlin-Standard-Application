package com.example.kotlinstandardapplication.canvaspage.basic

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityCanvasDrawBinding

/**
 * @author Phong-Kaster
 * @since 29-06-2023
 * draw basic shapes
 */
class CanvasDrawActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCanvasDrawBinding
    private lateinit var bitmap: Bitmap/*blackboard or container*/
    private lateinit var canvas: Canvas/*what stores all shapes or drawables*/
    private lateinit var paint: Paint/*pen*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_canvas_draw)*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas_draw)

        createBitmap()
        drawShape()
        drawCircle()
        drawLine()
        /*drawArc()*/
        drawPoint()
        drawText()
    }

    private fun createBitmap(){
        /*Step 1: initialize a bitmap with some width and height*/
        bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)

        /*assign it to a Canvas*/
        canvas = Canvas(bitmap)
        canvas.drawARGB(255, 78, 168, 186)
    }

    private fun drawShape(){
        /*Then we draw a Rectangle and a Oval to the Canvas object.*/
        var shapeDrawable: ShapeDrawable?


        /*draw rectangle shape to canvas*/
        shapeDrawable = ShapeDrawable(RectShape())

        /*bounds are coordinate*/
        shapeDrawable.setBounds( 0, 0, 50, 90)
        shapeDrawable.paint.color = Color.parseColor("#009944")
        shapeDrawable.draw(canvas)

        /*draw oval shape to canvas*/
        shapeDrawable = ShapeDrawable(OvalShape())
        shapeDrawable.setBounds( 0, 100, 300, 200)
        shapeDrawable.paint.color = Color.parseColor("#009191")
        shapeDrawable.draw(canvas)

        /*Now the Bitmap object holds the pixels with rectangle and oval drawn.*/


        /*assign the bitmap as a background of ImageView mentioned in the layout xml file.*/
        binding.image.background = BitmapDrawable(resources, bitmap)
    }

    private fun drawCircle(){
        /*configure the pen*/
        paint = Paint()
        paint.color = Color.parseColor("#FFFFFF")
        paint.strokeWidth = 25F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        paint.isDither = true


        // get device dimensions
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        // circle center
        println("Width : "+displayMetrics.widthPixels)
        /*var center_x = (displayMetrics.widthPixels/2).toFloat()
        var center_y = (displayMetrics.heightPixels/2).toFloat()*/

        var center_x = 400f
        var center_y = 600f
        var radius = 100F

        // draw circle
        canvas.drawCircle(center_x, center_y, radius, paint)
        // now bitmap holds the updated pixels

        /*assign the bitmap as a background of ImageView mentioned in the layout xml file.*/
        binding.image.background = BitmapDrawable(resources, bitmap)
    }

    private fun drawLine(){
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawLine(450F, 100F, 450F, 400F, paint)

        paint.strokeCap = Paint.Cap.BUTT
        canvas.drawLine(550F, 100F, 550F, 400F, paint)

        paint.strokeCap = Paint.Cap.SQUARE
        canvas.drawLine(650F, 100F, 650F, 400F, paint)
    }

    private fun drawArc(){
        //Draw Arc
        val width = 200f
        val height = 200f

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val left = (400 - width) / 2.0f
        val top = (400 - height) / 2.0f
        canvas.drawArc(RectF(left, top, left + width, top + height), 45f, 270f, true, paint)
    }

    private fun drawPoint(){
        paint.color = resources.getColor(R.color.purple_700, null)
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawPoint(600F, 600F, paint)
    }

    private fun drawText(){
        /*draw text*/
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        /*canvas.drawPaint(paint)*/

        paint.color = Color.BLACK
        paint.textSize = 20f
        canvas.drawText("Some Text", 10f, 25f, paint)
    }
}