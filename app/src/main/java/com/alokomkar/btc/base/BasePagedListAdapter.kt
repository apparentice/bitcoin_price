package com.alokomkar.btc.base

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class BasePagedListAdapter<T, VH : BaseViewHolder<T>>(callback: DiffUtil.ItemCallback<T>)
    : PagedListAdapter<T, VH>(callback)