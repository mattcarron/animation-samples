package com.example.animationsamples.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import kotlinx.android.synthetic.main.activity_android_framework_loading_video.*


class AndroidFrameworkLoadingVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_framework_loading_video)

        loadingFrameVideoPlayer.setup(
            R.drawable.autosergei_autocheck_activated_video_frame1,
            R.raw.autosergei_talk_baseline
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        loadingFrameVideoPlayer?.release()
    }
}
