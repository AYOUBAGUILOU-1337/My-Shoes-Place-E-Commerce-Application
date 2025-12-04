package com.example.shoesplace.data

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.BufferedReader
import java.io.InputStreamReader
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Product::class, WishlistItem::class, CartItem::class, Order::class, User::class, Transaction::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun wishlistDao(): WishlistDao
    abstract fun cartDao(): CartDao
    abstract fun orderDao(): OrderDao
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shoesplace_database"
                ).fallbackToDestructiveMigration().addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        try {
                            val input = context.assets.open("my_app.sql")
                            var sql = input.bufferedReader().use { it.readText() }
                            sql = sql.replace("CREATE TABLE ", "CREATE TABLE IF NOT EXISTS ")
                            // execute individual statements
                            sql.split(";").map { it.trim() }.filter { it.isNotEmpty() }.forEach { stmt ->
                                db.execSQL(stmt)
                            }
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                        }
                    }
                }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
