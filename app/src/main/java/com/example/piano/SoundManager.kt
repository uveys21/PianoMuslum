package com.example.piano


import android.content.Context
import android.media.MediaPlayer


class SoundManager(private val context: Context) {
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    // Ses çalma işlemini gerçekleştiren fonksiyon
    fun playSound(soundFileName: Int) {
        val soundManager = SoundManager(context)
        val soundResource = R.raw.nota1
        soundManager.playSound(soundResource)

        mediaPlayer.apply {
            reset()
            setDataSource(context, uri)
            prepare()
            start()
        }
    }
}



