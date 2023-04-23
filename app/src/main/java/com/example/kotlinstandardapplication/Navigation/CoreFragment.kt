package com.example.kotlinstandardapplication.Navigation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

open class CoreFragment : Fragment() {
    val navController by lazy { findNavController() }
}