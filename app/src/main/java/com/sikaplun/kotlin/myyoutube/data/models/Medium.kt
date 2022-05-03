package com.sikaplun.kotlin.myyoutube.data.models

import com.google.gson.annotations.SerializedName

data class Medium(
    @SerializedName("width") val width: String,
    @SerializedName("url") val url: String,
    @SerializedName("height") val height: String
)