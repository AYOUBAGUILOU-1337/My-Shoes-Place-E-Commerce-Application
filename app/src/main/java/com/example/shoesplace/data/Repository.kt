package com.example.shoesplace.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(private val db: AppDatabase) {
    fun seedSampleProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val sample = listOf(
                Product("1", "Nike Dunk Low", "Nike", 120.0, "https://images.unsplash.com/photo-1610824542867-9c1e3ff89d2f?w=800", 4.7, 1200),
                Product("2", "Adidas Ultraboost", "Adidas", 150.0, "https://images.unsplash.com/photo-1528701800489-476a4a73bfb1?w=800", 4.8, 800),
                Product("3", "Puma RS", "Puma", 85.0, "https://images.unsplash.com/photo-1552345386-dc85f8e0f6e1?w=800", 4.6, 420),
                Product("4", "Nike Air Max", "Nike", 130.0, "https://images.unsplash.com/photo-1589820296121-59e2d0d0a7b0?w=800", 4.9, 2000)
            )

            db.productDao().insertAll(sample)
            // create a sample user
            db.userDao().insert(com.example.shoesplace.data.User(id = "u1", fullName = "Ayoub Aguilou", phone = "095-575-000-123", walletBalance = 500.0))
        }
    }
}
