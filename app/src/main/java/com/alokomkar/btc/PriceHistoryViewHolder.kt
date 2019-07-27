package com.alokomkar.btc

import android.view.ViewGroup
import com.alokomkar.btc.base.BaseViewHolder
import com.alokomkar.btc.data.local.entity.PriceHistory

class PriceHistoryViewHolder( viewGroup: ViewGroup )
    : BaseViewHolder<PriceHistory>(
    viewGroup,
    R.layout.item_price_history
) {

    override fun bindData(item: PriceHistory) {

    }
}