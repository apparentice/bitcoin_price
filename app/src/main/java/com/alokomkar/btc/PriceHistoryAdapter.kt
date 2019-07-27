package com.alokomkar.btc

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.alokomkar.btc.base.BaseRecyclerViewAdapter
import com.alokomkar.btc.data.local.entity.PriceHistory

class PriceHistoryAdapter : BaseRecyclerViewAdapter<PriceHistory, PriceHistoryViewHolder>(){

    private var previousDate = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceHistoryViewHolder
        = PriceHistoryViewHolder(parent)

    override fun onBindViewHolder(holder: PriceHistoryViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = itemsList[position]
        holder.bindData(item, previousDate != item.priceDate )

        if( previousDate != item.priceDate )
            previousDate = item.priceDate
    }

    fun notifyChanges( newList : List<PriceHistory> ) {
        val diffCallBack = DiffUtil.calculateDiff( object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = itemsList[oldItemPosition].time == newList[newItemPosition].time

            override fun getOldListSize(): Int
                = itemsList.size

            override fun getNewListSize(): Int
                    = newList.size

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
                    = itemsList[oldItemPosition] == newList[newItemPosition]

        })
        diffCallBack.dispatchUpdatesTo(this)
    }
}