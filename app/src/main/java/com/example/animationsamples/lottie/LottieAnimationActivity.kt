package com.example.animationsamples.lottie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation_programmatically.*


class LottieAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_programmatically)

        playAnimationButton.setOnClickListener {
            watch_animation.setAnimation("watch_progress.json")
            watch_animation.loop(true)
            watch_animation.speed = 4f
            watch_animation.playAnimation()
        }
    }
}