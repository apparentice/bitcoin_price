package com.alokomkar.btc.data.remote.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.mapper.PriceMapper
import com.alokomkar.btc.data.remote.ApiResponse
import com.alokomkar.btc.data.remote.Response
import com.alokomkar.btc.data.remote.api.PriceApi
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PriceServiceImpl( private val priceApi: PriceApi, private val appExecutors: AppExecutors ) : PriceService {

    private val mapper : PriceMapper by lazy { PriceMapper() }
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun getCurrentPrice(): LiveData<ApiResponse<CurrentPrice>> {

        val priceLiveData : MutableLiveData<ApiResponse<CurrentPrice>> = MutableLiveData()
        priceLiveData.value = null

        priceApi.getCurrentPrice()
            .subscribeOn(Schedulers.from(appExecutors.networkIO()))
            .blockingSubscribe({
                priceLiveData.value = ApiResponse.create(Response(null, mapper.mapToEntity(it)))
            }, {
                priceLiveData.value = ApiResponse.create(it)
            }, 1 )
        return priceLiveData
    }

    override fun getPriceHistory(): LiveData<ApiResponse<List<PriceHistory>>> {

        val priceHistoryLiveData = MutableLiveData<ApiResponse<List<PriceHistory>>>()
        val priceHistoryList = ArrayList<PriceHistory>()

        val d = priceApi.getPriceHistory()
            .map { items ->
                var previousHeader = ""
                items.forEach{ item ->
                    if( previousHeader == "" ) {
                        item.header = item.getPriceDate()
                        previousHeader = item.header
                    } else if( previousHeader != item.getPriceDate() ) {
                        item.header = item.getPriceDate()
                        previousHeader = item.header
                    }
                }
                items
            }
            .subscribeOn(Schedulers.from(appExecutors.networkIO()))
            .observeOn(Schedulers.from(appExecutors.mainThread()))
            .subscribe(
                {
                    priceHistoryList.clear()
                    priceHistoryList.addAll(it)
                    priceHistoryLiveData.value = ApiResponse.create(Response(null, priceHistoryList.toList()))
                },
                {
                    priceHistoryLiveData.value = ApiResponse.create(it)
                })
        compositeDisposable.add(d)
        return priceHistoryLiveData
    }

    override fun dispose() {
        compositeDisposable.dispose()
    }
}