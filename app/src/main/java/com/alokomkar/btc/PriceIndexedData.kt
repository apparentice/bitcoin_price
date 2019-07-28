package com.alokomkar.btc

data class PriceIndexedData( val index : Int, val priceTimeStamp : String ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PriceIndexedData

        if (priceTimeStamp != other.priceTimeStamp) return false

        return true
    }

    override fun hashCode(): Int {
        return priceTimeStamp.hashCode()
    }
}