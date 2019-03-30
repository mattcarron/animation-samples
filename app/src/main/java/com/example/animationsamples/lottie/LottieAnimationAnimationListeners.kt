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
            pencilAnimation.setAnimation("pencil_write_anim.json")
            pencilAnimation.playAnimation()
            pencilAnimation.loop(true)

            var forwardAnimation = true
            pencilAnimation.addAnimatorUpdateListener {

                // Set animation progress
                val progress = (it.animatedValue as Float * 100).toInt()
                progressTv.text = "Progress: $progress%"

                // Reverse animation
                if (forwardAnimation && progress >= 50 ||
                    !forwardAnimation && progress <= 0
                ) {
                    forwardAnimation = !forwardAnimation
                    pencilAnimation.reverseAnimationSpeed()
                }
            }
        }
    }
}