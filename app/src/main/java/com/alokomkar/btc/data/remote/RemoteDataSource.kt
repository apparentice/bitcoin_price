package com.alokomkar.btc.data.remote

import androidx.lifecycle.LiveData
import com.alokomkar.btc.data.DataSource
import com.alokomkar.btc.data.local.entity.CurrentPrice
import com.alokomkar.btc.data.local.entity.PriceHistory
import com.alokomkar.btc.data.remote.service.PriceService

class RemoteDataSource( private val priceService: PriceService ) : DataSource {
    override fun getCurrentPrice(): LiveData<CurrentPrice> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPriceHistory(): LiveData<ArrayList<PriceHistory>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}