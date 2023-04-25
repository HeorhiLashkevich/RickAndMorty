package com.example.rickandmorty.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://rickandmortyapi.com/api/"

object NetworkController {

    fun getRickAndMortyApi(): RickAndMortyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create((RickAndMortyApi::class.java))
    }
//    fun getRickAndMortyApiWithRx(): RickAndMortyApi {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(getOkHttpClient())
//
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        return retrofit.create((RickAndMortyApi::class.java))
//    }


    private fun getOkHttpClient() =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor { message ->
                    Log.d("OK HTTP", message)
                }.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .connectTimeout(10000L, TimeUnit.SECONDS)
            .readTimeout(10000L, TimeUnit.SECONDS)
            .writeTimeout(10000L, TimeUnit.SECONDS)
            .build()

}