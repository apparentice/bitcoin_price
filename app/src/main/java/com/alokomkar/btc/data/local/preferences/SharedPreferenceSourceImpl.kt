package com.alokomkar.btc.data.local.preferences

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferenceSourceImpl( private val application: Application ) : SharedPreferenceSource {

    private val sharedPreferences : SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(application)}
    override var lastUpdatedTimeStamp : Long
        get() = sharedPreferences.getLong(PREF_LAST_UPDATED, 0)
        set(value) = sharedPreferences.edit().putLong(PREF_LAST_UPDATED, value).apply()

    companion object{
        private const val PREF_LAST_UPDATED : String = "dataLastUpdated"
    }
}