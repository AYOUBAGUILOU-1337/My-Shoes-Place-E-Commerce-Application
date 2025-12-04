package com.example.shoesplace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoesplace.R
import com.example.shoesplace.data.ShoesPlaceApplication
import com.example.shoesplace.ui.adapters.ProductAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WishlistFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_wishlist, container, false)
        recyclerView = view.findViewById(R.id.wishlist_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = ProductAdapter(listOf(), onItemClick = { product ->
            val intent = android.content.Intent(requireContext(), com.example.shoesplace.ui.ProductDetailActivity::class.java)
            intent.putExtra("productId", product.id)
            startActivity(intent)
        })
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            ShoesPlaceApplication.database.wishlistDao().getAll().collect { wishlist ->
                val products = wishlist.mapNotNull { item ->
                    ShoesPlaceApplication.database.productDao().getById(item.productId)
                }
                adapter.setData(products)
            }
        }

        return view
    }
}
