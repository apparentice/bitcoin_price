package com.alokomkar.btc.data.repository

import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.data.ServiceLocator

class BTCRepositoryImpl(
    private val serviceLocator: ServiceLocator,
    private val appExecutors: AppExecutors ) : BTCRepository