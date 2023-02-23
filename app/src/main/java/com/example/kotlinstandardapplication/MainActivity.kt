package com.example.kotlinstandardapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.kotlinstandardapplication.Home.HomeActivity
import com.example.kotlinstandardapplication.Introduction.IntroductionActivity
import com.example.kotlinstandardapplication.Login.LoginActivity
import com.example.kotlinstandardapplication.Multipurpose.Multipurpose
import com.example.kotlinstandardapplication.Multipurpose.Notification
import com.example.kotlinstandardapplication.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Notification.createNotificationChannel(this)
        openActivity()

        Notification.setupNotificationWithImage(this, this, "Heil Hitler")
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
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}