package com.sikaplun.kotlin.myyoutube.data.api

import com.sikaplun.kotlin.myyoutube.data.models.VideoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun getVideosDetails(

        @Query("key")
        key: String,

        @Query("channelId")
        channelId: String,

        @Query("part")
        part: String,

        @Query("order")
        order: String,

        @Query("maxResults")
        maxResults: String,

        @Query("type")
        type: String

    ): Call<VideoModel>
}
