package com.example.animationsamples.lottie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation_xml.*

class LottieAnimationXMLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_xml)

        playAnimationButton.setOnClickListener {
            pencilAnimation.playAnimation()
        }
    }
}