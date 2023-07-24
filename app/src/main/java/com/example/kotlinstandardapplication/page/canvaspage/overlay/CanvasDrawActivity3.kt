package com.example.kotlinstandardapplication.page.canvaspage.overlay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityCanvasDraw3Binding

class CanvasDrawActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityCanvasDraw3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas_draw3)
        setupComponent()
    }

    private fun setupComponent(){
        binding.customViewAlpha.addCustomViewBeta("abc")
    }
}