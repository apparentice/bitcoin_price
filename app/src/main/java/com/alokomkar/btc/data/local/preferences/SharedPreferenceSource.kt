package com.alokomkar.btc.data.local.preferences

interface SharedPreferenceSource {
    var lastUpdatedTimeStamp : Long
    var isCacheExpired : Boolean
}