package com.sikaplun.kotlin.myyoutube.data.repositories

import android.util.Log
import com.sikaplun.kotlin.myyoutube.data.api.ApiService
import com.sikaplun.kotlin.myyoutube.data.models.Items
import com.sikaplun.kotlin.myyoutube.data.models.VideoModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoListRequest(private val apiService: ApiService): DataSource<List<Items>> {

    private val listVideos = BehaviorSubject.create<List<Items>>()

    fun findVideos(query: String) {
        apiService.getVideosDetails(
            key = YOUTUBE_API_KEY,
            query = query,
            part = "snippet",
            order = Order.viewCount.toString(),
            maxResults = "50",
            type = "video"
        )
            .enqueue(object : Callback<VideoModel> {
                override fun onResponse(
                    call: Call<VideoModel>,
                    response: Response<VideoModel>
                ) {
                    if (response.isSuccessful) {
                        listVideos.onNext(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<VideoModel>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    override fun getData(): Observable<List<Items>> = listVideos

    enum class Order {
        DATE,
        RATING,
        RELEVANCE,
        TITLE,
        videoCount,
        viewCount
    }

    companion object {
        private const val YOUTUBE_API_KEY = "AIzaSyBQptopL-q5eRjBwo_k3Qr3N8CTwvu9PWk"
    }
}