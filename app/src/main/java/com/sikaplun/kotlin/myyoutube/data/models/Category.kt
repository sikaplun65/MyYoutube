package com.sikaplun.kotlin.myyoutube.data.models

import java.util.*

data class Category(
    val category: String
){
    private val id: String = Calendar.getInstance().time.time.toString()
}
