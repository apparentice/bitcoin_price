package com.alokomkar.btc.data.remote.network_response
import com.google.gson.annotations.SerializedName


data class CurrentPriceResponse(
    @SerializedName("open")
    var openOn: Open = Open(),
    @SerializedName("ask")
    var ask: Double = 0.0,
    @SerializedName("averages")
    var averages: Averages = Averages(),
    @SerializedName("bid")
    var bid: Double = 0.0,
    @SerializedName("changes")
    var changes: Changes = Changes(),
    @SerializedName("display_symbol")
    var displaySymbol: String = "",
    @SerializedName("display_timestamp")
    var displayTimestamp: String = "",
    @SerializedName("high")
    var high: Double = 0.0,
    @SerializedName("last")
    var last: Double = 0.0,
    @SerializedName("low")
    var low: Double = 0.0,
    @SerializedName("timestamp")
    var timestamp: Long = 0,
    @SerializedName("volume")
    var volume: Double = 0.0,
    @SerializedName("volume_percent")
    var volumePercent: Double = 0.0
)

