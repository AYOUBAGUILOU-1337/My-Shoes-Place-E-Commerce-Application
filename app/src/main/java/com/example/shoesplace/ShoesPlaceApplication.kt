package com.example.shoesplace

import android.app.Application
import com.example.shoesplace.data.AppDatabase

class ShoesPlaceApplication : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
    }
}
