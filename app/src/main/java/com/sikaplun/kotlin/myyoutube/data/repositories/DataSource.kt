package com.sikaplun.kotlin.myyoutube.data.repositories

import io.reactivex.rxjava3.core.Observable

interface DataSource<T> {
    fun getData(): Observable<T>
}