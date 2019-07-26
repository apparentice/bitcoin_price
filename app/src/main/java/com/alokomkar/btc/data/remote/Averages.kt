package com.alokomkar.btc.data.remote

import com.google.gson.annotations.SerializedName

data class Averages(
    @SerializedName("day")
    var day: Double = 0.0,
    @SerializedName("month")
    var month: Double = 0.0,
    @SerializedName("week")
    var week: Double = 0.0
)