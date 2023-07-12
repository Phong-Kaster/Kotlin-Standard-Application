package com.example.kotlinstandardapplication.databindingpage.lession2bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/*
class BindingAdapter {
}*/

@BindingAdapter("loadImageFromURL")
fun ImageView.loadImageFromURL(url :Int){
    this.load(url)/*function from Coil*/
}
