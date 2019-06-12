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

        // Get KeyPath
        playAnimationButton.setOnClickListener {
            watch_animation.playAnimation()

            watch_animation.resolveKeyPath(KeyPath("**")).forEach {
                Log.i("KeyPath", it.toString())
            }
        }

        // Update properties at run time (choose 1 option):
        // Target one specific layer
        changeColourButton.setOnClickListener {
            watch_animation.addValueCallback(
                KeyPath("Crown Outlines", "Group 1", "Fill 1"),
                LottieProperty.COLOR, { Color.GREEN }
            )

            // Wildcard (*)
//            changeColourButton.setOnClickListener {
//                watch_animation.addValueCallback(
//                    KeyPath("*", "Group 1", "Fill 1"),
//                    LottieProperty.COLOR, { Color.GREEN }
//                )
//            }

            // Globstar (**)
//            changeColourButton.setOnClickListener {
//                watch_animation.addValueCallback(
//                    KeyPath("**", "Fill 1"),
//                    LottieProperty.COLOR, { Color.GREEN }
//                )
//            }
        }
    }
}