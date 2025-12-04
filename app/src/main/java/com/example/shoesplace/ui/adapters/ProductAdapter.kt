package com.example.shoesplace.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shoesplace.R
import com.example.shoesplace.data.Product

class ProductAdapter(
    private var items: List<Product>,
    private val onItemClick: (Product) -> Unit,
    private val onWishlistClick: ((Product) -> Unit)? = null,
    private val isWishlisted: ((Product) -> Boolean)? = null
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.product_title)
        val price: TextView = view.findViewById(R.id.product_price)
        val image: ImageView = view.findViewById(R.id.product_image)
        val wishlist: ImageView = view.findViewById(R.id.product_wishlist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_card, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.title.text = p.name
        holder.price.text = "$${p.price}"

        Glide.with(holder.image.context).load(p.imageUrl).centerCrop().into(holder.image)

        holder.itemView.setOnClickListener { onItemClick(p) }

        // wishlist
        holder.wishlist.setOnClickListener { onWishlistClick?.invoke(p) }
        val wishlisted = isWishlisted?.invoke(p) ?: false
        holder.wishlist.setImageResource(if (wishlisted) R.drawable.ic_favorite else R.drawable.ic_favorite)
    }

    override fun getItemCount(): Int = items.size

    fun setData(products: List<Product>) {
        this.items = products
        notifyDataSetChanged()
    }
}
