package com.sikaplun.kotlin.myyoutube.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.kotlin.myyoutube.data.models.Category
import com.sikaplun.kotlin.myyoutube.databinding.CategoryItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    private val categories = mutableListOf<Category>()

    private var onItemClickCallbackCategoryAdapter: OnItemClickCallbackCategoryAdapter? = null

    fun setOnItemClickCallbackCategoryAdapter(onItemClickCallback: OnItemClickCallbackCategoryAdapter){
        onItemClickCallbackCategoryAdapter = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCategoryAdapter(data: List<Category>){
        categories.clear()
        categories.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
          fun bind(data: Category){
              binding.apply {
                  root.setOnClickListener{
                      onItemClickCallbackCategoryAdapter?.onItemClickedCategoryAdapter(data = data)
                  }
                  categoryButton.text = data.category
              }
          }

    }


    interface OnItemClickCallbackCategoryAdapter{
        fun onItemClickedCategoryAdapter(data: Category)
    }
}