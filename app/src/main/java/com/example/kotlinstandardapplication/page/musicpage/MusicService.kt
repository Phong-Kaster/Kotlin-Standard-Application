package com.example.kotlinstandardapplication.page.musicpage

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.example.kotlinstandardapplication.R

/**
 * @since 29-03-2023
 * Music Service
 * To use music service, we have to define it in AndroidManifest.xml
 * It's a foreground service.
 * It creates a music player notification and defines Pending Intents to handle clickOn event
 * These pending intents is transferred to Music Broadcast receiver to continue handling.
 */
class MusicService : Service() {

    private val tag = "MusicActivity - MusicService"
    private var myBinder = MyBinder()
    private lateinit var mediaSessionCompat : MediaSessionCompat

    override fun onBind(p0: Intent?): IBinder {
        mediaSessionCompat = MediaSessionCompat(baseContext, "tag")
        return myBinder
    }

    inner class MyBinder: Binder(){
        fun getInstance(): MusicService {
            return this@MusicService
        }
    }

    companion object {
        private const val CHANNEL_ID = "music-page"
        const val ACTION_PREVIOUS = "action-previous"
        const val ACTION_PLAY = "action-play"
        const val ACTION_NEXT = "action-next"
        const val ACTION_EXIT = "action-exit"



        private lateinit var notification: Notification

        fun createNotificationChannel(context: Context) {
            // Create the NotificationChannel, but only on API 26+ because
            // the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = context.getString(R.string.app_name)
                val descriptionText = context.getString(R.string.app_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                // Register the channel with the system
                val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }/*end Companion object*/

    @SuppressLint("UnspecifiedImmutableFlag")
    fun setupMusicNotification(icon: Int) {
        /*1. setup basically*/
        /*val notificationManagerCompat = NotificationManagerCompat.from(applicationContext)*/
        val style = androidx.media.app.NotificationCompat.MediaStyle().setMediaSession(mediaSessionCompat.sessionToken)
        val album = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.img_fuhrer)
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT


        /*2. setup intent for PLAY, NEXT & PREVIOUS*/
        val intentPrevious =
            Intent(applicationContext, MusicBroadcastReceiver::class.java).setAction(ACTION_PREVIOUS)
        val intentPlayPause =
            Intent(applicationContext, MusicBroadcastReceiver::class.java).setAction(ACTION_PLAY)
        val intentNext =
            Intent(applicationContext, MusicBroadcastReceiver::class.java).setAction(ACTION_NEXT)
        val intentExit =
            Intent(applicationContext, MusicBroadcastReceiver::class.java).setAction(ACTION_EXIT)


        val pendingIntentPrevious = PendingIntent.getBroadcast(applicationContext, 0, intentPrevious, flag)
        val pendingIntentPlayPause =
            PendingIntent.getBroadcast(applicationContext, 0, intentPlayPause, flag)
        val pendingIntentNext = PendingIntent.getBroadcast(applicationContext, 0, intentNext, flag)
        val pendingIntentExit = PendingIntent.getBroadcast(applicationContext, 0, intentExit, flag)


        val intentHome = Intent(this, MusicActivity::class.java)
        val pendingIntentHome: PendingIntent? = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intentHome)
            getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        /*3. setup notification for playing music & attach pending intents*/
        val position = MusicActivity.position
        val song = MusicActivity.songs[position].name
        val artist = MusicActivity.songs[position].artist

        notification = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentIntent(pendingIntentHome)
            .setContentTitle(song)
            .setContentText(artist)
            .setSmallIcon(R.drawable.ic_swastika)
            .setLargeIcon(album)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_previous, "Previous", pendingIntentPrevious)
            .addAction(icon, "Play", pendingIntentPlayPause)
            .addAction(R.drawable.ic_next, "Next", pendingIntentNext)
            .addAction(R.drawable.ic_exit, "Exit", pendingIntentExit)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(style)
            .setPriority(androidx.core.app.NotificationCompat.PRIORITY_HIGH)
            .setVisibility(androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC)
            .build()
        /*notificationManagerCompat.notify(1, notification)*/
        startForeground(13, notification)
    }
}