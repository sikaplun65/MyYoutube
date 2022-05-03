package com.sikaplun.kotlin.myyoutube.data.models

import android.content.Context
import com.sikaplun.kotlin.myyoutube.R

class CategoryList(
    private val context: Context,
    private val categories: List<Category> = listOf(
        Category(context.getString(R.string.all)),
        Category(context.getString(R.string.my_channels)),
        Category(context.getString(R.string.football)),
        Category(context.getString(R.string.wildlife)),
        Category(context.getString(R.string.music)),
        Category(context.getString(R.string.favorites))
    )
) {
    fun getCategories(): List<Category> = categories
}