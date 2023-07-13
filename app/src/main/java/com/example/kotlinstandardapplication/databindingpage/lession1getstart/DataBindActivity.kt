package com.example.kotlinstandardapplication.databindingpage.lession1getstart

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityDataBindBinding

class DataBindActivity : AppCompatActivity(), DataBindViewModel.Callback {

    private lateinit var binding: ActivityDataBindBinding
    private lateinit var viewModel: DataBindViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setupComponent()
        setupEvent()
        setupViewModel()
    }

    private fun setupComponent() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind)
        binding.user1 = User(null, null)
        binding.user2 = User("Kaster", 2000)
    }


    private fun setupViewModel() {
        viewModel = DataBindViewModel(this)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this@DataBindActivity
    }

    private fun setupEvent() {
        /*There objects are bound to the UI basically*/
        binding.buttonChange.setOnClickListener {
            binding.user1 = User("Phong Kaster", 2000)
            binding.user2 = User(null, null)
        }

        /*There objects are bound to the UI with observable*/
        binding.buttonObservable.setOnClickListener {
            viewModel.changeIdentity()
            viewModel.changeText()
        }
    }

    override fun showToast() {
        Toast.makeText(this, "User is updated successfully !", Toast.LENGTH_SHORT).show()
    }
}