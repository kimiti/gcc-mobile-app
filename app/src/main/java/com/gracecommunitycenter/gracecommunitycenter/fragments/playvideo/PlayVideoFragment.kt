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
    private var mPlayer: SimpleExoPlayer? = null
    private lateinit var playerView: PlayerView
    private var url: String? = null
    private var playwhenready = false
    private var currentWindow = 0
    private var playbackposition: Long = 0
    private lateinit var videoTitle: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_play_video, container, false)

        val args = PlayVideoFragmentArgs.fromBundle(requireArguments())
        videoTitle = mView.findViewById(R.id.tv_fullscreen_title)
        playerView = mView.findViewById(R.id.exoplayer_fullscreen)
        videoTitle.text = args.videoTitle
        url = args.videourl


        return mView
    }

    private fun buildMediaSource(uri: Uri): MediaSource? {
        val datasourcefactory: DataSource.Factory = DefaultHttpDataSourceFactory("video")
        return ProgressiveMediaSource.Factory(datasourcefactory)
            .createMediaSource(uri)
    }

    private fun initializeplayer() {
        mPlayer = ExoPlayerFactory.newSimpleInstance(context)
        playerView?.setPlayer(mPlayer)
        val uri = Uri.parse(url)
        val mediaSource = buildMediaSource(uri)
        mPlayer?.setPlayWhenReady(playwhenready)
        mPlayer?.seekTo(currentWindow, playbackposition)
        mPlayer?.prepare(mediaSource, false, false)
    }

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 26) {
            initializeplayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT >= 26 || mPlayer == null) {
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
        if (mPlayer != null) {
            playwhenready = mPlayer?.playWhenReady!!
            playbackposition = mPlayer?.currentPosition!!
            currentWindow = mPlayer?.currentWindowIndex!!
            mPlayer = null
        }
    }


}