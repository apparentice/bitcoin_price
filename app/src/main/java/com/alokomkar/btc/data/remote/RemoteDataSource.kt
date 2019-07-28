package com.alokomkar.btc.data.remote

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.remote.service.PriceService

class RemoteDataSource( private val priceService: PriceService ) : PriceService {

    override fun getCurrentPrice(): LiveData<ApiResponse<CurrentPrice>>
            = priceService.getCurrentPrice()

    override fun getPriceHistory(): LiveData<ApiResponse<List<PriceHistory>>>
            = priceService.getPriceHistory()

    override fun dispose() = priceService.dispose()

}