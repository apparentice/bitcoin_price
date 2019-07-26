package com.alokomkar.btc.data

import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.BTCApplication
import com.alokomkar.btc.data.local.AppDatabase
import com.alokomkar.btc.data.local.LocalDataSource
import com.alokomkar.btc.data.remote.RemoteDataSource
import com.alokomkar.btc.data.remote.RemoteServiceProvider

class ServiceLocator private constructor (
    private val application : BTCApplication,
    private val appExecutors: AppExecutors
){

    private val appDatabase : AppDatabase by lazy { AppDatabase.getInstance(application, appExecutors) }
    private val remoteServiceProvider : RemoteServiceProvider by lazy { RemoteServiceProvider(application, appExecutors) }

    val remoteDataSource : RemoteDataSource by lazy { RemoteDataSource( remoteServiceProvider ) }
    val localDataSource : LocalDataSource by lazy { LocalDataSource( appDatabase ) }

    //Boolean to track if network is connected
    val isNetworkConnected = true

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