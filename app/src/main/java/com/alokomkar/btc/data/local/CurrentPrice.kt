package com.alokomkar.btc.data.local

import androidx.room.Entity

@Entity(tableName = "CurrentPrice")
data class CurrentPrice( val id : Int = 1,
                         var askingPrice : Double,
                         var sellingPrice : Double,
                         var lastUpdateTimeStamp : Long )