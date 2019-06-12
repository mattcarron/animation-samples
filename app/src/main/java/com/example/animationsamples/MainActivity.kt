package com.example.animationsamples

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.MainActivity.AnimationType.*
import com.example.animationsamples.androidframework.*
import com.example.animationsamples.lottie.LottieAnimationTaskActivity
import com.example.animationsamples.video.AndroidFrameworkLoadingVideoActivity
import com.example.animationsamples.video.AndroidFrameworkVideoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lottie.setOnClickListener { launchIntent(LOTTIE_ANIMATION) }
        androidFrameworkTaskAnimation.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_TASK_ANIMATION) }
        androidFrameworkTaskAnimator.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_TASK_ANIMATOR) }
        androidFrameworkViewAnimation.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_VIEW_ANIMATION) }
        androidFrameworkAnimator.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_ANIMATOR) }
        androidFrameworkDrawableAnimation.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_DRAWABLE_ANIMATION) }
        androidFrameworkVideoAnimation.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_VIDEO_ANIMATION) }
        androidFrameworkLoadingVideoAnimation.setOnClickListener { launchIntent(ANDROID_FRAMEWORK_LOADING_VIDEO_ANIMATION) }
    }

    private fun launchIntent(animationType: AnimationType) {
        val intent: Intent = when (animationType) {
            LOTTIE_ANIMATION -> Intent(this, LottieAnimationTaskActivity::class.java)
            ANDROID_FRAMEWORK_TASK_ANIMATION -> Intent(this, AndroidFrameworkAnimationTaskActivity::class.java)
            ANDROID_FRAMEWORK_TASK_ANIMATOR -> Intent(this, AndroidFrameworkAnimatorTaskActivity::class.java)
            ANDROID_FRAMEWORK_VIEW_ANIMATION -> Intent(this, AndroidFrameworkViewAnimationActivity::class.java)
            ANDROID_FRAMEWORK_ANIMATOR -> Intent(this, AndroidFrameworkAnimatorActivity::class.java)
            ANDROID_FRAMEWORK_DRAWABLE_ANIMATION -> Intent(this, AndroidFrameworkDrawableAnimationActivity::class.java)
            ANDROID_FRAMEWORK_VIDEO_ANIMATION -> Intent(this, AndroidFrameworkVideoActivity::class.java)
            ANDROID_FRAMEWORK_LOADING_VIDEO_ANIMATION -> Intent(this, AndroidFrameworkLoadingVideoActivity::class.java)
        }
        startActivity(intent)
    }

    enum class AnimationType {
        LOTTIE_ANIMATION,
        ANDROID_FRAMEWORK_TASK_ANIMATION,
        ANDROID_FRAMEWORK_TASK_ANIMATOR,
        ANDROID_FRAMEWORK_VIEW_ANIMATION,
        ANDROID_FRAMEWORK_ANIMATOR,
        ANDROID_FRAMEWORK_DRAWABLE_ANIMATION,
        ANDROID_FRAMEWORK_VIDEO_ANIMATION,
        ANDROID_FRAMEWORK_LOADING_VIDEO_ANIMATION,
    }

}
