package com.example.shoesplace.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoesplace.R
import com.example.shoesplace.data.ShoesPlaceApplication
import com.example.shoesplace.ui.adapters.ProductAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CheckoutFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var totalText: TextView
    private lateinit var btnContinue: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_checkout, container, false)
        recyclerView = view.findViewById(R.id.checkout_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        totalText = view.findViewById(R.id.total_amount)
        btnContinue = view.findViewById(R.id.btn_continue_payment)

        adapter = ProductAdapter(listOf(), onItemClick = { product ->
            val intent = android.content.Intent(requireContext(), com.example.shoesplace.ui.ProductDetailActivity::class.java)
            intent.putExtra("productId", product.id)
            startActivity(intent)
        })
        recyclerView.adapter = adapter

        loadCart()

        btnContinue.setOnClickListener {
            val intent = android.content.Intent(requireContext(), com.example.shoesplace.ui.PaymentMethodsActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun loadCart() {
        lifecycleScope.launch {
            ShoesPlaceApplication.database.cartDao().getAll().collect { cartItems ->
                // map to products for visualization; join products
                val products = cartItems.mapNotNull { item ->
                    ShoesPlaceApplication.database.productDao().getById(item.productId)
                }
                adapter.setData(products)

                val total = products.sumOf { it.price }
                totalText.text = "$${"%.2f".format(total)}"
            }
        }
    }
}
