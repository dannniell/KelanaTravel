package com.example.trevelplannerkelana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trevelplannerkelana.data.DetailImg
import com.example.trevelplannerkelana.databinding.ItemDetailBinding

class DetailImgAdapter(var ArrDetailImg: ArrayList<DetailImg>): RecyclerView.Adapter<DetailImgAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    class ViewHolder(val binding: ItemDetailBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(mContext).load(ArrDetailImg[position].img).into(ivDetailImg)
        }
    }

    override fun getItemCount(): Int {
        return ArrDetailImg.size
    }
}