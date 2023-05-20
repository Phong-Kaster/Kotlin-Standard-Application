package com.example.kotlinstandardapplication.Navigationpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityNavigationBinding


/**
 * @since 23-04-2023
 * this activity uses navigation component + data binding + bottom navigation view + fragment container view
 */
class NavigationActivity : AppCompatActivity() {

    private lateinit var navigationBinding: ActivityNavigationBinding
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponent()
        setupEvent()
    }

    private fun setupComponent()
    {
        navigationBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)

        /*create navController*/
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }


    private fun setupEvent()
    {
        navigationBinding.bottomNavigationView.setupWithNavController(navController)
    }
}