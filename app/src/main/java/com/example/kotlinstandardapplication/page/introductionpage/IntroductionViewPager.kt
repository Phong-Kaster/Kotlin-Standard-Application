package com.example.kotlinstandardapplication.page.introductionpage

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.kotlinstandardapplication.R

class IntroductionViewPager
constructor(val context: Context, private val list: List<Introduction>): PagerAdapter() {



    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // declare layout
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layout = inflater.inflate(R.layout.fragment_introduction, null) as View

        // mapping components
        val photo = layout.findViewById(R.id.photo) as ImageView
        val title = layout.findViewById(R.id.title) as TextView
        val description = layout.findViewById(R.id.description) as TextView

        // get values
        val photoValue = list[position].photo
        val titleValue = list[position].title
        val descriptionValue = list[position].description

        // set values
        photo.setImageResource(photoValue)
        title.text = titleValue
        description.text = descriptionValue


        // return
        container.addView(layout)
        return layout
    }
}