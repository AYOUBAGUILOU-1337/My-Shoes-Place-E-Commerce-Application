package com.example.shoesplace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.shoesplace.R
import com.example.shoesplace.data.ShoesPlaceApplication
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WalletFragment : Fragment() {
    private lateinit var balanceView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wallet, container, false)
        balanceView = view.findViewById(R.id.wallet_balance)

        lifecycleScope.launch {
            ShoesPlaceApplication.database.userDao().getUser().collect { user ->
                val balance = user?.walletBalance ?: 500.0
                balanceView.text = "$${"%.2f".format(balance)}"
            }
        }

        return view
    }
}
