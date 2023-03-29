package com.example.kotlinstandardapplication.Musicpage

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.example.kotlinstandardapplication.R

/**
 * @since 28-03-2023
 * this activity is used to show song which is being played
 * this activity just show music player on notification system
 */
class MusicActivity : AppCompatActivity(), ServiceConnection {

    private val TAG = "MusicActivity"
    private lateinit var buttonPlay: ImageButton
    private lateinit var title: TextView


    companion object{
        var position = 0
        var musicService : MusicService? = null
        var songs = ArrayList<Music>()
        var isPlaying: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val intent = Intent(this, MusicService::class.java)
        bindService(intent, this, BIND_AUTO_CREATE)
        startService(intent)

        MusicService.createNotificationChannel(context = this)
        fetch()
        setupEvent()
    }

    private fun fetch()
    {
        val song1 = Music("Track 1", "Artist 1", R.drawable.ic_musik)
        val song2 = Music("Track 2", "Artist 2", R.drawable.ic_musik)
        val song3 = Music("Track 3", "Artist 3", R.drawable.ic_musik)
        val song4 = Music("Track 4", "Artist 4", R.drawable.ic_musik)
        val song5 = Music("Track 5", "Artist 5", R.drawable.ic_musik)

        songs.add(song1)
        songs.add(song2)
        songs.add(song3)
        songs.add(song4)
        songs.add(song5)
    }

    private fun setupEvent()
    {
        buttonPlay = findViewById(R.id.buttonPlay)
        buttonPlay.setOnClickListener {

        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        if(musicService == null)
        {
            val binder = service as MusicService.MyBinder
            musicService = binder.getInstance()
            Log.d(TAG, "Music service is NOT NULL")
        }
        musicService!!.setupMusicNotification(R.drawable.ic_play)
        Log.d(TAG, "Music player's notification is created")
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        musicService = null
    }
}