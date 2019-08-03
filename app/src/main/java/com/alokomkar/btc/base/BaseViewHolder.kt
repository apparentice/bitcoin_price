package com.alokomkar.btc.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    viewGroup: ViewGroup,
    layoutId : Int,
    onAdapterItemClickListener : OnAdapterItemClickListener ?= null )
    : RecyclerView.ViewHolder( LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)) {

    init {
        itemView.setOnClickListener {
            val itemPosition = adapterPosition
            if( itemPosition != RecyclerView.NO_POSITION )
                onAdapterItemClickListener?.onItemClick(it, itemPosition)
        }
    }
    abstract fun bindData( item : T )

}