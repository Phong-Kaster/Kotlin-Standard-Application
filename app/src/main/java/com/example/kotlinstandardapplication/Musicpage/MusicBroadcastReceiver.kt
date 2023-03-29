package com.example.kotlinstandardapplication.Musicpage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.kotlinstandardapplication.R
import kotlin.math.log
import kotlin.system.exitProcess

/**
 * @since 29-03-2023
 * Music Broadcast Receiver
 * To use music broadcast receiver, we have to define it in AndroidManifest.xml
 * this class listens pending intents from music service, then it depends on what received action is
 * this class will run corresponding functions: action previous, action play, ....
 */
class MusicBroadcastReceiver : BroadcastReceiver() {

    private val tag = "MusicActivity - Broadcast Receiver"

    override fun onReceive(context: Context?, intent: Intent?) {
        /*Log.d(tag, "received action = ${intent?.action}")*/
        when(intent?.action){
            //only play next or prev song, when music list contains more than one song
            MusicService.ACTION_PREVIOUS -> actionPrevious()
            MusicService.ACTION_PLAY -> actionPlay()
            MusicService.ACTION_NEXT -> actionNext()
            MusicService.ACTION_EXIT -> actionExit()
        }
    }

    private fun actionPrevious()
    {
        /*Log.d(tag, "actionPrevious() run")*/
        val position = MusicActivity.position
        val size = MusicActivity.songs.size
        if( position > 0 ) MusicActivity.position--

        /*Log.d(tag, "size: $size - position: $position")*/

        MusicActivity.isPlaying = true
        MusicActivity.musicService!!.setupMusicNotification(R.drawable.ic_pause)
    }

    private fun actionPlay()
    {
        val flag = MusicActivity.isPlaying
        /*Log.d(tag, "actionPlay() run")
        Log.d(tag, "before isPlaying = $flag")*/
        if(flag)/*playing so that pause*/
        {
            MusicActivity.isPlaying = false
            MusicActivity.musicService!!.setupMusicNotification(R.drawable.ic_play)
        }
        else/*pausing so that play*/
        {
            MusicActivity.isPlaying = true
            MusicActivity.musicService!!.setupMusicNotification(R.drawable.ic_pause)
        }
        /*Log.d(tag, "after isPlaying = ${MusicActivity.isPlaying}")*/
    }

    private fun actionNext()
    {
        /*Log.d(tag, "actionNext() run")*/
        val position = MusicActivity.position
        val size = MusicActivity.songs.size
        if( position < size-1 ) MusicActivity.position++

       /* Log.d(tag, "size: $size - position: $position")*/

        MusicActivity.isPlaying = true
        MusicActivity.musicService!!.setupMusicNotification(R.drawable.ic_pause)
    }

    /**
     * @since 28-03-2023
     * exit the application
     */
    private fun actionExit()
    {
        if( MusicActivity.musicService != null)
        {
            MusicActivity.musicService!!.stopForeground(true)/*stop service*/
            MusicActivity.musicService!!.stopSelf()
            MusicActivity.musicService = null/*clean service*/
            Log.d(tag, "Music service is stopped")
        }
        exitProcess(1)
    }
}