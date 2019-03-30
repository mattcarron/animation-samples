package com.example.animationsamples.video

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
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
import com.google.android.exoplayer2.video.VideoListener
import kotlinx.android.synthetic.main.view_loading_videoplayer.view.*


class LoadingFrameVideoPlayer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
    : FrameLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_loading_videoplayer, this)
    }

    fun setup(@DrawableRes loadingImage: Int, @RawRes videoUri: Int) {
        setLoadingImage(loadingImage)

        val player = createSimpleExoPlayer()
        player.addVideoListener(createOnRenderedFirstFrameListener())
        player.prepare(createVideoSource(videoUri))

        setPlayerToVideoPlayerView(player)
    }

    private fun setLoadingImage(loadingImage: Int) {
        imageView.setImageResource(loadingImage)
        imageView.visibility = View.VISIBLE
    }

    private fun createSimpleExoPlayer(): SimpleExoPlayer {
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory()
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
        player.playWhenReady = true
        player.repeatMode = Player.REPEAT_MODE_ONE
        return player
    }

    private fun createOnRenderedFirstFrameListener(): VideoListener {
        return object : VideoListener {
            override fun onRenderedFirstFrame() {
                imageView.visibility = View.INVISIBLE
            }
        }
    }

    private fun createVideoSource(videoUri: Int): ExtractorMediaSource {
        val dataSpec = DataSpec(RawResourceDataSource.buildRawResourceUri(videoUri))
        val rawResourceDataSource = RawResourceDataSource(context)
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