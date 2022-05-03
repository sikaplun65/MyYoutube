package com.sikaplun.kotlin.myyoutube.data.di.modules

import com.sikaplun.kotlin.myyoutube.data.repositories.VideoListRequest
import org.koin.dsl.module


val dataModule = module {
    single { VideoListRequest(apiService = get()) }
}