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

class OrdersFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_orders, container, false)
        val rv = view.findViewById<RecyclerView>(R.id.orders_recycler)
        rv.layoutManager = LinearLayoutManager(requireContext())
        val productAdapter = ProductAdapter(listOf(), onItemClick = { product ->
            val intent = android.content.Intent(requireContext(), com.example.shoesplace.ui.ProductDetailActivity::class.java)
            intent.putExtra("productId", product.id)
            startActivity(intent)
        })
        rv.adapter = productAdapter

        lifecycleScope.launch {
            ShoesPlaceApplication.database.productDao().getAll().collect { list ->
                // in place of orders, we simply show products to click on for demo
                productAdapter.setData(list)
            }
        }

        return view
    }
}
