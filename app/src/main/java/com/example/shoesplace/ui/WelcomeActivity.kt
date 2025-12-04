package com.example.shoesplace.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.shoesplace.R
import com.example.shoesplace.data.Repository

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // Seed sample items once
        val repo = Repository(com.example.shoesplace.ShoesPlaceApplication.database)
        repo.seedSampleProducts()

        findViewById<android.view.View>(R.id.btn_register).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        findViewById<android.view.View>(R.id.btn_login).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
