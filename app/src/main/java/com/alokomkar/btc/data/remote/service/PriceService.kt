package com.alokomkar.btc.data.remote.service

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.remote.ApiResponse

interface PriceService {
    fun getCurrentPrice() : LiveData<ApiResponse<CurrentPrice>>
    fun getPriceHistory() : LiveData<ApiResponse<List<PriceHistory>>>
    fun dispose()
}