package com.alokomkar.btc

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alokomkar.btc.base.BaseViewModel
import com.alokomkar.btc.data.Resource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.repository.BTCRepository

class PriceViewModel( application: Application ) : BaseViewModel(application) {

    private val repository : BTCRepository = btcApplication.btcRepository

    val currentPriceLiveData : LiveData<Resource<CurrentPrice>> = MutableLiveData()
    val priceHistoryLiveData : LiveData<Resource<List<PriceHistory>>> = MutableLiveData()

    fun getCurrentPrice() {
        currentPriceLiveData as MutableLiveData
        currentPriceLiveData.value = repository.getCurrentPrice().value
    }
    fun getPriceHistory( fetchOffline : Boolean ) {
        priceHistoryLiveData as MutableLiveData
        priceHistoryLiveData.value = repository.getPriceHistory(fetchOffline).value
    }

}