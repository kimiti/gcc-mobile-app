package com.gracecommunitycenter.gracecommunitycenter.fragments.videos.videosAdapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gracecommunitycenter.gracecommunitycenter.R

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    var VideoTitleView: TextView


    init {
        VideoTitleView = view.findViewById(R.id.VideoTitle)
        view.setOnClickListener {
            mClickListener!!.onItemClick(view, bindingAdapterPosition)
        }

    }

    private var mClickListener: Clicklistener? = null
    interface Clicklistener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnClicklistener(clicklistener: VideoViewHolder.Clicklistener) {
        mClickListener = clicklistener
    }


}