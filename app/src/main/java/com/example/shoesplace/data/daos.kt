package com.example.shoesplace.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM oproducts ORDER BY oname")
    fun getAll(): Flow<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<Product>)

    @Query("SELECT * FROM oproducts WHERE oid = :id LIMIT 1")
    suspend fun getById(id: String): Product?
}

@Dao
interface WishlistDao {
    @Query("SELECT * FROM owishlist")
    fun getAll(): Flow<List<WishlistItem>>

    @Insert
    suspend fun insert(wishlistItem: WishlistItem)

    @Delete
    suspend fun delete(wishlistItem: WishlistItem)

    @Query("DELETE FROM owishlist WHERE oid = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM owishlist WHERE oproduct_id = :productId LIMIT 1")
    suspend fun findByProductId(productId: String): WishlistItem?
}

@Dao
interface CartDao {
    @Query("SELECT * FROM ocart_items")
    fun getAll(): Flow<List<CartItem>>

    @Insert
    suspend fun insert(item: CartItem)

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)
}

@Dao
interface OrderDao {
    @Query("SELECT * FROM oorders")
    fun getAll(): Flow<List<Order>>

    @Insert
    suspend fun insert(order: Order)

    @Update
    suspend fun update(order: Order)
}

@Dao
interface UserDao {
    @Query("SELECT * FROM ousers LIMIT 1")
    fun getUser(): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)
}

@Dao
interface TransactionDao {
    @Query("SELECT * FROM otransactions")
    fun getAll(): Flow<List<com.example.shoesplace.data.Transaction>>

    @Insert
    suspend fun insert(tx: com.example.shoesplace.data.Transaction)
}
