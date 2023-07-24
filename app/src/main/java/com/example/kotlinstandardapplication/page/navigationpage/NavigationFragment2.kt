package com.example.kotlinstandardapplication.page.navigationpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.FragmentNavigation2Binding


class NavigationFragment2 : CoreFragment() {

    private lateinit var navigation2Binding: FragmentNavigation2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navigation2Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation2, container, false)
        return navigation2Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvent()
    }

    private fun setupEvent()
    {
        navigation2Binding.buttonChartBar.setOnClickListener{
            navController.navigate(R.id.action_chart_bar)
        }

        navigation2Binding.buttonChartPie.setOnClickListener{
            navController.navigate(R.id.action_chart_pie)
        }

        navigation2Binding.buttonChartRadar.setOnClickListener {
            navController.navigate(R.id.action_chart_radar)
        }
    }
}