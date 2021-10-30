package com.gracecommunitycenter.gracecommunitycenter.fragments.playvideo

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.material.textfield.TextInputLayout
import com.gracecommunitycenter.gracecommunitycenter.R

class PlayVideoFragment : Fragment() {

    private lateinit var mView: View

    private var playerView: PlayerView? = null

    private lateinit var player: SimpleExoPlayer

    private var url: String? = null

    private var playwhenready = false

    private var currentWindow = 0
    private var playbackposition: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_play_video, container, false)

        val args = PlayVideoFragmentArgs.fromBundle(requireArguments())

        var videoTitle = mView.findViewById<TextView>(R.id.tv_fullscreen_title)
        playerView = mView.findViewById<PlayerView>(R.id.exoplayer_fullscreen)

        var videourl = mView.findViewById<TextView>(R.id.videourl)

        videoTitle.text = args.videoTitle

        videourl.text = args.videourl

        url = args.videourl



        return mView
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        val datasourcefactory: DataSource.Factory = DefaultHttpDataSourceFactory("video")
        return ProgressiveMediaSource.Factory(datasourcefactory)
            .createMediaSource(uri)
    }

    private fun initializeplayer() {
        player = ExoPlayerFactory.newSimpleInstance(context)
        playerView?.setPlayer(player)
        val uri = Uri.parse(url)
        val mediaSource = buildMediaSource(uri)
        player.setPlayWhenReady(playwhenready)
        player.seekTo(currentWindow, playbackposition)
        player.prepare(mediaSource, false, false)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 26) {
            initializeplayer()
        }
    }

     override fun onResume() {
        super.onResume()
        if (Util.SDK_INT >= 26 || player == null) {
            //  initializeplayer();
        }
    }

     override fun onPause() {
        super.onPause()
        if (Util.SDK_INT > 26) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            playwhenready = player.playWhenReady
            playbackposition = player.currentPosition
            currentWindow = player.currentWindowIndex
            player = null!!
        }
    }








}