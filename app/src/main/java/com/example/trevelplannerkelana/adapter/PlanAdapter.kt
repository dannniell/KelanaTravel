package com.example.trevelplannerkelana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trevelplannerkelana.data.Plan

import com.example.trevelplannerkelana.databinding.ItemPlanBinding

class PlanAdapter(val ArrPlan: ArrayList<Plan>): RecyclerView.Adapter<PlanAdapter.ViewHolder>() {
    private lateinit var mContext: Context

    class ViewHolder(val binding: ItemPlanBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = ItemPlanBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return PlanAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding){
            Glide.with(mContext).load(ArrPlan[position].img).into(ivPlanImage)
            tvLocationPlan.text = ArrPlan[position].name
        }
    }

    override fun getItemCount(): Int {
        return ArrPlan.size
    }
}