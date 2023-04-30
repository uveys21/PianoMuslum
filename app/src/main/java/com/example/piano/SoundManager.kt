package com.example.piano


import android.content.Context
import android.media.MediaPlayer
import android.net.Uri


class SoundManager(private val context: Context) {
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    // Ses çalma işlemini gerçekleştiren fonksiyon
    fun playSound(soundFileName: Int) {
        val uri = Uri.parse("android.resource://${context.packageName}/$soundFileName")

        mediaPlayer.apply {
            reset()
            setDataSource(context, uri)
            prepare()
            start()
        }
    }

}



