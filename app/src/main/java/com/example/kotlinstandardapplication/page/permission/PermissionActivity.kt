package com.example.kotlinstandardapplication.page.permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityPermissionBinding
import java.util.jar.Manifest

class PermissionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setupComponent()
        setupEvent()
    }

    private fun setupComponent(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_permission)
    }


    private val permission = ArrayList<String>(

    )

    private fun setupEvent(){
        binding.requestOnePermission.setOnClickListener {
            val flag = PermissionManager.isPermissionAccessed(this, android.Manifest.permission.CAMERA)
            if(flag){
                Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Not Granted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}