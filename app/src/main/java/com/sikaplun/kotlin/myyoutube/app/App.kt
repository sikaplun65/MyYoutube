package com.sikaplun.kotlin.myyoutube.app

import android.app.Application
import com.sikaplun.kotlin.myyoutube.data.di.modules.appModule
import com.sikaplun.kotlin.myyoutube.data.di.modules.dataModule
import com.sikaplun.kotlin.myyoutube.data.di.modules.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)
            modules(listOf(appModule, dataModule, retrofitModule))
        }
    }
}