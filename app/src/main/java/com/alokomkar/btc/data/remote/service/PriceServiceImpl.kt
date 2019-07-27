package com.alokomkar.btc.data.remote.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.mapper.PriceMapper
import com.alokomkar.btc.data.remote.api.PriceApi
import com.alokomkar.btc.data.remote.network_response.CurrentPriceResponse
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PriceServiceImpl( private val priceApi: PriceApi, private val appExecutors: AppExecutors ) : PriceService {

    private val mapper : PriceMapper by lazy { PriceMapper() }
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun getCurrentPrice(): LiveData<CurrentPrice> {

        val priceLiveData : MutableLiveData<CurrentPrice> = MutableLiveData()
        priceLiveData.value = null

        priceApi.getCurrentPrice()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.io())
            .subscribe(object : SingleObserver<CurrentPriceResponse> {

                override fun onSuccess(t: CurrentPriceResponse) {
                    priceLiveData.value = mapper.mapToEntity(t)
                }

                override fun onSubscribe(d: Disposable) {
                    compositeDisposable.add(d)
                }

                override fun onError(e: Throwable) {
                    priceLiveData.value = null
                }

            })

        return priceLiveData
    }

    override fun getPriceHistory(): LiveData<ArrayList<PriceHistory>> {

        val priceHistoryLiveData = MutableLiveData<ArrayList<PriceHistory>>()

        priceApi.getPriceHistory()
            .subscribeOn(Schedulers.from(appExecutors.networkIO()))
            .blockingSubscribe(
                {
                    if( priceHistoryLiveData.value == null ) {
                        priceHistoryLiveData.value = arrayListOf()
                    }
                    priceHistoryLiveData.value?.apply { addAll(it) }
                },
                {

                },
                1000)

        return priceHistoryLiveData
    }
}