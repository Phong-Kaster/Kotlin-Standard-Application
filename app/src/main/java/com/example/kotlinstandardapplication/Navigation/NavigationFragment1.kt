package com.example.kotlinstandardapplication.Navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.Adapter.MenuAdapter
import com.example.kotlinstandardapplication.Model.Menu
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.FragmentNavigation1Binding

class NavigationFragment1 : Fragment() {

    private val TAG = "Navigation Activity"
    private lateinit var binding: FragmentNavigation1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_navigation1, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupComponent()
    }

    private fun setupComponent() {
        val list = arrayListOf<Menu>()
        val menu1 = Menu(R.drawable.ic_swastika, "Swastika")
        val menu2 = Menu(R.drawable.ic_balkenkreuz, "Balkenkreuz")
        val menu3 = Menu(R.drawable.ic_wehrmacht_sadler, "Wehrmacht Sadler")

        list.add(menu1)
        list.add(menu2)
        list.add(menu3)

        Log.d(TAG, "setupComponent: ${list.size}")
        val spinnerAdapter = MenuAdapter(requireContext(), list)
        binding.spinnerMenu.adapter = spinnerAdapter
    }
}