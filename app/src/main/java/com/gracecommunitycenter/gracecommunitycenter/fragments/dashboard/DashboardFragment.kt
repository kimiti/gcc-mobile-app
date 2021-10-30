package com.gracecommunitycenter.gracecommunitycenter.fragments.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gracecommunitycenter.gracecommunitycenter.R

class DashboardFragment : Fragment() {

    private lateinit var mView: View
    private val TAG = "DashboardFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_dashboard, container, false)


        val videoCard = mView.findViewById<CardView>(R.id.videosCard)
        val logout = mView.findViewById<Button>(R.id.logout)

        videoCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_videosFragment)
        }

        logout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_dashboardFragment_to_authLoginFragment2)
        }

        return mView
    }

}