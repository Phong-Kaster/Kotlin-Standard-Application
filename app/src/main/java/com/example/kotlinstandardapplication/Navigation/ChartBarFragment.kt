package com.example.kotlinstandardapplication.Navigation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.FragmentChartBarBinding
import com.example.kotlinstandardapplication.databinding.FragmentChartPieBinding
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ChartBarFragment : CoreFragment() {

    private lateinit var binding: FragmentChartBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chart_bar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
    }

    private fun setupChart()
    {
        /*initialize bar entries*/
        val list = arrayListOf<BarEntry>()
        val entry1 = BarEntry(100f, 100f)
        val entry2 = BarEntry(101f, 101f)
        val entry3 = BarEntry(102f, 102f)
        val entry4 = BarEntry(103f, 103f)
        val entry5 = BarEntry(104f, 104f)
        val entry6 = BarEntry(105f, 105f)

        list.add(entry1)
        list.add(entry2)
        list.add(entry3)
        list.add(entry4)
        list.add(entry5)
        list.add(entry6)

        /*data set*/
        val dataSet = BarDataSet(list, "list")
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        dataSet.valueTextColor = Color.WHITE


        /*start drawing chart*/
        val bar = BarData(dataSet)
        binding.chartBar.setFitBars(true)
        binding.chartBar.data = bar
        binding.chartBar.description.text = "This is Chart Bar"
        binding.chartBar.animateXY(1000, 1000)
    }
}