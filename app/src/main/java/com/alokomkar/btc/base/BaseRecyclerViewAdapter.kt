package com.alokomkar.btc.base

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<T>> : RecyclerView.Adapter<VH>() {

    var onAdapterItemClickListener : OnAdapterItemClickListener ?= null
    var onEmptyOrNot: ((isEmpty: Boolean) -> Unit)? = null
    var onReadyToLoadMore: (() -> Unit)? = null

    protected var itemsList: MutableList<T> = mutableListOf()

    fun getItemAtPosition( position: Int ) : T = itemsList[position]

    fun addAll(list: List<T>) {
        val startPosition = itemsList.size
        itemsList.addAll(startPosition, list)
        notifyItemRangeInserted(startPosition, list.size)
        onEmptyOrNot?.invoke(itemsList.isEmpty())
    }

    fun add(item: T) {
        itemsList.add(item)
        notifyItemInserted(itemsList.size - 1)
        onEmptyOrNot?.invoke(itemsList.isEmpty())
    }

    fun addUnique(item : T) {
        if( !itemsList.contains(item) ) {
            itemsList.add(item)
            notifyItemInserted(itemsList.size - 1)
        }
        onEmptyOrNot?.invoke(itemsList.isEmpty())
    }

    fun addAllUnique(list: List<T>) {
        if( itemsList.isEmpty() ) {
            addAll(list)
        }
        else {
            //val startPosition = itemsList.size
            list.iterator().forEach { t ->
                if( !itemsList.contains(t) ) {
                    itemsList.add(t)
                }
            }
            notifyDataSetChanged()
            onEmptyOrNot?.invoke(itemsList.isEmpty())
        }
    }

    fun removeAll() {
        itemsList.clear()
        notifyDataSetChanged()
        onEmptyOrNot?.invoke(itemsList.isEmpty())
    }

    fun remove(item: T) {
        val position = itemsList.indexOf(item)
        itemsList.removeAt(position)
        notifyItemRemoved(position)
        onEmptyOrNot?.invoke(itemsList.isEmpty())
    }

    @CallSuper
    override fun onBindViewHolder(holder: VH, position: Int) {
        onReadyToLoadMore?.let {
            if (position == itemCount - 2)
                it.invoke()
        }
    }

    override fun getItemCount(): Int = itemsList.size
}