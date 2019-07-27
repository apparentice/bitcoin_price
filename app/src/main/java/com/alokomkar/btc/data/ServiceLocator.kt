package com.alokomkar.btc.data

import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.BTCApplication
import com.alokomkar.btc.data.local.AppDatabase
import com.alokomkar.btc.data.local.LocalDataSource
import com.alokomkar.btc.data.remote.RemoteDataSource
import com.alokomkar.btc.data.remote.RetrofitApiProvider
import com.alokomkar.btc.data.remote.service.PriceService
import com.alokomkar.btc.data.remote.service.PriceServiceImpl

class ServiceLocator private constructor (
    private val application : BTCApplication,
    private val appExecutors: AppExecutors
){

    private val appDatabase : AppDatabase by lazy { AppDatabase.getInstance(application, appExecutors) }
    private val retrofitApiProvider : RetrofitApiProvider by lazy { RetrofitApiProvider(application, appExecutors) }
    private val priceService : PriceService by lazy { PriceServiceImpl(retrofitApiProvider.getPriceApiService(), appExecutors) }

    val remoteDataSource : RemoteDataSource by lazy { RemoteDataSource( priceService ) }
    val localDataSource : LocalDataSource by lazy { LocalDataSource( appDatabase ) }

    //Boolean to track if network is connected
    val isNetworkConnected get() = application.isNetworkAvailable

    companion object {
        @Volatile private var instance : ServiceLocator ?= null
        fun getInstance( application: BTCApplication, appExecutors: AppExecutors )
                = instance ?: synchronized(this) {
            instance ?:
            ServiceLocator(application, appExecutors).also {
                instance = it
            }
        }
    }

}