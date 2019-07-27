package com.alokomkar.btc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.alokomkar.btc.base.BaseDao
import com.alokomkar.btc.data.local.entity.CurrentPrice

@Dao
abstract class CurrentPriceDao : BaseDao<CurrentPrice> {

    @Query("SELECT * FROM CurrentPrice WHERE priceId = :priceId")
    abstract fun fetchCurrentPrice( priceId : Int ) : LiveData<CurrentPrice>

}