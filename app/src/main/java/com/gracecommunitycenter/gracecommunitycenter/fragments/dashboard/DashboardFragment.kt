package com.gracecommunitycenter.gracecommunitycenter.fragments.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.gracecommunitycenter.gracecommunitycenter.R
import kotlin.system.exitProcess

class DashboardFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var videoCard: CardView
    private lateinit var logout: Button
    private lateinit var AnnouncementsCard: CardView
    private lateinit var EmaterialsCard: CardView
    private lateinit var SongCard: CardView
    private lateinit var sermonCard: CardView
    private lateinit var settingsCard: CardView
    private val TAG = "DashboardFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
//                Firebase.auth.signOut()
                android.os.Process.killProcess(android.os.Process.myPid());
                exitProcess(1);
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_dashboard, container, false)
        videoCard = mView.findViewById(R.id.videosCard)
        AnnouncementsCard = mView.findViewById(R.id.AnnouncementsCard)
        EmaterialsCard = mView.findViewById(R.id.E_materialsCard)
        SongCard = mView.findViewById(R.id.SongCard)
        sermonCard = mView.findViewById(R.id.sermonCard)
        settingsCard = mView.findViewById(R.id.settingsCard)
        logout = mView.findViewById<Button>(R.id.logout)

        videoCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_videosFragment)
        }
        AnnouncementsCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_announcementsFragment)
        }
        EmaterialsCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_e_materialsFragment)
        }

        SongCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_songsFragment)
        }

        sermonCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_sermonsFragment)
        }

        settingsCard.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
        }

        logout.setOnClickListener {
            Firebase.auth.signOut()
            findNavController().navigate(R.id.action_dashboardFragment_to_authLoginFragment2)
        }

        return mView
    }

}