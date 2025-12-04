package com.example.shoesplace.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.shoesplace.R
import com.example.shoesplace.data.ShoesPlaceApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productId = intent.getStringExtra("productId") ?: return
        val titleView = findViewById<TextView>(R.id.product_title)
        val priceView = findViewById<TextView>(R.id.product_price)
        val img = findViewById<ImageView>(R.id.product_image)
        val btnAdd = findViewById<Button>(R.id.btn_add_to_cart)

        CoroutineScope(Dispatchers.Main).launch {
            val product = ShoesPlaceApplication.database.productDao().getById(productId)
            if (product != null) {
                titleView.text = product.name
                priceView.text = "$${product.price}"
                Glide.with(this@ProductDetailActivity).load(product.imageUrl).into(img)
            }
        }

        btnAdd.setOnClickListener {
            // add to cart
            CoroutineScope(Dispatchers.IO).launch {
                ShoesPlaceApplication.database.cartDao().insert(
                    com.example.shoesplace.data.CartItem(
                        id = java.util.UUID.randomUUID().toString(),
                        productId = productId,
                        quantity = 1,
                        status = "active"
                    )
                )
                // After adding, go to cart/checkout screen or show a toast
                runOnUiThread {
                    android.widget.Toast.makeText(this@ProductDetailActivity, "Added to cart", android.widget.Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
