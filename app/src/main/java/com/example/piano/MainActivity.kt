package com.example.piano

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var soundPool: SoundPool
    private var soundIds = IntArray(8)
    private var isMusicPlaying: Boolean = false
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var seekBar: SeekBar

    companion object {
        const val EXTRA_SELECTED_MUSIC = "extra_selected_music"
    }


    private val musicSelectionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val extras = result.data?.extras
                if (extras != null) {
                    val selectedMusic = extras.getString(EXTRA_SELECTED_MUSIC)
                    selectedMusic?.let { music ->
                        when (music) {
                            "Muzik 1" -> {
                                mediaPlayer.release()
                                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.kahtali)
                                mediaPlayer.start()
                                isMusicPlaying = true
                            }
                            "Muzik 2" -> {
                                mediaPlayer.release()
                                mediaPlayer = MediaPlayer.create(this@MainActivity, R.raw.muslum)
                                mediaPlayer.start()
                                isMusicPlaying = true
                            }
                        }
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById(R.id.music_seekbar)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnPreparedListener { mp ->
            mp.start()
            seekBar.max = mp.duration
        }

        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )

        mediaPlayer.setOnCompletionListener {
            seekBar.progress = 0
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val newPosition = progress * mediaPlayer.duration / 100
                    mediaPlayer.seekTo(newPosition)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.start()
            }
        })

        soundPool = SoundPool.Builder()
            .setMaxStreams(8)
            .build()

        soundIds[0] = soundPool.load(this, R.raw.do_note, 1)
        soundIds[1] = soundPool.load(this, R.raw.re_note, 1)
        soundIds[2] = soundPool.load(this, R.raw.mi_note, 1)
        soundIds[3] = soundPool.load(this, R.raw.fa_note, 1)
        soundIds[4] = soundPool.load(this, R.raw.sol_note, 1)
        soundIds[5] = soundPool.load(this, R.raw.la_note, 1)
        soundIds[6] = soundPool.load(this, R.raw.si_note, 1)
        soundIds[7] = soundPool.load(this, R.raw.do_note_2, 1)

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener(this)

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener(this)

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener(this)

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener(this)

        val button5 = findViewById<Button>(R.id.button5)
        button5.setOnClickListener(this)

        val button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener(this)

        val button7 = findViewById<Button>(R.id.button7)
        button7.setOnClickListener(this)

        val button8 = findViewById<Button>(R.id.button8)
        button8.setOnClickListener(this)

        val playButton = findViewById<Button>(R.id.play_button)
        playButton.setOnClickListener(this)

        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener(this)

        val listButton = findViewById<Button>(R.id.muzik_button)
        listButton.setOnClickListener {
            val intent = Intent(this, MuziklerActivity::class.java)
            musicSelectionLauncher.launch(intent)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.play_button -> {
                if (!isMusicPlaying) {
                    mediaPlayer.start()
                    isMusicPlaying = true
                }
            }

            R.id.pause_button -> {
                if (isMusicPlaying) {
                    mediaPlayer.pause()
                    isMusicPlaying = false
                }
            }

            R.id.muzik_button -> {
                val intent = Intent(this, MuziklerActivity::class.java)
                musicSelectionLauncher.launch(intent)
            }

            R.id.button1 -> soundPool.play(soundIds[0], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button2 -> soundPool.play(soundIds[1], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button3 -> soundPool.play(soundIds[2], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button4 -> soundPool.play(soundIds[3], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button5 -> soundPool.play(soundIds[4], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button6 -> soundPool.play(soundIds[5], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button7 -> soundPool.play(soundIds[6], 1.0f, 1.0f, 0, 0, 1.0f)
            R.id.button8 -> soundPool.play(soundIds[7], 1.0f, 1.0f, 0, 0, 1.0f)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        soundPool.release()
    }
}



