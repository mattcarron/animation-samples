package com.example.animationsamples.video

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import com.example.animationsamples.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import kotlinx.android.synthetic.main.activity_android_framework_video.*


class AndroidFrameworkVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_framework_video)

        setup(R.raw.autosergei_talk_baseline)
    }

    fun setup(@RawRes videoUri: Int) {
        val player = createSimpleExoPlayer()
        player.prepare(createVideoSource(videoUri))

        setPlayerToVideoPlayerView(player)
    }

    override fun onDestroy() {
        super.onDestroy()
        release()
    }

    private fun createSimpleExoPlayer(): SimpleExoPlayer {
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        player.playWhenReady = true
        player.repeatMode = Player.REPEAT_MODE_ONE
        return player
    }

    private fun createVideoSource(videoUri: Int): ExtractorMediaSource {
        val dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(videoUri))
        val rawResourceDataSource = RawResourceDataSource(this)
        rawResourceDataSource.open(dataSpec)
        val factory = DataSource.Factory { rawResourceDataSource }
        val createMediaSource = ExtractorMediaSource.Factory(factory)
            .setExtractorsFactory(DefaultExtractorsFactory())
            .createMediaSource(rawResourceDataSource.uri)
        rawResourceDataSource.close()
        return createMediaSource
    }

    private fun setPlayerToVideoPlayerView(player: SimpleExoPlayer) {
        playerView.visibility = View.VISIBLE
        playerView.setKeepContentOnPlayerReset(true)
        playerView.useController = false
        playerView.player = player
    }

    fun release() {
        playerView
            ?.player
            ?.release()
    }
}
