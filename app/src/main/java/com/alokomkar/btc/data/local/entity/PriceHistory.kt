package com.alokomkar.btc.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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
) {
    @Ignore
    var priceDate = time.split(":")[0] + ":00"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PriceHistory

        if (average != other.average) return false
        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        var result = average.hashCode()
        result = 31 * result + time.hashCode()
        return result
    }


}