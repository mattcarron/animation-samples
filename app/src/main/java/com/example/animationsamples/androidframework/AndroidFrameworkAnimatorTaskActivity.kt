package com.example.animationsamples.androidframework

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_android_framework_animation.*

class AndroidFrameworkAnimatorTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_framework_animation)

        pencilAnimation.setAnimation("pencil_write_anim.json")
        pencilAnimation.loop(true)

        pencil_imageview.waitForMeasurement {
            runAnimationMixAnimationAndAnimator()
            pencilAnimation.playAnimation()
        }
    }

    private fun runAnimationMixAnimationAndAnimator() {
        val pencil = findViewById<ImageView>(R.id.pencil_imageview)
        val pencilLine = findViewById<View>(R.id.pencil_line_view)
        val pencilLineCoverUp = findViewById<View>(R.id.pencil_line_view_cover_up)
        val xDistancePixels = dpToPixel(R.dimen.pencilAnimation_drawDistance)

        val leftToRightDuration = 800L
        val lineUnscaleDuration = 550L
        val spinDuration = 1100L

        val lineUnscaleStartTime = 450L
        val spinStartTime = 900L

        // Pen Left to Right
        val rotateAnimator = ObjectAnimator.ofFloat(pencil, View.ROTATION, -5f, 10f)
        val objectAnimatorLeftToRight = ObjectAnimator.ofFloat(pencil, View.TRANSLATION_X, 0f, xDistancePixels)
        objectAnimatorLeftToRight.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {
                pencil.pivotX = pencil.width / 2f
                pencil.pivotY = pencil.height.toFloat()
            }
        })

        val leftToRightAnimatorSet = AnimatorSet()
        leftToRightAnimatorSet.playTogether(rotateAnimator, objectAnimatorLeftToRight)
        leftToRightAnimatorSet.interpolator = AccelerateDecelerateInterpolator()
        leftToRightAnimatorSet.duration = leftToRightDuration

        // Line draw left to right
        pencilLine.pivotX = 0f
        val scaleAnimationScaleAnimator = ObjectAnimator.ofFloat(pencilLine, View.SCALE_X, 0f, 1f)
        scaleAnimationScaleAnimator.duration = leftToRightDuration

        // Line erase left to right
        pencilLineCoverUp.pivotX = 0f
        val unscaleAnimationScaleAnimator = ObjectAnimator.ofFloat(pencilLineCoverUp, View.SCALE_X, 0f, 1f)
        unscaleAnimationScaleAnimator.duration = lineUnscaleDuration
        unscaleAnimationScaleAnimator.startDelay = lineUnscaleStartTime

        val lineScaleAnimatorSet = AnimatorSet()
        lineScaleAnimatorSet.playTogether(
            scaleAnimationScaleAnimator,
            unscaleAnimationScaleAnimator
        )

        // Pen back flip back to start
        val pencilWidth = pencil.width
        val lineWidth = pencilLine.width
        val x = lineWidth.toFloat()
        val y = 0f
        val path = Path()
        path.moveTo(x, y)
        path.quadTo(
            x - xDistancePixels / 2,
            y - xDistancePixels / 2,
            x - xDistancePixels - pencilWidth / 2,
            y
        )
        val pencilPathTranslation = ObjectAnimator.ofFloat(
            pencil, View.TRANSLATION_X, View.TRANSLATION_Y, path)

        // Pencil spin
        val objectAnimatorSpin = ObjectAnimator.ofFloat(pencil, View.ROTATION, 10f, -365f)

        objectAnimatorSpin.interpolator = DecelerateInterpolator(0.8f)
        objectAnimatorSpin.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {
                pencil.pivotX = pencil.width / 2f
                pencil.pivotY = pencil.height / 2f
            }
        })

        val pencilSpinSet = AnimatorSet()
        pencilSpinSet.playTogether(pencilPathTranslation, objectAnimatorSpin)
        pencilSpinSet.startDelay = spinStartTime
        pencilSpinSet.duration = spinDuration

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(lineScaleAnimatorSet, pencilSpinSet, leftToRightAnimatorSet)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {

                animatorSet.start()
            }

            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })

        animatorSet.start()
    }

    private fun dpToPixel(id: Int): Float {
        return resources.getDimensionPixelSize(id).toFloat()
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
