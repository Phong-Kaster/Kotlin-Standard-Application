package com.example.kotlinstandardapplication.Home

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.graphics.Outline
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewOutlineProvider
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.kotlinstandardapplication.Multipurpose.Multipurpose
import com.example.kotlinstandardapplication.Multipurpose.Multipurpose.Companion.convertDPtoPixel
import com.example.kotlinstandardapplication.Multipurpose.Notification
import com.example.kotlinstandardapplication.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource


/**
 * @author Phong-Kaster
 * @since 21-02-2023
 * Home Activity
 */
class HomeActivity : AppCompatActivity() {

    private val hlsStream2 = "https://cph-p2p-msl.akamaized.net/hls/live/2000341/test/master.m3u8"
    private val hlsStream = "https://v-liv-golf.viewlift.com/ArchiveA/livgolf-teamchampionship-finals-30Oct2022/index_4.m3u8"
    private var exoPlayer: ExoPlayer? = null
    private lateinit var playerView: PlayerView
    private lateinit var buttonFullscreen: ImageButton
    private lateinit var logo: ImageView
    private lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupComponent()
        setupEvent()

        //Notification.setupNotification(this, this, "Home Activity")
    }


    private fun setupComponent()
    {
        playerView = findViewById(R.id.playerView)
        logo = findViewById(R.id.logo)
        buttonFullscreen = findViewById(R.id.exo_fullscreen)
        layout = findViewById(R.id.layout)
    }


    /**
     * @since 20-02-2023
     */
    private fun setupPlayer()
    {
        try {
            playerView.outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setRoundRect(15, 15, view.width, view.height, 15f)
                }
            }
            playerView.clipToOutline = true

            // Create a data source factory.
            val dataSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
            val hlsStream = MediaItem.fromUri(hlsStream)
            val hlsStream2 = MediaItem.fromUri(hlsStream2)
            val hlsMediaSource: HlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
                .createMediaSource(hlsStream)

            // Build the media items.
            exoPlayer = ExoPlayer.Builder(this).build()
            playerView.player = exoPlayer
            playerView.keepScreenOn = true

            exoPlayer?.playWhenReady = true
            exoPlayer?.seekTo(currentItem, playbackPosition)

            /*Approach 1 - Media Item*/
            exoPlayer?.addMediaItem(hlsStream)
            exoPlayer?.addMediaItem(hlsStream2)
            exoPlayer!!.prepare()
            exoPlayer!!.play()



            /*Approach 2 - HLS way*/
            /*exoPlayer!!.setMediaSource(hlsMediaSource)
            exoPlayer!!.prepare()
            exoPlayer!!.play()*/
        }
        catch (ex: Exception)
        {
            exoPlayer!!.release()
            println("==========================ERROR=================")
            println(ex)
        }
    }


    private var playWhenReady = true
    private var currentItem = 0
    private var playbackPosition = 0L
    private fun releasePlayer() {
        exoPlayer?.let { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentItem = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
        }
        exoPlayer = null
    }

    override fun onResume() {
        super.onResume()
        Multipurpose.hideStatusBar(window)
        Multipurpose.setColorStatusBarAndNavigationBar(this, window)
        setupPlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }

    /**
     * xoay man hinh thi mo full man hinh cai player view
     */
    @SuppressLint("SourceLockedOrientationActivity")
    private fun setupEvent()
    {
        buttonFullscreen.setOnClickListener{
            if( resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE )
            {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT// update orientation
                logo.visibility = VISIBLE//hide logo
                playerView.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT// update height & width for player view
                playerView.layoutParams.height = convertDPtoPixel(this, 230)// update height & width for player view
                layout.background = ContextCompat.getDrawable(this, R.drawable.background_theme)// update background
            }
            else
            {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                logo.visibility = GONE
                playerView.layoutParams.height = LinearLayout.LayoutParams.MATCH_PARENT
                playerView.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
                layout.background = ContextCompat.getDrawable(this, R.color.black)
                Multipurpose.hideStatusBar(window)
            }
        }
    }
}