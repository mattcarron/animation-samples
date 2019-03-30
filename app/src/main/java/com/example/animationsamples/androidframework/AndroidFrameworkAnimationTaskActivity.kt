package com.example.animationsamples.androidframework

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.*
import android.view.animation.Animation.RELATIVE_TO_SELF
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_android_framework_animation.*


class AndroidFrameworkAnimationTaskActivity : AppCompatActivity() {

    private lateinit var pencil: ImageView
    private lateinit var pencilLine: View
    private lateinit var pencilLineCoverUp: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_framework_animation)

        pencil = findViewById(R.id.pencil_imageview)
        pencilLine = findViewById(R.id.pencil_line_view)
        pencilLineCoverUp = findViewById(R.id.pencil_line_view_cover_up)

        pencilAnimation.setAnimation("pencil_write_anim.json")

        pencil.waitForMeasurement {
            runAnimation()

            pencilAnimation.playAnimation()
        }
    }

    private fun runAnimation() {
        val xDistancePixels = dpToPixel(R.dimen.pencilAnimation_drawDistance)

        val leftToRightDuration = 800L
        val lineUnscaleDuration = 500L
        val spinDuration = 1000L

        val lineUnscaleStartTime = 500L
        val spinStartTime = 1000L

        // Pen Left to Right
        val rotateAnimation = RotateAnimation(-5f, 10f, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 1f)
        val translateAnimation = TranslateAnimation(0f, xDistancePixels, 0f, 0f)
        val animationSetPencil = AnimationSet(false)
        animationSetPencil.addAnimation(rotateAnimation)
        animationSetPencil.addAnimation(translateAnimation)
        animationSetPencil.duration = leftToRightDuration
        animationSetPencil.fillAfter = true
        animationSetPencil.interpolator = AccelerateDecelerateInterpolator()
        pencil.animation = animationSetPencil
        pencil.animate()

        // Line draw left to right
        val scaleAnimationScaleAnimation = ScaleAnimation(0f, 1f, 1f, 1f, 0f, 0.5f)
        scaleAnimationScaleAnimation.duration = leftToRightDuration
        scaleAnimationScaleAnimation.fillAfter = true
        pencilLine.animation = scaleAnimationScaleAnimation
        pencilLine.animate()

        // Line erase left to right
        val scaleAnimationUnscaleAnimation = ScaleAnimation(0f, 1f, 1f, 1f, 0f, 0.5f)
        scaleAnimationUnscaleAnimation.duration = lineUnscaleDuration
        scaleAnimationUnscaleAnimation.startOffset = lineUnscaleStartTime
        scaleAnimationUnscaleAnimation.fillAfter = true
        pencilLineCoverUp.animation = scaleAnimationUnscaleAnimation
        pencilLine.animate()

        // Pen back flip back to start
        val pencilWidth = pencil.width
        val x = 0f
        val y = 0f
        val path = Path()
        path.moveTo(x, y)
        path.quadTo(
            x - xDistancePixels / 2,
            y - xDistancePixels / 2,
            x - xDistancePixels - pencilWidth/2,
            y + pencilWidth
        )
        val objectAnimatorArc = ObjectAnimator.ofFloat(pencil, View.TRANSLATION_X, View.TRANSLATION_Y, path)
        objectAnimatorArc.startDelay = spinStartTime
        objectAnimatorArc.duration = spinDuration
        objectAnimatorArc.start()

        // Pencil spin
        val objectAnimatorSpin = ObjectAnimator.ofFloat(pencil, View.ROTATION, 0f, -375f)
        objectAnimatorSpin.startDelay = spinStartTime
        objectAnimatorSpin.duration = spinDuration
        objectAnimatorSpin.start()
    }

    private fun dpToPixel(id: Int): Float {
        return resources.getDimensionPixelSize(id).toFloat()
    }

    inline fun <T: View> T.waitForMeasurement(crossinline callback: T.() -> Unit) {
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