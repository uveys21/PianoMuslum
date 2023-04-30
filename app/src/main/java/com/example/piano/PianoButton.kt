package com.example.piano

import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class PianoButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private var soundResource: Int = 0

    override fun performClick(): Boolean {
        super.performClick()
        val lastTouchEvent = MotionEvent.obtain(0, 0, MotionEvent.ACTION_CANCEL, 0f, 0f, 0)
        val event = lastTouchEvent ?: return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // Tuşa basıldığında yapılacak işlemler
                // Örneğin, ilgili nota sesini çal
                playSound(context, soundResource)
                // Animasyonu başlatmak için gerekli kodu burada ayarlayabilirsiniz
                // Örneğin: this.startAnimation(animationResource)
                lastTouchEvent.recycle()
                return true
            }
            MotionEvent.ACTION_UP -> {
                // Tuştan parmağın çekildiğinde yapılacak işlemler
                // Animasyonu durdurmak veya sıfırlamak için gerekli kodu burada ayarlayabilirsiniz
                // Örneğin: this.clearAnimation()
                lastTouchEvent.recycle()
                return true
            }
            else -> {
                lastTouchEvent.recycle()
                return false
            }
        }
    }




    private fun playSound(context: Context, soundResource: Int) {
        val mediaPlayer = MediaPlayer.create(context, soundResource)
        mediaPlayer.start()
    }

}
