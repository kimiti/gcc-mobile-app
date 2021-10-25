package com.gracecommunitycenter.gracecommunitycenter.fragments.videos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.gracecommunitycenter.gracecommunitycenter.R
import com.gracecommunitycenter.gracecommunitycenter.fragments.videos.videosAdapter.VideoViewHolder
import com.gracecommunitycenter.gracecommunitycenter.models.VideoModel


class VideosFragment() : Fragment() {

    private lateinit var mView: View

    private val FireStoreDB = FirebaseFirestore.getInstance().collection("videos")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_videos, container, false)
        val recyclerView = mView.findViewById<RecyclerView?>(R.id.recyclerview_ShowVideo)



        var adapter: FirestoreRecyclerAdapter<VideoModel, VideoViewHolder>? = null
        var query: Query = FireStoreDB

        val response = FirestoreRecyclerOptions.Builder<VideoModel>()
            .setQuery(query, VideoModel::class.java)
            .build()


        adapter = object : FirestoreRecyclerAdapter<VideoModel, VideoViewHolder>(response) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {

                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.videos_adapter_layout, parent, false)
                return VideoViewHolder(view)

            }

            override fun onBindViewHolder(
                holder: VideoViewHolder,
                position: Int,
                model: VideoModel
            ) {
                holder.VideoTitleView.text = model.videoTitle
            }
        }


        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.startListening()
        recyclerView.adapter = adapter

        return mView
    }

}