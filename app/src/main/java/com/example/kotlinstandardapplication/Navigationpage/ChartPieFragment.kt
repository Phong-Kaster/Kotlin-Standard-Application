package com.example.kotlinstandardapplication.Navigationpage

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.FragmentChartPieBinding
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate


class ChartPieFragment : CoreFragment() {

    private lateinit var binding: FragmentChartPieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chart_pie, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
    }

    private fun setupChart()
    {
        /*initialize pie entries*/
        val list = arrayListOf<PieEntry>()
        val entry1 = PieEntry(100f, 100f)
        val entry2 = PieEntry(101f, 101f)
        val entry3 = PieEntry(102f, 102f)
        val entry4 = PieEntry(103f, 103f)
        val entry5 = PieEntry(104f, 104f)
        val entry6 = PieEntry(105f, 105f)

        list.add(entry1)
        list.add(entry2)
        list.add(entry3)
        list.add(entry4)
        list.add(entry5)
        list.add(entry6)

        /*data set*/
        val dataSet = PieDataSet(list, "list")
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 11f


        /*start drawing chart*/
        val data = PieData(dataSet)
        binding.chartPie.data = data
        binding.chartPie.description.text = "Chart Pie !"
        binding.chartPie.centerText = "Yolo"
        binding.chartPie.animateXY(3000,3000)
    }
}