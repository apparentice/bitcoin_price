package com.alokomkar.btc

import android.view.ViewGroup
import com.alokomkar.btc.base.BaseRecyclerViewAdapter

class PriceDateAdapter : BaseRecyclerViewAdapter<String, PriceDateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceDateViewHolder
            = PriceDateViewHolder(parent)

    override fun onBindViewHolder(holder: PriceDateViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(itemsList[position])
    }
}