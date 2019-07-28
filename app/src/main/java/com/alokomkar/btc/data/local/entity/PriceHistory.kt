package com.alokomkar.btc.data.local.entity

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {

    fun getPriceDate() = time.split(":")[0] + ":00"

    @Ignore
    var header : String = ""

    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readString() ?: ""
    ) {
        header = parcel.readString() ?: ""
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(average)
        parcel.writeString(time)
        parcel.writeString(header)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PriceHistory

        if (time != other.time) return false

        return true
    }

    override fun hashCode(): Int {
        return time.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<PriceHistory> {
        override fun createFromParcel(parcel: Parcel): PriceHistory {
            return PriceHistory(parcel)
        }

        override fun newArray(size: Int): Array<PriceHistory?> {
            return arrayOfNulls(size)
        }
    }


}