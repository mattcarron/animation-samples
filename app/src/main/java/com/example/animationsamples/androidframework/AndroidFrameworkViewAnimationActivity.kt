package com.example.animationsamples.androidframework

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.*
import android.view.animation.Animation.*
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R


class AndroidFrameworkViewAnimationActivity : AppCompatActivity() {

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

        fourViewAnimationTypesSet()
        fourViewAnimationTypesOneView()
    }

    private fun fourViewAnimationTypesSet() {
        val scaleAnimation = ScaleAnimation(1f, 0.5f, 1f, 0.5f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.repeatCount = INFINITE
        scaleAnimation.repeatMode = REVERSE
        scaleAnimation.duration = 2000
        textViewScale.animation = scaleAnimation

        val translateAnimation = TranslateAnimation(
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 1f,
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 0f
        )
        translateAnimation.repeatCount = INFINITE
        translateAnimation.repeatMode = REVERSE
        translateAnimation.duration = 2000
        textViewTranslate.animation = translateAnimation

        val rotationAnimation = RotateAnimation(
            0f, 360f,
            RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        rotationAnimation.repeatCount = INFINITE
        rotationAnimation.repeatMode = REVERSE
        rotationAnimation.duration = 2000
        textViewRotation.animation = rotationAnimation
        textViewRotation.animate()

        val alphaAnimation = AlphaAnimation(1f, 0f)
        alphaAnimation.repeatCount = INFINITE
        alphaAnimation.repeatMode = REVERSE
        alphaAnimation.duration = 2000
        textViewAlpha.animation = alphaAnimation

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(rotationAnimation)
        animationSet.addAnimation(alphaAnimation)
        animationSet.start()
    }

    private fun fourViewAnimationTypesOneView() {
        val scaleAnimation = ScaleAnimation(1f, 0.5f, 1f, 0.5f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.repeatCount = INFINITE
        scaleAnimation.repeatMode = REVERSE
        scaleAnimation.duration = 2000

        val translateAnimation = TranslateAnimation(
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 1f,
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 0f
        )
        translateAnimation.repeatCount = INFINITE
        translateAnimation.repeatMode = REVERSE
        translateAnimation.duration = 2000

        val rotationAnimation = RotateAnimation(0f, 360f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        rotationAnimation.repeatCount = INFINITE
        rotationAnimation.repeatMode = REVERSE
        rotationAnimation.duration = 2000

        val alphaAnimation = AlphaAnimation(1f, 0f)
        alphaAnimation.repeatCount = INFINITE
        alphaAnimation.repeatMode = REVERSE
        alphaAnimation.duration = 2000

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(rotationAnimation)
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(alphaAnimation)
        textViewAllTypes.animation = animationSet
        animationSet.start()
    }

    private fun fourViewAnimationOneView() {
        val scaleAnimation = ScaleAnimation(1f, 0.5f, 1f, 0.5f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        val translateAnimation = TranslateAnimation(
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 1f,
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 0f
        )
        val rotationAnimation = RotateAnimation(0f, 360f)
        val alphaAnimation = AlphaAnimation(1f, 0f)

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(rotationAnimation)
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(alphaAnimation)
        animationSet.repeatCount = INFINITE
        animationSet.repeatMode = REVERSE
        animationSet.duration = 2000
        textViewAllTypes.animation = animationSet
        textViewAllTypes.animate()
    }


    private fun fourObjectAnimatorOneView() {
        val scaleAnimation = ScaleAnimation(1f, 0.5f, 1f, 0.5f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f)
        val translateAnimation = TranslateAnimation(
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 1f,
            RELATIVE_TO_PARENT, 0f,
            RELATIVE_TO_PARENT, 0f
        )
        val rotationAnimation = RotateAnimation(0f, 360f)
        val alphaAnimation = AlphaAnimation(1f, 0f)

        val animationSet = AnimationSet(true)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(rotationAnimation)
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(alphaAnimation)
        animationSet.repeatCount = INFINITE
        animationSet.repeatMode = REVERSE
        animationSet.duration = 2000
        textViewAllTypes.animation = animationSet
        textViewAllTypes.animate()
    }

    private fun dpToPixel(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            resources.displayMetrics
        )
    }

    inline fun <T : View> T.waitForMeasurement(crossinline callback: T.() -> Unit) {
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                if (measuredWidth > 0 && measuredHeight > 0) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    callback()
                }
            }
        })
    }
}
