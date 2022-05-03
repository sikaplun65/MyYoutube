package com.sikaplun.kotlin.myyoutube.data.di.modules

import com.sikaplun.kotlin.myyoutube.data.api.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

val retrofitModule = module {
    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}