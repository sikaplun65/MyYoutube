package com.sikaplun.kotlin.myyoutube.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sikaplun.kotlin.myyoutube.R
import com.sikaplun.kotlin.myyoutube.data.api.RetrofitClient
import com.sikaplun.kotlin.myyoutube.data.models.Items
import com.sikaplun.kotlin.myyoutube.data.models.VideoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val listVideos = MutableLiveData<List<Items>>()

    fun setSearchVideo() {
        RetrofitClient.apiInstance
            .getVideosDetails(
                key = YOUTUBE_API_KEY,
                channelId = "UCoF2NslstOjGAyQL3p7D_xQ",
                part = "snippet",
                order = "date",
                maxResults = "50",
                type = "video"
            )
            .enqueue(object : Callback<VideoModel> {
                override fun onResponse(
                    call: Call<VideoModel>,
                    response: Response<VideoModel>
                ) {
                    if (response.isSuccessful) {
                        listVideos.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<VideoModel>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getListVideos(): LiveData<List<Items>> = listVideos

    companion object{
        private const val YOUTUBE_API_KEY = "AIzaSyBQptopL-q5eRjBwo_k3Qr3N8CTwvu9PWk"
    }
}