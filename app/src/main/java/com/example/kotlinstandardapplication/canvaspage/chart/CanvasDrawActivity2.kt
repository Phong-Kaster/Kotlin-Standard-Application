package com.example.kotlinstandardapplication.canvaspage.chart

import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.canvaspage.chart.linechart.Coordinate
import com.example.kotlinstandardapplication.canvaspage.chart.linechart.LineChart
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

    private fun setupComponent() {

        val coordinate = mutableListOf<Coordinate>(
            Coordinate(100f, "12:00", LineChart.colorBaseLine(100), R.drawable.ic_swastika),
            Coordinate(200f, "15:00", LineChart.colorBaseLine(200), 0),
            Coordinate(330f, "19:00", LineChart.colorBaseLine(330), 0),
            Coordinate(750f, "15:00", LineChart.colorBaseLine(750), 0),
            Coordinate(610f, "15:00", LineChart.colorBaseLine(610), 0),
            Coordinate(750f, "15:00", LineChart.colorBaseLine(750), 0),
            Coordinate(800f, "15:00", LineChart.colorBaseLine(800), 0),
            Coordinate(750f, "15:00", LineChart.colorBaseLine(750), 0),
            Coordinate(910f, "20:00", LineChart.colorBaseLine(910), 0),
            Coordinate(950f, "21:00", LineChart.colorBaseLine(950), R.drawable.ic_swastika),
            Coordinate(980f, "21:00", LineChart.colorBaseLine(980), R.drawable.ic_swastika),
            Coordinate(905f, "21:00", LineChart.colorBaseLine(905), R.drawable.ic_swastika),
        )

        binding.lineChartContainer.coordinates = coordinate
    }
}