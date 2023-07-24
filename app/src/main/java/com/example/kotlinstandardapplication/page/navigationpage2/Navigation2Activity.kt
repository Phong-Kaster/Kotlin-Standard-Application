package com.example.kotlinstandardapplication.page.navigationpage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityNavigation2Binding

class Navigation2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigation2Binding
    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_navigation_2)*/
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_2)
        setupComponent()
    }

    private fun setupComponent()
    {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }
}