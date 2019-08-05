package com.alokomkar.btc

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.alokomkar.btc.data.ServiceLocator
import com.alokomkar.btc.data.repository.BTCRepository
import com.alokomkar.btc.data.repository.BTCRepositoryImpl

class BTCApplication : Application() {

    private val appExecutors : AppExecutors by lazy { AppExecutors() }
    private val serviceLocator : ServiceLocator by lazy { ServiceLocator(this, appExecutors) }

    val btcRepository : BTCRepository by lazy {
        BTCRepositoryImpl(
            serviceLocator.localDataSource,
            serviceLocator.remoteDataSource,
            serviceLocator.sharedPreferenceSource,
            appExecutors
        )
    }

}