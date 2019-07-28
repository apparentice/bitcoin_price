package com.alokomkar.btc

import android.view.ViewGroup
import com.alokomkar.btc.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_date.view.*

class PriceDateViewHolder( viewGroup: ViewGroup)
    : BaseViewHolder<PriceIndexedData>(
    viewGroup,
    R.layout.item_date
) {

    override fun bindData(item: PriceIndexedData) {
        itemView.tvHeading.text = item.priceTimeStamp
    }
}