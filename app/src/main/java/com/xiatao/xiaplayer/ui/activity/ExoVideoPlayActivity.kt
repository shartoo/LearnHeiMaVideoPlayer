package com.xiatao.xiaplayer.ui.activity

import com.xiatao.xiaplayer.base.BaseActivity
import com.xiatao.xiaplayer.model.PartHaoKanMV
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import android.content.Intent
import android.net.Uri
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.*
import com.google.android.exoplayer2.util.EventLogger
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_exo_playvideo.*
import java.io.IOException

// https://gist.github.com/codeshifu/c26bb8a5f27f94d73b3a4888a509927c
class ExoVideoPlayActivity:BaseActivity(){
    var dataSourceFactory:DataSource.Factory? = null
    var  videoSource:MediaSource? = null
    var trackSelector: DefaultTrackSelector? = null
    var  exoplayer:SimpleExoPlayer? = null //SimpleExoPlayer.Builder(this).build()
    var videoPlayBean:PartHaoKanMV? = null
    var startAutoplay = true

    override fun getLayoutId(): Int {
        return com.xiatao.xiaplayer.R.layout.activity_exo_playvideo
    }

    fun createMediaSource(intent: Intent): MediaSource? {
        //首先你需要获取到Uri，我这里是通过另一个activity的intent传递过来的
        //获取数据.此处的 "item" 会在 MvPagerFragment 中 startActivity中作为key
        videoPlayBean = intent.getParcelableExtra<PartHaoKanMV>("item")
        val playUrl = videoPlayBean!!.videoUrl
         println("网络视频地址 \t ${playUrl}")
        var videoUri: Uri = Uri.parse(playUrl)
        //接下来你需要分析出你要播放的视频是什么格式的
        val type: Int = Util.inferContentType(videoUri)
        println("解析出的视频格式为 ： $type")
        //这里的BassApplication是我自定义的application的名称
        var dataSourceFactory: DataSource.Factory =
                DefaultHttpDataSourceFactory(Util.getUserAgent(this, "BassApplication"))
        //接下来就是根据不同的视频格式，创建不同的MediaSource了
        return when (type) {
            C.TYPE_DASH ->
                DashMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
            C.TYPE_SS ->
                SsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
            C.TYPE_HLS ->
                HlsMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
            C.TYPE_OTHER ->
                ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
            else -> null
        }
    }

    override fun initData() {
        initPlayer()
    }

    fun initPlayer(){
        if(exoplayer==null){
            trackSelector = DefaultTrackSelector(this)
            //初始化Exoplayer
            exoplayer = SimpleExoPlayer.Builder(this).build()
            //监听播放状态以及失败原因
            exoplayer!!.addListener(PlayerEventListener())
            //将错误信息打印出来
            exoplayer!!.addAnalyticsListener(EventLogger(trackSelector))
            //
            exoplayer!!.setAudioAttributes(
                    AudioAttributes.DEFAULT, /* handleAudioFocus= */  /* handleAudioFocus= */
                    true
            )
            //当他准备好资源后就播放
            exoplayer!!.playWhenReady = startAutoplay
            //这里是将player设置近player_view中
            exoplayerView.player = exoplayer
            //
            //exoplayerView.setPlaybackPreparer(this)
        }
        //初始化完成后，我们就将资源设置近player中
        createMediaSource(intent)?.let { exoplayer!!.prepare(it) }
        }

    //这里监听到播放时的状态，以及获取到异常情况
    private class PlayerEventListener : Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            println("xxxxx  onPlayerStateChanged")
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            if (error.type == ExoPlaybackException.TYPE_SOURCE) {
                val cause: IOException = error.sourceException
                if (cause is HttpDataSource.HttpDataSourceException) {
                    val httpError: HttpDataSource.HttpDataSourceException = cause
                    val dataSpec = httpError.dataSpec
                    if (httpError is HttpDataSource.InvalidResponseCodeException) {
                        // Cast to InvalidResponseCodeException and retrieve the response code,
                        // message and headers.
                    } else {
                        // Try calling httpError.getCause() to retrieve the underlying cause,
                        // although note that it may be null.
                    }
                }
            }
            println("xxxxxxxxxx   发生播放异常")
        }

        override fun onTracksChanged(trackGroups: TrackGroupArray, trackSelections: TrackSelectionArray) {
            println("xxxxx  onTracksChanged")
        }
    }
    override fun onStart() {
        super.onStart()
        //初始化player
        initPlayer()
        //让player_view准备好
        exoplayerView?.onResume()
    }
    private fun releasePlayer() {
        if (exoplayer != null) {
            exoplayer!!.release()
            exoplayer = null
            trackSelector = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }
}
