package com.example.kotlinstandardapplication.page.canvaspage.overlay

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.kotlinstandardapplication.R

@SuppressLint("InflateParams")
class CustomViewBeta
@JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyleAttribute: Int = 0
): RelativeLayout(context, attributeSet, defaultStyleAttribute)
{
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_view_beta, null, true);
        val textView = findViewById<TextView>(R.id.customviewbeta_textview);
        textView.setText("ASDASDASDAD");
    }
}