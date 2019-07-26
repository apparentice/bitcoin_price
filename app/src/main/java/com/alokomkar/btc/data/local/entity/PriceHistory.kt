package com.alokomkar.btc.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "PriceHistory")
data class PriceHistory(
    @SerializedName("average")
    var average: Double = 0.0,
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "timeStamp")
    @SerializedName("time")
    var time: String = ""
)