package com.example.shoesplace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoesplace.R
import com.example.shoesplace.data.ShoesPlaceApplication
import com.example.shoesplace.data.Product
import com.example.shoesplace.ui.adapters.ProductAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private var products = listOf<Product>()
    private var wishlistIds = setOf<String>()
    private var selectedBrand = "All"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.products_recycler)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        adapter = ProductAdapter(listOf(), onItemClick = { product ->
            val intent = android.content.Intent(requireContext(), com.example.shoesplace.ui.ProductDetailActivity::class.java)
            intent.putExtra("productId", product.id)
            startActivity(intent)
        }, onWishlistClick = { product ->
            // basic wishlist toggle using DAO
            lifecycleScope.launch {
                val wish = ShoesPlaceApplication.database.wishlistDao().findByProductId(product.id)
                if (wish != null) {
                    ShoesPlaceApplication.database.wishlistDao().delete(wish)
                } else {
                    ShoesPlaceApplication.database.wishlistDao().insert(
                        com.example.shoesplace.data.WishlistItem(java.util.UUID.randomUUID().toString(), product.id)
                    )
                }
            }
        }, isWishlisted = { p ->
            wishlistIds.contains(p.id)
        })

        recyclerView.adapter = adapter

        val btnAll = view.findViewById<Button>(R.id.btn_brand_all)
        val btnNike = view.findViewById<Button>(R.id.btn_brand_nike)
        val btnAdidas = view.findViewById<Button>(R.id.btn_brand_adidas)
        val btnPuma = view.findViewById<Button>(R.id.btn_brand_puma)

        btnAll.setOnClickListener { selectedBrand = "All"; filterProducts() }
        btnNike.setOnClickListener { selectedBrand = "Nike"; filterProducts() }
        btnAdidas.setOnClickListener { selectedBrand = "Adidas"; filterProducts() }
        btnPuma.setOnClickListener { selectedBrand = "Puma"; filterProducts() }

        loadProducts()
        loadWishlist()
        return view
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            ShoesPlaceApplication.database.productDao().getAll().collect { list ->
                products = list
                filterProducts()
            }
        }
    }

    private fun loadWishlist() {
        lifecycleScope.launch {
            ShoesPlaceApplication.database.wishlistDao().getAll().collect { list ->
                wishlistIds = list.map { it.productId }.toSet()
                // update adapter's UI
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun filterProducts() {
        val filtered = if (selectedBrand == "All") products else products.filter { it.brand == selectedBrand }
        adapter.setData(filtered)
    }
}
