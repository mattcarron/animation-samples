package com.example.animationsamples.lottie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation_programmaticaly.*


class LottieAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_programmaticaly)

        playAnimationButton.setOnClickListener {
            pencilAnimation.setAnimation("pencil_write_anim.json")
            pencilAnimation.playAnimation()
            pencilAnimation.loop(true)
        }
    }
}