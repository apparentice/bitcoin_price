package com.alokomkar.btc

import android.view.ViewGroup
import com.alokomkar.btc.base.BaseRecyclerViewAdapter
import com.alokomkar.btc.base.OnAdapterItemClickListener

class PriceDateAdapter : BaseRecyclerViewAdapter<PriceIndexedData, PriceDateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceDateViewHolder
            = PriceDateViewHolder(parent, onAdapterItemClickListener)

    override fun onBindViewHolder(holder: PriceDateViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bindData(itemsList[position])
    }

}