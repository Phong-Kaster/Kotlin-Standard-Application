package com.example.kotlinstandardapplication.page.coroutinepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinstandardapplication.page.coroutinepage.database.UserEntity
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityCoroutineBinding

class CoroutineActivity : AppCompatActivity() {

    private val TAG = "CoroutineActivity"
    private lateinit var binding: ActivityCoroutineBinding
    private lateinit var viewModel: CoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_coroutine)*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)


        setupComponent()
    }

    fun setupComponent()
    {
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[CoroutineViewModel::class.java]

        Log.d(TAG, "before insert ")
        val record = UserEntity(0, "Phong", "Kaster")
        /*viewModel.insert(record)*/
        viewModel.hasSuccess().observe(this,  Observer {
            aBoolean->
            if( aBoolean == true)
            {
                Toast.makeText(this, "Action success !", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Action success !", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.findById(1)!!.observe(this, Observer { item->
            if( item == null) {
                Toast.makeText(this, "Item is NULL", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this, "Item name ${item.firstName} ${item.lastName}", Toast.LENGTH_SHORT).show()
            }
        })

    }
}