package com.alokomkar.btc.data.remote

import com.google.gson.annotations.SerializedName

data class Price(
    @SerializedName("day")
    var day: Double = 0.0,
    @SerializedName("hour")
    var hour: Double = 0.0,
    @SerializedName("month")
    var month: Double = 0.0,
    @SerializedName("month_3")
    var month3: Double = 0.0,
    @SerializedName("month_6")
    var month6: Double = 0.0,
    @SerializedName("week")
    var week: Double = 0.0,
    @SerializedName("year")
    var year: Double = 0.0
)