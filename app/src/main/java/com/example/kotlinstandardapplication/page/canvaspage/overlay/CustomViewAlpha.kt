package com.example.kotlinstandardapplication.page.canvaspage.overlay

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout


class CustomViewAlpha
@JvmOverloads
constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defaultStyleAttribute: Int = 0
): LinearLayout(context, attributeSet, defaultStyleAttribute) {

    fun addCustomViewBeta(someString: String?) {
        val customViewBeta = CustomViewBeta(context)
        addView(customViewBeta)
    }
}