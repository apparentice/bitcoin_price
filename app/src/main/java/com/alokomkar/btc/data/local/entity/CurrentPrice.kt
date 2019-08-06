package com.alokomkar.btc.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CurrentPrice")
data class CurrentPrice(@PrimaryKey @ColumnInfo(name ="priceId") @NonNull val id : Int = 1,
                        var askingPrice : Double,
                        var sellingPrice : Double,
                        var lastUpdateTimeStamp : Long ) {

    override fun toString(): String {
        return "CurrentPrice(askingPrice=$askingPrice, sellingPrice=$sellingPrice, lastUpdateTimeStamp=$lastUpdateTimeStamp)"
    }
}