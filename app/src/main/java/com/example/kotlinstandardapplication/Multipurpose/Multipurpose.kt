package com.example.kotlinstandardapplication.Multipurpose

import android.content.Context
import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.example.kotlinstandardapplication.R

/**
 * @author Phong-Kaster
 * @since 16-02-2023
 * Multipurpose is a class which include all smart functions as beautiful string format,
 * hide status bar,
 *
 * It plays role as Helper in Website development
 */
class Multipurpose {
    companion object
    {
        /**
         * @author Phong-Kaster
         * @since 16-02-20223
         * @param window is the whole screen of device
         * hide the top status bar which shows time, battery status, wifi,...
         *
         */
        fun hideStatusBar(window: Window)
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            {
                window.insetsController?.hide(WindowInsets.Type.statusBars())
            }
            else// if Android version < Android 11
            {
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }// end hideStatusBar

        /**
         * @since 21-02-2023
         * convert a dp value to pixels
         * for instance, we want to set height for imageView is 30dp programmatically.
         * This function converts 30dp to pixel
         */
        fun convertDPtoPixel(context: Context, value: Int): Int {
            val scale: Float = context.resources.displayMetrics.density
            val pixels: Int = (value * scale + 0.5f).toInt()
            return pixels
        }

        /**
         * @since 23-02-2023
         * set color for status bar and bottom navigation
         */
        fun setColorStatusBarAndNavigationBar(context: Context, window: Window) {
            window.statusBarColor = ContextCompat.getColor(context, R.color.black); //status bar or the time bar at the top (see example image1)
            window.navigationBarColor = ContextCompat.getColor(context, R.color.gray); // Navigation bar the soft bottom of some phones like nexus and some Samsung note series  (see example image2)
        }
    }
}