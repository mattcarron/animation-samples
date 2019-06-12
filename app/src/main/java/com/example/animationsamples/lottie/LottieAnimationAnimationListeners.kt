package com.example.animationsamples.lottie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation_listeners.*

class LottieAnimationAnimationListeners : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_listeners)

        playAnimationButton.setOnClickListener {
            lottieAnimationView.setAnimation("watch_progress.json")
            lottieAnimationView.playAnimation()

            lottieAnimationView.addAnimatorUpdateListener { valueAnimator ->
                // Set animation progress
                val progress = (valueAnimator.animatedValue as Float * 100).toInt()
                progressTv.text = "Progress: $progress%"
            }
        }
    }
}