package com.alokomkar.btc.data.remote.api

import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.remote.network_response.CurrentPriceResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface PriceApi {

    @GET("ticker/BTCUSD")
    fun getCurrentPrice() : Flowable<CurrentPriceResponse>

    @GET("history/BTCUSD?period=daily&?format=json")
    fun getPriceHistory() : Flowable<List<PriceHistory>>
}