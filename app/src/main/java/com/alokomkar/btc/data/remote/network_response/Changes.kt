package com.alokomkar.btc.data.remote.network_response

import com.google.gson.annotations.SerializedName

data class Changes(
    @SerializedName("percent")
    var percent: Percent = Percent(),
    @SerializedName("price")
    var price: Price = Price()
)