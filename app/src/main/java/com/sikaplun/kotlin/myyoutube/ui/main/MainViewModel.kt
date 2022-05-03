package com.sikaplun.kotlin.myyoutube.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikaplun.kotlin.myyoutube.data.models.Items
import com.sikaplun.kotlin.myyoutube.data.repositories.VideoListRequest
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class MainViewModel(private val videoListRequest: VideoListRequest) : ViewModel() {

    private val listVideo by lazy { MutableLiveData<List<Items>>() }
    private lateinit var disposable: Disposable

    fun setSearchVideo(query: String) {
        videoListRequest.findVideos(query)
    }

    fun getListVideos(): MutableLiveData<List<Items>> {
        disposable = videoListRequest.getData().subscribeBy(
            onNext = {
                listVideo.postValue(it)
            },
            onError = { it.printStackTrace() },
            onComplete = { Log.i("Complete", "onCompleted: COMPLETED!") }
        )
        return listVideo
    }

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }

}