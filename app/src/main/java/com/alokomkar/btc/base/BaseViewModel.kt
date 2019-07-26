package com.alokomkar.btc.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.alokomkar.btc.BTCApplication

abstract class BaseViewModel( application: Application ) : AndroidViewModel(application) {
    val btcApplication : BTCApplication = application as BTCApplication
}
