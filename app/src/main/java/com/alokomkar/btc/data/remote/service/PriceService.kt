package com.alokomkar.btc.data.remote.service

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory

interface PriceService {
    fun getCurrentPrice() : LiveData<CurrentPrice>
    fun getPriceHistory() : LiveData<ArrayList<PriceHistory>>
}