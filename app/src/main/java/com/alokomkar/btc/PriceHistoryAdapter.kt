package com.alokomkar.btc

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.alokomkar.btc.base.BaseRecyclerViewAdapter
import com.alokomkar.btc.data.local.entity.PriceHistory

class PriceHistoryAdapter : BaseRecyclerViewAdapter<PriceHistory, PriceHistoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceHistoryViewHolder
        = PriceHistoryViewHolder(parent)

    override fun onBindViewHolder(holder: PriceHistoryViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = itemsList[position]
        holder.bindData(item, item.header.isNotEmpty() )
    }
}