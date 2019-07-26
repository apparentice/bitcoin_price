package com.alokomkar.btc.base

interface BaseMapper<T, V> {
    fun mapToEntity( type : T ) : V
    fun mapFromEntity( type : V ) : T
}