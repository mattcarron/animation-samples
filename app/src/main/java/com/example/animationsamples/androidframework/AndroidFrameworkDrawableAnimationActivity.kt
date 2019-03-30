package com.example.animationsamples.androidframework

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_android_framework_drawable_animation.*


class AndroidFrameworkDrawableAnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_framework_drawable_animation)

        imageview.setBackgroundResource(R.drawable.droid_animation)
        imageview_morphing_icon.setBackgroundResource(R.drawable.avd_anim)

        animate_btn.setOnClickListener {
            (imageview.background as AnimationDrawable).start()
            (imageview_morphing_icon.background as AnimatedVectorDrawable).start()
        }
    }
}
