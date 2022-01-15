package com.example.trevelplannerkelana.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trevelplannerkelana.R
import com.example.trevelplannerkelana.data.Location
import com.example.trevelplannerkelana.databinding.ItemLokasiBinding


class LocationAdapter(val ArrLocation: ArrayList<Location>): RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    class ViewHolder(val binding: ItemLokasiBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = ItemLokasiBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            Glide.with(mContext).load(ArrLocation[position].img).into(ivLocation)
            tvNameLocation.text = ArrLocation[position].name

            holder.itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("sublocation", ArrLocation[position].name)
                holder.itemView.findNavController().navigate(R.id.action_homeFragment_to_subLocationFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return ArrLocation.size
    }

}
