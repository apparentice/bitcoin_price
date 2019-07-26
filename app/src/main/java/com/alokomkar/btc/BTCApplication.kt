package com.alokomkar.btc

import android.app.Application
import com.alokomkar.btc.data.ServiceLocator
import com.alokomkar.btc.data.repository.BTCRepository
import com.alokomkar.btc.data.repository.BTCRepositoryImpl

class BTCApplication : Application() {

    private val appExecutors : AppExecutors by lazy { AppExecutors() }
    private val serviceLocator : ServiceLocator by lazy { ServiceLocator.getInstance(this, appExecutors) }

    val btcRepository : BTCRepository by lazy { BTCRepositoryImpl(serviceLocator, appExecutors) }

}