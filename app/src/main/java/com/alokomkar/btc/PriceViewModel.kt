package com.alokomkar.btc

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.alokomkar.btc.base.BaseViewModel
import com.alokomkar.btc.data.Resource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.repository.BTCRepository
import com.alokomkar.btc.util.AbsentLiveData

class PriceViewModel( application: Application ) : BaseViewModel(application) {

    private val repository : BTCRepository = btcApplication.btcRepository

    private val currentPriceMutableData : MutableLiveData<Resource<CurrentPrice>> = MutableLiveData()
    val currentPriceLiveData : LiveData<Resource<CurrentPrice>>
            = Transformations.switchMap(currentPriceMutableData) { currentPrice ->
        if( currentPrice == null ) AbsentLiveData.create()
        else repository.getCurrentPrice()
    }

    private val priceHistoryMutableLiveData : MutableLiveData<Resource<List<PriceHistory>>> = MutableLiveData()
    val priceHistoryLiveData : LiveData<Resource<List<PriceHistory>>>
            = Transformations.switchMap(priceHistoryMutableLiveData) { priceHistoryList ->
        if( priceHistoryList == null ) AbsentLiveData.create()
        else repository.getPriceHistory()
    }

    fun getCurrentPrice() {
        currentPriceMutableData.value = repository.getCurrentPrice().value
    }
    fun getPriceHistory() {
        priceHistoryMutableLiveData.value = repository.getPriceHistory().value
    }
}