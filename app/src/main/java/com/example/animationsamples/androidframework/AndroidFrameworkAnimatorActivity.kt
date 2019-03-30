package com.example.animationsamples.androidframework

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R


class AndroidFrameworkAnimatorActivity : AppCompatActivity() {

    private lateinit var textViewScale: TextView
    private lateinit var textViewTranslate: TextView
    private lateinit var textViewAlpha: TextView
    private lateinit var textViewRotation: TextView
    private lateinit var textViewAllTypes: TextView
    private lateinit var container: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_framework_view_animation)

        container = findViewById(R.id.container)

        textViewScale = findViewById(R.id.textview_scale)
        textViewTranslate = findViewById(R.id.textview_translate)
        textViewRotation = findViewById(R.id.textview_rotation)
        textViewAlpha = findViewById(R.id.textview_alpha)
        textViewAllTypes = findViewById(R.id.textview_alltypes)

        valueAnimator()
    }

    private fun valueAnimator() {

        val colorAnimator = ValueAnimator.ofArgb(Color.RED, Color.GREEN, Color.BLUE)
        colorAnimator.duration = 5000
        colorAnimator.repeatCount = INFINITE
        colorAnimator.repeatMode = REVERSE
        colorAnimator.addUpdateListener { animation ->
            textViewAllTypes.setTextColor(animation.animatedValue as Int)
        }
        colorAnimator.start()

        val colorObjectAnimator = ObjectAnimator.ofArgb(
            textViewAllTypes, "textColor", Color.RED, Color.GREEN, Color.BLUE)
        colorObjectAnimator.duration = 5000
        colorObjectAnimator.repeatCount = INFINITE
        colorObjectAnimator.repeatMode = REVERSE
        colorObjectAnimator.start()
    }
}
