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

    override fun bindData(item: PriceHistory) {
        itemView.cvDate.changeVisibility(item.header.isBlank())
        itemView.tvDate.text = item.header
        itemView.tvCurrentPrice.text = "$ ${item.average}"
    }
}