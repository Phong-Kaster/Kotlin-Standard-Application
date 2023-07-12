package com.example.kotlinstandardapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstandardapplication.Multipurpose.Multipurpose
import com.example.kotlinstandardapplication.Multipurpose.Notification
import com.example.kotlinstandardapplication.Util.SharePreferenceUtil2
import com.example.kotlinstandardapplication.databindingpage.lession2bindingadapter.DataBindActivity2


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
        Notification.createNotificationChannel(this)
        openActivity()


        /*show notification*/
        Notification.setupNotificationWithImage(this, this, "Heil Hitler")


        /*share preference version 2*/
        SharePreferenceUtil2.initialize(this)
        val defaultLanguage = SharePreferenceUtil2.instance?.defaultLanguage
        Toast.makeText(this, "language: $defaultLanguage", Toast.LENGTH_SHORT).show()
    }


    override fun onResume() {
        super.onResume()
        Multipurpose.hideStatusBar(window)
        Multipurpose.setColorStatusBarAndNavigationBar(this, window)
    }

    /**
     * @author Phong-Kaster
     * @since 16-02-2023
     * open welcome screen if the application is open first time
     * open home screen if not
     */
    private fun openActivity()
    {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, DataBindActivity2::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}