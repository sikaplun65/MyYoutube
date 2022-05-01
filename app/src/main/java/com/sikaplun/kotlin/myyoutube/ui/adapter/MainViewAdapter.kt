package com.sikaplun.kotlin.myyoutube.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sikaplun.kotlin.myyoutube.data.models.Items
import com.sikaplun.kotlin.myyoutube.databinding.VideoItemBinding

class MainActivityAdapter : RecyclerView.Adapter<MainActivityAdapter.MainActivityViewHolder>() {

    private val listVideos = mutableListOf<Items>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListVideos(data: List<Items>) {
        listVideos.clear()
        listVideos.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val view = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainActivityViewHolder, position: Int) {
        holder.bind(listVideos[position])
    }

    override fun getItemCount(): Int = listVideos.size


    inner class MainActivityViewHolder(private val binding: VideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Items) {
            binding.apply {
                Glide.with(itemView)
                    .load(data.snippet.thumbnails.high.url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imageView)

                textView.text = data.snippet.title
            }
        }
    }

}