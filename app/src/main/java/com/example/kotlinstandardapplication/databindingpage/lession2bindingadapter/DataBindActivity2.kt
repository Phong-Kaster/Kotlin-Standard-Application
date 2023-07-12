package com.example.kotlinstandardapplication.databindingpage.lession2bindingadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityDataBind2Binding

class DataBindActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityDataBind2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind_2)

        val image = ImageModel("Phong Kaster",
            "Android Programmer",
        "https://postimg.cc/0zRdqnpB")
    }
}