package com.example.animationsamples.lottie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation_listeners.lottieAnimationView
import kotlinx.android.synthetic.main.activity_lottie_animation_listeners.playAnimationButton
import kotlinx.android.synthetic.main.activity_lottie_animation_listeners.progressTv
import kotlinx.android.synthetic.main.activity_lottie_animation_listeners_short_circuit.*

class LottieAnimationAnimationListenersShortcircuit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_listeners_short_circuit)

        var downloading: Boolean
        playAnimationButton.setOnClickListener {
            downloading = true
            lottieAnimationView.playAnimation()
            lottieAnimationView.addAnimatorUpdateListener { valueAnimator ->
                // Set animation progress
                val progress = (valueAnimator.animatedValue as Float * 100).toInt()
                progressTv.text = "Progress: $progress%"

                if (downloading && progress >= 40) {
                    lottieAnimationView.progress = 0f
                }
            }
        }

        downloadFinishedButton.setOnClickListener {
            downloading = false
        }
    }
}