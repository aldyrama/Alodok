package com.alodokter.basic_mvvm.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alodokter.basic_mvvm.R
import com.alodokter.basic_mvvm.activity.HomeDetailActivity
import com.alodokter.basic_mvvm.model.ProductModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_two_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private val list = ArrayList<ProductModel>()

    fun setHomeList(list: List<ProductModel>) {
        this.list.clear()
        this.list.addAll(list)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(position: Int){
            val data = list[adapterPosition]
            itemView.apply {
                if (!data.image.isNullOrBlank()) {
                    Picasso.get().load(data.image).into(img_category_news)
                }

                cardView_category.setOnClickListener {
                    cardView_category.context.startActivity(
                        Intent(cardView_category.context, HomeDetailActivity::class.java)
                            .putExtra("data", Gson().toJson(data, ProductModel::class.java))
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_two_item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(position)
    }
}