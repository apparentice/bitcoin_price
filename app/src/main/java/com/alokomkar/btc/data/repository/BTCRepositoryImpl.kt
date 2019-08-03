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
        return object : NetworkBoundResource<CurrentPrice, CurrentPrice>(appExecutors, serviceLocator.sharedPreferenceSource) {
            override fun saveCallResultToLocalDb(item: CurrentPrice)
                    = serviceLocator.localDataSource.insertPrice(item)

            override fun createNetworkCall(): LiveData<ApiResponse<CurrentPrice>>
                    = serviceLocator.remoteDataSource.getCurrentPrice()

            override fun loadFromLocalDatabase(): LiveData<CurrentPrice>
                    = serviceLocator.localDataSource.getCurrentPrice()

            override fun shouldFetch(data: CurrentPrice?): Boolean
                    = data == null || serviceLocator.sharedPreferenceSource.isCacheExpired()

        }.asLiveData()
    }

    override fun getPriceHistory(): LiveData<Resource<List<PriceHistory>>> {
        return object : NetworkBoundResource<List<PriceHistory>, List<PriceHistory>>(appExecutors, serviceLocator.sharedPreferenceSource) {
            override fun saveCallResultToLocalDb(item: List<PriceHistory>)
                    = serviceLocator.localDataSource.insertAllPriceHistory(item)

            override fun createNetworkCall(): LiveData<ApiResponse<List<PriceHistory>>>
                    = serviceLocator.remoteDataSource.getPriceHistory()

            override fun loadFromLocalDatabase(): LiveData<List<PriceHistory>>
                    = serviceLocator.localDataSource.getPriceHistory()

            override fun shouldFetch(data: List<PriceHistory>?): Boolean
                    = (data == null || serviceLocator.sharedPreferenceSource.isCacheExpired())

        }.asLiveData()
    }


}