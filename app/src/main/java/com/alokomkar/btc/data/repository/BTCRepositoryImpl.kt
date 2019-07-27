package com.alokomkar.btc.data.repository

import androidx.lifecycle.LiveData
import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.data.Resource
import com.alokomkar.btc.data.ServiceLocator
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.remote.ApiResponse

class BTCRepositoryImpl(
    private val serviceLocator: ServiceLocator,
    private val appExecutors: AppExecutors ) : BTCRepository {

    override fun getCurrentPrice(): LiveData<Resource<CurrentPrice>> {
        return object : NetworkBoundResource<CurrentPrice, CurrentPrice>(appExecutors) {
            override fun saveCallResultToLocalDb(item: CurrentPrice) {

            }

            override fun createNetworkCall(): LiveData<ApiResponse<CurrentPrice>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromLocalDatabase(): LiveData<CurrentPrice> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: CurrentPrice?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.asLiveData()
    }

    override fun getPriceHistory(): LiveData<Resource<ArrayList<PriceHistory>>> {
        return object : NetworkBoundResource<ArrayList<PriceHistory>, ArrayList<PriceHistory>>(appExecutors) {
            override fun saveCallResultToLocalDb(item: ArrayList<PriceHistory>) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun createNetworkCall(): LiveData<ApiResponse<ArrayList<PriceHistory>>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun loadFromLocalDatabase(): LiveData<ArrayList<PriceHistory>> {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun shouldFetch(data: ArrayList<PriceHistory>?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }.asLiveData()
    }


}