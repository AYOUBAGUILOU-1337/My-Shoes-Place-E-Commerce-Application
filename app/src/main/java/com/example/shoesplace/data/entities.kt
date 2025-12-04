package com.example.shoesplace.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "oproducts")
data class Product(
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val id: String,
    @ColumnInfo(name = "oname") val name: String,
    @ColumnInfo(name = "obrand") val brand: String,
    @ColumnInfo(name = "oprice") val price: Double,
    @ColumnInfo(name = "oimage_url") val imageUrl: String,
    @ColumnInfo(name = "orating") val rating: Double = 4.9,
    @ColumnInfo(name = "osold_count") val soldCount: Int = 0
)

@Entity(tableName = "owishlist")
data class WishlistItem(
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val id: String,
    @ColumnInfo(name = "oproduct_id") val productId: String
)

@Entity(tableName = "ocart_items")
data class CartItem(
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val id: String,
    @ColumnInfo(name = "oproduct_id") val productId: String,
    @ColumnInfo(name = "oquantity") val quantity: Int = 1,
    @ColumnInfo(name = "ostatus") val status: String = "active"
)

@Entity(tableName = "oorders")
data class Order(
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val id: String,
    @ColumnInfo(name = "oproduct_id") val productId: String,
    @ColumnInfo(name = "ototal_amount") val totalAmount: Double,
    @ColumnInfo(name = "ostatus") val status: String = "paid"
)

@Entity(tableName = "ousers")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val id: String,
    @ColumnInfo(name = "ofull_name") val fullName: String,
    @ColumnInfo(name = "ophone") val phone: String = "",
    @ColumnInfo(name = "owallet_balance") val walletBalance: Double = 0.0
)

@Entity(tableName = "otransactions")
data class Transaction(
    @PrimaryKey
    @ColumnInfo(name = "oid")
    val id: String,
    @ColumnInfo(name = "otype") val type: String,
    @ColumnInfo(name = "oamount") val amount: Double,
    @ColumnInfo(name = "ocreated_at") val createdAt: String
)
