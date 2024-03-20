package com.example.myapplication.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.ProductModel

class ProductAdapter:RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList= emptyList<ProductModel>()
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvProductName = itemView.findViewById<TextView>(R.id.tvProductName)
        val tvProductPrice = itemView.findViewById<TextView>(R.id.tvProductPrice)
        val bDel:Button=itemView.findViewById(R.id.bDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_layout,parent,false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.tvProductName.text = product.productName
        holder.tvProductPrice.text = product.productPrice.toString()
        holder.bDel.setOnClickListener {
            val productToDelete = productList[position]
            productList = productList.minus(productToDelete)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:List<ProductModel>){
        productList=list
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addProduct(product: ProductModel) {
        productList += product
        notifyDataSetChanged()
    }


}