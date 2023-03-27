package com.example.kotlinstandardapplication.Introductionpage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.Loginpage.LoginActivity
import com.example.kotlinstandardapplication.Multipurpose.Multipurpose
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityIntroductionBinding

class IntroductionActivity : AppCompatActivity() {

    /*use DataBinding*/
    private lateinit var introBinding: ActivityIntroductionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        introBinding = DataBindingUtil.setContentView(this, R.layout.activity_introduction)
        //setContentView(R.layout.activity_introduction)
        Multipurpose.hideStatusBar(window)


//        setupIntroductionSlide()
//        setupEvent()
    }

    /**
     * @author Phong-Kaster
     * @since 16-02-2023
     * setup introduction slide
     */
    private fun setupIntroductionSlide()
    {
        /*Step 1: prepare contents to display*/
        val list = arrayListOf<Introduction>()

        val introduction1 = Introduction(R.drawable.ic_introduction_1, getString(R.string.intro_title_1), getString(R.string.intro_description_1))
        val introduction2 = Introduction(R.drawable.ic_introduction_2, getString(R.string.intro_title_2), getString(R.string.intro_description_2))
        val introduction3 = Introduction(R.drawable.ic_logo, getString(R.string.intro_title_3), getString(R.string.intro_description_3))


        list.add(introduction1)
        list.add(introduction2)
        list.add(introduction3)

        val viewPagerAdapter = IntroductionViewPager(this, list)
        introBinding.viewPager.adapter = viewPagerAdapter

        /*Step 2: attach tabLayout with view pager*/
        introBinding.tabLayout.setupWithViewPager(introBinding.viewPager)
    }

    /**
     * @author Phong-Kaster
     * @since 16-02-2023
     * position is the current item that the indicator is standing
     */
    private var position = 0
    private fun setupEvent()
    {
        introBinding.button.setOnClickListener {
            position = introBinding.viewPager.currentItem
            if (position == introBinding.viewPager.size)
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else if (position < introBinding.viewPager.size)
            {
                position++
                introBinding.viewPager.currentItem = position
                introBinding.button.text = getString(R.string.next)
            }

        }
    }
}