package com.example.animationsamples.lottie

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation_dynamic_properties.*

class LottieAnimationDynamicPropertiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation_dynamic_properties)

        // Get keypath
        playAnimationButton.setOnClickListener {
            pencilAnimation.playAnimation()

            val log = pencilAnimation.resolveKeyPath(KeyPath("**"))
            for (i in log) run {
                Log.i("KeyPath", i.toString())
            }
        }

        // Update animation properties at runtime
        changeColourButton.setOnClickListener {
            pencilAnimation.addValueCallback(
                // Target specific layer
                KeyPath("pencil", "Group 8", "Fill 1"),

                //Target wildcard(*)
//                KeyPath("*", "Group 7", "Stroke 1"),

                //Target globstar(**)
//                KeyPath("**", "Stroke 1"),
                LottieProperty.COLOR, { Color.GREEN }
            )
        }
    }
}