package com.alokomkar.btc

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.alokomkar.btc.data.ServiceLocator
import com.alokomkar.btc.data.repository.BTCRepository
import com.alokomkar.btc.data.repository.BTCRepositoryImpl

class BTCApplication : Application() {

    private val appExecutors : AppExecutors by lazy { AppExecutors() }
    // Not required to have instance creation as
    // lazy returns same instance on subsequent access - rectify post testing
    private val serviceLocator : ServiceLocator by lazy { ServiceLocator.getInstance(this, appExecutors) }

    val btcRepository : BTCRepository by lazy { BTCRepositoryImpl(serviceLocator, appExecutors) }

    fun isNetworkAvailable() : Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}