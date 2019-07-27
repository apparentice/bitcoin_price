package com.alokomkar.btc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.alokomkar.btc.base.BaseDao
import com.alokomkar.btc.data.local.entity.PriceHistory

@Dao
abstract class PriceHistoryDao : BaseDao<PriceHistory> {

    @Transaction
    @Query("SELECT * FROM PriceHistory ORDER BY timeStamp DESC" )
    abstract fun fetchAll() : LiveData<List<PriceHistory>>

}