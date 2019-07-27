package com.alokomkar.btc.data

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory

interface DataSource {
    fun getCurrentPrice() : LiveData<CurrentPrice>
    fun getPriceHistory() : LiveData<List<PriceHistory>>
}