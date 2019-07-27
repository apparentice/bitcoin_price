package com.alokomkar.btc

import android.view.ViewGroup
import com.alokomkar.btc.base.BaseViewHolder
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.extension.changeVisibility
import kotlinx.android.synthetic.main.item_price_history.view.*

class PriceHistoryViewHolder( viewGroup: ViewGroup )
    : BaseViewHolder<PriceHistory>(
    viewGroup,
    R.layout.item_price_history
) {

    fun bindData( item : PriceHistory, isHeaderDisplayed : Boolean ) {
        itemView.cvDate.changeVisibility(isHeaderDisplayed)
        bindData(item)
    }

    override fun bindData(item: PriceHistory) {
        itemView.tvDate.text = item.priceDate
        itemView.tvCurrentPrice.text = "$ ${item.average}"
    }
}