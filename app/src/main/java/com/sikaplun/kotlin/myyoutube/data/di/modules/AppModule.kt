package com.sikaplun.kotlin.myyoutube.data.di.modules

import com.sikaplun.kotlin.myyoutube.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        MainViewModel(videoListRequest = get())
    }
}