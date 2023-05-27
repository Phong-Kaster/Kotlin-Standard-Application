package com.example.kotlinstandardapplication.databindingpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityDataBindBinding

class DataBindActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataBindBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_data_bind)*/

        setupComponent()
        setupEvent()

    }

    private fun setupComponent() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind)
        binding.user1 = User(null, null)
        binding.user2 = User("Kaster", 2000)
    }

    private fun setupEvent(){
        binding.buttonChange.setOnClickListener {
            binding.user1 = User("Phong Kaster", 2000)
            binding.user2 = User(null, null)
        }
    }
}