package com.alokomkar.btc.data.local

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.DataSource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory

class LocalDataSource( private val appDatabase: AppDatabase ) :  DataSource {

    fun insertPrice( price: CurrentPrice )
            = appDatabase.currentPriceDao().insert(price)

    fun insertAllPriceHistory( priceHistoryList : List<PriceHistory> )
    {
        appDatabase.runInTransaction {
            priceHistoryList.forEach {  appDatabase.priceHistoryDao().insert(it)  }
        }
    }

    //Since there will always be only one entry in database
    override fun getCurrentPrice(): LiveData<CurrentPrice>
            = appDatabase.currentPriceDao().fetchCurrentPrice(1)

    override fun getPriceHistory(): LiveData<List<PriceHistory>>
            = appDatabase.priceHistoryDao().fetchAll()
}