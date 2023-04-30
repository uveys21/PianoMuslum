package com.example.piano

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MuziklerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SELECTED_MUSIC = "extra_selected_music"
    }

    private lateinit var mediaPlayer: MediaPlayer
    private var isMusicPlaying: Boolean = false

    private val musicSelectionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val extras = result.data?.extras
                if (extras != null) {
                    val selectedMusic = extras.getString(MainActivity.EXTRA_SELECTED_MUSIC)
                    selectedMusic?.let { music ->
                        val intent = Intent()
                        intent.putExtra(MainActivity.EXTRA_SELECTED_MUSIC, music)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.muzikler)

        val listView: ListView = findViewById(R.id.Liste)
        val muzik1Button = findViewById<Button>(R.id.muzik1)
        val muzik2Button = findViewById<Button>(R.id.muzik2)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
            isMusicPlaying = false
        }

        val muzikler = arrayOf("Muzik 1", "Muzik 2")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, muzikler)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedMuzik = muzikler[position]
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_SELECTED_MUSIC, selectedMuzik)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        muzik1Button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MainActivity.EXTRA_SELECTED_MUSIC, "Muzik 1")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        muzik2Button.setOnClickListener {
            val intent = Intent()
            intent.putExtra(MainActivity.EXTRA_SELECTED_MUSIC, "Muzik 2")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}
