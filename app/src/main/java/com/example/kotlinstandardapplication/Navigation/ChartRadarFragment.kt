package com.example.kotlinstandardapplication.Navigation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.FragmentChartRadarBinding
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class ChartRadarFragment : CoreFragment() {


    private lateinit var binding: FragmentChartRadarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chart_radar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart()
    }

    private fun setupChart()
    {
        /*initialize radar entries*/
        val list = arrayListOf<RadarEntry>()
        val entry1 = RadarEntry(100f)
        val entry2 = RadarEntry(101f)
        val entry3 = RadarEntry(102f)
        val entry4 = RadarEntry(103f)
        val entry5 = RadarEntry(104f)
        val entry6 = RadarEntry(105f)

        list.add(entry1)
        list.add(entry2)
        list.add(entry3)
        list.add(entry4)
        list.add(entry5)
        list.add(entry6)



        /*data set*/
        val dataSet = RadarDataSet(list, "Phong's Radar Chart")
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
        dataSet.lineWidth = 2f
        dataSet.valueTextSize = 14f
        dataSet.color = Color.WHITE
        dataSet.fillColor = Color.YELLOW
        dataSet.valueTextColor = Color.WHITE
        dataSet.setDrawFilled(true)


        /*start drawing*/
        val data = RadarData()
        data.addDataSet(dataSet)
        binding.chartRadar.setBackgroundColor( Color.rgb(60, 65, 82))
        binding.chartRadar.data = data
        binding.chartRadar.description.text = "Radar Chart"
        binding.chartRadar.animateXY(1000, 1000)

        /*binding.chartRadar.webColor = Color.LTGRAY
        binding.chartRadar.webColorInner = Color.LTGRAY*/
        binding.chartRadar.xAxis.textColor = Color.WHITE

    }

}


