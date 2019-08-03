package com.alokomkar.btc

import android.view.ViewGroup
import com.alokomkar.btc.base.BaseViewHolder
import com.alokomkar.btc.base.OnAdapterItemClickListener
import kotlinx.android.synthetic.main.item_date.view.*

class PriceDateViewHolder(
    viewGroup: ViewGroup,
    onAdapterItemClickListener: OnAdapterItemClickListener ?= null
)
    : BaseViewHolder<PriceIndexedData>(
    viewGroup,
    R.layout.item_date,
    onAdapterItemClickListener
) {

    override fun bindData(item: PriceIndexedData) {
        itemView.tvHeading.text = item.priceTimeStamp
    }
}