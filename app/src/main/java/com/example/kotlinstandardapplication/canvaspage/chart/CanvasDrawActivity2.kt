package com.example.kotlinstandardapplication.canvaspage.chart

import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityCanvasDraw2Binding

/**
 * @author Phong-Kaster
 * @since 01-07-2023
 * draw chart
 */
class CanvasDrawActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityCanvasDraw2Binding
    private lateinit var canvas: Canvas
    private lateinit var paint: Paint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas_draw_2)
        setupComponent()
    }

    private fun setupComponent(){

    }
}