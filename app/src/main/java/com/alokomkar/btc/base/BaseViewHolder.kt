package com.alokomkar.btc.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(viewGroup: ViewGroup, layoutId : Int)
    : RecyclerView.ViewHolder( LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)) {

    var itemClickListener: ((View) -> Unit)? = null

    init {
        itemView.setOnClickListener { itemClickListener?.invoke(it) }
    }

    abstract fun bindData( item : T )

}