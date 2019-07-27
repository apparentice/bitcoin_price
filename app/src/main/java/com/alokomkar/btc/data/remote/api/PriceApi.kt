package com.alokomkar.btc.data.remote.api

import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.remote.network_response.CurrentPriceResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface PriceApi {

    @GET
    fun getCurrentPrice() : Single<CurrentPriceResponse>

    @GET
    fun getPriceHistory() : Flowable<List<PriceHistory>>
}