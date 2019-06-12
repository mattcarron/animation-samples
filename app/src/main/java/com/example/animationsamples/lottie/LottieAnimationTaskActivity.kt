package com.example.animationsamples.lottie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_lottie_animation.*

class LottieAnimationTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_animation)

        lottieXML.setOnClickListener { launchIntent(AnimationType.LOTTIE_XML) }
        lottieProgrammatically.setOnClickListener { launchIntent(AnimationType.LOTTIE_PROGRAMMATICALLY) }
        lottieAttributes.setOnClickListener { launchIntent(AnimationType.LOTTIE_ATTRIBUTES) }
        lottieAnimationListeners.setOnClickListener { launchIntent(AnimationType.LOTTIE_ANIMATION_LISTENER) }
        lottieAnimationListenersShortCircuit.setOnClickListener { launchIntent(AnimationType.LOTTIE_ANIMATION_LISTENER_SHORT_CIRCUIT) }
        lottieDynamicProperties.setOnClickListener { launchIntent(AnimationType.LOTTIE_DYNAMIC_PROPERTIES) }
    }

    private fun launchIntent(animationType: AnimationType) {
        val intent: Intent = when (animationType) {
            AnimationType.LOTTIE_XML -> Intent(this, LottieAnimationXMLActivity::class.java)
            AnimationType.LOTTIE_ATTRIBUTES -> Intent(this, LottieAnimationAttributesActivity::class.java)
            AnimationType.LOTTIE_PROGRAMMATICALLY -> Intent(this, LottieAnimationActivity::class.java)
            AnimationType.LOTTIE_ANIMATION_LISTENER -> Intent(this, LottieAnimationAnimationListeners::class.java)
            AnimationType.LOTTIE_ANIMATION_LISTENER_SHORT_CIRCUIT -> Intent(this, LottieAnimationAnimationListenersShortcircuit::class.java)
            AnimationType.LOTTIE_DYNAMIC_PROPERTIES -> Intent(this, LottieAnimationDynamicPropertiesActivity::class.java)
        }
        startActivity(intent)
    }

    enum class AnimationType {
        LOTTIE_XML,
        LOTTIE_PROGRAMMATICALLY,
        LOTTIE_ATTRIBUTES,
        LOTTIE_ANIMATION_LISTENER,
        LOTTIE_ANIMATION_LISTENER_SHORT_CIRCUIT,
        LOTTIE_DYNAMIC_PROPERTIES
    }
}