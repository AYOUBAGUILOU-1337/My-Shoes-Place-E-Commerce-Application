package com.example.shoesplace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.shoesplace.R
import com.example.shoesplace.data.ShoesPlaceApplication
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import android.widget.TextView

class ProfileFragment : Fragment() {
    private lateinit var nameView: TextView
    private lateinit var phoneView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        nameView = view.findViewById(R.id.profile_name)
        phoneView = view.findViewById(R.id.profile_phone)

        lifecycleScope.launch {
            ShoesPlaceApplication.database.userDao().getUser().collect { user ->
                nameView.text = user?.fullName ?: "Ayoub Aguilou"
                phoneView.text = user?.phone ?: "095-575-000-123"
            }
        }

        return view
    }
}
