package com.alokomkar.btc.data.mapper

import com.alokomkar.btc.base.BaseMapper
import com.alokomkar.btc.data.local.CurrentPrice
import com.alokomkar.btc.data.remote.CurrentPriceResponse

class PriceMapper : BaseMapper<CurrentPriceResponse, CurrentPrice> {

    override fun mapToEntity(type: CurrentPriceResponse): CurrentPrice
            = CurrentPrice(1, type.ask, type.bid, type.timestamp)

    override fun mapFromEntity(type: CurrentPrice): CurrentPriceResponse
            = CurrentPriceResponse(ask = type.askingPrice, bid = type.sellingPrice, timestamp = type.lastUpdateTimeStamp)
}