package com.alokomkar.btc.data.repository

import androidx.lifecycle.LiveData
import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.data.Resource
import com.alokomkar.btc.data.local.LocalDataSource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.local.preferences.SharedPreferenceSource
import com.alokomkar.btc.data.remote.ApiResponse
import com.alokomkar.btc.data.remote.RemoteDataSource

class BTCRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val sharedPreferenceSource: SharedPreferenceSource,
    private val appExecutors: AppExecutors ) : BTCRepository {


    override fun getCurrentPrice(): LiveData<Resource<CurrentPrice>> {
        return object : NetworkBoundResource<CurrentPrice, CurrentPrice>(appExecutors, sharedPreferenceSource) {
            override fun saveCallResultToLocalDb(item: CurrentPrice)
                    = localDataSource.insertPrice(item)

            override fun createNetworkCall(): LiveData<ApiResponse<CurrentPrice>>
                    = remoteDataSource.getCurrentPrice()

            override fun loadFromLocalDatabase(): LiveData<CurrentPrice>
                    = localDataSource.getCurrentPrice()

            override fun shouldFetch(data: CurrentPrice?): Boolean
                    = data == null || sharedPreferenceSource.isCacheExpired()

        }.asLiveData()
    }

    override fun getPriceHistory(): LiveData<Resource<List<PriceHistory>>> {
        return object : NetworkBoundResource<List<PriceHistory>, List<PriceHistory>>(appExecutors, sharedPreferenceSource) {
            override fun saveCallResultToLocalDb(item: List<PriceHistory>)
                    = localDataSource.insertAllPriceHistory(item)

            override fun createNetworkCall(): LiveData<ApiResponse<List<PriceHistory>>>
                    = remoteDataSource.getPriceHistory()

            override fun loadFromLocalDatabase(): LiveData<List<PriceHistory>>
                    = localDataSource.getPriceHistory()

            override fun shouldFetch(data: List<PriceHistory>?): Boolean
                    = (data == null || data.isEmpty() || sharedPreferenceSource.isCacheExpired())

        }.asLiveData()
    }

    override fun dispose() = remoteDataSource.dispose()

}