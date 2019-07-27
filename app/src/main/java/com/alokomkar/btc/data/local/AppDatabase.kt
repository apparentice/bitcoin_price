package com.alokomkar.btc.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.R
import com.alokomkar.btc.data.local.dao.CurrentPriceDao
import com.alokomkar.btc.data.local.dao.PriceHistoryDao
import com.alokomkar.btc.data.local.entity.CurrentPrice

@Database( entities = [CurrentPrice::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {

    abstract fun currentPriceDao() : CurrentPriceDao
    abstract fun priceHistoryDao() : PriceHistoryDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(application: Application, appExecutors: AppExecutors): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(application).also { INSTANCE = it }
            }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(application,
                AppDatabase::class.java, application.getString(R.string.local_database_name))
                .build()

    }
}