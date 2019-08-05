package com.alokomkar.btc.data.repository

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.Resource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory

interface BTCRepository {
    fun getCurrentPrice() : LiveData<Resource<CurrentPrice>>
    fun getPriceHistory() : LiveData<Resource<List<PriceHistory>>>
    fun dispose()
}