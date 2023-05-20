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
import com.example.kotlinstandardapplication.databinding.FragmentNavigation3Binding


class NavigationFragment3 : Fragment() {

    lateinit var binding: FragmentNavigation3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        /*return inflater.inflate(R.layout.fragment_navigation3, container, false)*/

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_navigation3, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvent()
    }

    private fun setupEvent()
    {
        binding.gotoFragment4.setOnClickListener {
            val destination: NavDirections = NavigationFragment3Directions.actionNavigationFragment3ToNavigationFragment4()
            findNavController().navigate(destination)
        }

        /*binding.gotoFragment5.setOnClickListener {
            val destination: NavDirections = NavigationFragment3Directions.actionNavigationFragment3ToNavigationFragment5()
            findNavController().navigate(destination)
        }*/
    }
}