package com.example.kotlinstandardapplication.Navigationpage2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.FragmentNavigation4Binding


class NavigationFragment4 : Fragment() {


    private lateinit var binding: FragmentNavigation4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_navigation4, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gotoFragment5.setOnClickListener {
            val destination: NavDirections = NavigationFragment4Directions.actionNavigationFragment4ToNavigationFragment5()
            findNavController().navigate(destination)
        }
    }
}