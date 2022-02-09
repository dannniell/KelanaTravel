package com.example.trevelplannerkelana.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trevelplannerkelana.R
import com.example.trevelplannerkelana.data.SubLocation
import com.example.trevelplannerkelana.databinding.ItemSublokasiBinding

class SubLocationAdapter(val ArrSubLocation: ArrayList<SubLocation>, val collectionData: String): RecyclerView.Adapter<SubLocationAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    class ViewHolder(val binding: ItemSublokasiBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = ItemSublokasiBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(mContext).load(ArrSubLocation[position].img).into(ivSubLocation)
            tvNameSubLocation.text = ArrSubLocation[position].name

            holder.itemView.setOnClickListener{
                val bundle = Bundle()
                //benerin isinya
                bundle.putString("c1", collectionData)
                bundle.putString("d1", ArrSubLocation[position].code)
                bundle.putString("nama", ArrSubLocation[position].name)
                bundle.putString("foto", ArrSubLocation[position].img)
                bundle.putString("peta", ArrSubLocation[position].map)
                holder.itemView.findNavController().navigate(R.id.action_subLocationFragment_to_detailFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return ArrSubLocation.size
    }
}