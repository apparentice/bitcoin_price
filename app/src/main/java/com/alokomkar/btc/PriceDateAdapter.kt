package com.alokomkar.btc

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alokomkar.btc.base.BaseRecyclerViewAdapter

class PriceDateAdapter : BaseRecyclerViewAdapter<PriceIndexedData, PriceDateViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceDateViewHolder
            = PriceDateViewHolder(parent)

    override fun onBindViewHolder(holder: PriceDateViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.apply {
            itemClickListener = {
                val itemPosition = adapterPosition
                if( itemPosition != RecyclerView.NO_POSITION )
                    this@PriceDateAdapter.onItemClickListener?.invoke(it, itemPosition, itemsList[itemPosition])
            }
            bindData(itemsList[position])

        }
    }
}