package com.sikaplun.kotlin.myyoutube.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.kotlin.myyoutube.data.models.Category
import com.sikaplun.kotlin.myyoutube.databinding.ActivityMainBinding
import com.sikaplun.kotlin.myyoutube.ui.MainActivityAdapter
import com.sikaplun.kotlin.myyoutube.ui.adapter.CategoryAdapter
import com.sikaplun.kotlin.myyoutube.ui.detail.SearchDialogFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val videoAdapter by lazy { MainActivityAdapter() }
    private val categoryAdapter by lazy { CategoryAdapter() }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListVideoRecyclerView()
        initCategoryRecyclerView()
        searchVideo()
        initSearchButton()
        showFoundVideos()
    }

    private fun initSearchButton() {
        binding.searchImageButton.setOnClickListener{
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(videoTitle: String) {
                    Toast.makeText(this@MainActivity,"В разработке",Toast.LENGTH_LONG).show()
                }
            })
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }


    private fun showFoundVideos() {
        viewModel.getListVideos().observe(this) { listVideos ->
            if (listVideos != null) {
                videoAdapter.setListVideos(listVideos)
                showLoading(false)
            }
        }
    }

    private fun searchVideo() {
        viewModel.setSearchVideo()
        showLoading(true)


    }

    private fun initListVideoRecyclerView() {
        binding.apply {
            videoRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            videoRecyclerView.setHasFixedSize(true)
            videoRecyclerView.adapter = videoAdapter
        }
    }

    private fun initCategoryRecyclerView() {
        val categoryList: List<Category> = getCategories()
        categoryAdapter.setCategoryAdapter(categoryList)

        binding.apply {
            categoryRecyclerView.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            categoryRecyclerView.setHasFixedSize(true)
            categoryRecyclerView.adapter = categoryAdapter
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun getCategories(): List<Category> {
        val categories = ArrayList<Category>()
        categories.add(Category("Все"))
        categories.add(Category("Мои каналы"))
        categories.add(Category("Футбол"))
        categories.add(Category("Дикая природа"))
        categories.add(Category("Музыка"))
        categories.add(Category("Избраное"))

        return categories
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}

//        youTubePlayerView = binding.youtubePlayerView
//
//        val listener = object : YouTubePlayer.OnInitializedListener {
//            override fun onInitializationSuccess(
//                provider: YouTubePlayer.Provider,
//                youTubePlayer: YouTubePlayer,
//                b: Boolean
//            ) {
//                if (!b) {
//                    youTubePlayer.loadVideo("rpzuEN8UhUQ")
//                    youTubePlayer.play()
//                }
//            }
//
//            override fun onInitializationFailure(
//                provider: YouTubePlayer.Provider,
//                youTubeInitializationResult: YouTubeInitializationResult
//            ) {
//                Log.e(TAG, "Error on Initializing Youtube")
//            }
//        }
//
//        youTubePlayerView.initialize(R.string.youtubeAPIKey.toString(), listener)