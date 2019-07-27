package com.alokomkar.btc.data.remote

import com.alokomkar.btc.AppExecutors
import com.alokomkar.btc.BTCApplication
import com.alokomkar.btc.BuildConfig
import com.alokomkar.btc.data.remote.api.PriceApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiProvider(private val application: BTCApplication, private val appExecutors: AppExecutors ) {

    private val BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/history/"
    private val retrofit : Retrofit by lazy { makeRetrofit(
        makeOkHttpClient(makeLoggingInterceptor(
            BuildConfig.DEBUG
        )), makeGson()) }

    fun getPriceApiService() : PriceApi
            = retrofit.create(PriceApi::class.java)


    private fun makeRetrofit( client: OkHttpClient,
                              gson: Gson ) : Retrofit
            = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()


    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient
            = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .build()


    private fun makeGson(): Gson
            = GsonBuilder()
        .setLenient()
        .create()


    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor
            = HttpLoggingInterceptor().apply{
        level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }


}