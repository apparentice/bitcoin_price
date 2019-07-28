package com.alokomkar.btc

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.alokomkar.btc.base.BasePagedListAdapter
import com.alokomkar.btc.data.local.entity.PriceHistory

class PriceHistoryPagedListAdapter : BasePagedListAdapter<PriceHistory, PriceHistoryViewHolder>(PRICE_HISTORY_COMPARATOR) {

    private var previousDate = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceHistoryViewHolder
            = PriceHistoryViewHolder(parent)

    override fun onBindViewHolder(holder: PriceHistoryViewHolder, position: Int) {
        val item = getItem(position)
        item?.apply {
            holder.bindData(item, previousDate != item.priceDate)
            if( previousDate != item.priceDate )
                previousDate = item.priceDate
        }
    }

    companion object {
        private val PRICE_HISTORY_COMPARATOR = object : DiffUtil.ItemCallback<PriceHistory>() {
            override fun areItemsTheSame(oldItem: PriceHistory, newItem: PriceHistory): Boolean =
                oldItem.time == newItem.time

            override fun areContentsTheSame(oldItem: PriceHistory, newItem: PriceHistory): Boolean =
                oldItem == newItem
        }
    }
}