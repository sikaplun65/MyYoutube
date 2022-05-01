package com.sikaplun.kotlin.myyoutube.data.models

import com.google.gson.annotations.SerializedName

data class Snippet(
    @SerializedName("publishTime") val publishTime: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("description") val description: String,
    @SerializedName("title") val title: String,
    @SerializedName("thumbnails") val thumbnails: Thumbnails,
    @SerializedName("channelId") val channelId: String,
    @SerializedName("liveBroadcastContent") val liveBroadcastContent: String
)


