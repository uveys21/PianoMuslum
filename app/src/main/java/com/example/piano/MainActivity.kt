package com.example.piano


import android.media.SoundPool
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var soundPool: SoundPool
    private var soundIds = IntArray(8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        findViewById<View>(R.id.button1).setOnClickListener(this)
        findViewById<View>(R.id.button2).setOnClickListener(this)
        findViewById<View>(R.id.button3).setOnClickListener(this)
        findViewById<View>(R.id.button4).setOnClickListener(this)
        findViewById<View>(R.id.button5).setOnClickListener(this)
        findViewById<View>(R.id.button6).setOnClickListener(this)
        findViewById<View>(R.id.button7).setOnClickListener(this)
        findViewById<View>(R.id.button8).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
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
        soundPool.release()
    }
}

