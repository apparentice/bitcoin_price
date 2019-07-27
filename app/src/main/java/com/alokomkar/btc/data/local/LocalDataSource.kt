package com.alokomkar.btc.data.local

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.DataSource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory

class LocalDataSource( private val appDatabase: AppDatabase ) : DataSource {
    override fun getCurrentPrice(): LiveData<CurrentPrice> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPriceHistory(): LiveData<ArrayList<PriceHistory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}