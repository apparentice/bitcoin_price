package com.alokomkar.btc

import android.app.Application
import com.alokomkar.btc.base.BaseViewModel
import com.alokomkar.btc.data.repository.BTCRepository

class PriceViewModel( application: Application ) : BaseViewModel(application) {

    private val repository : BTCRepository = btcApplication.btcRepository

}