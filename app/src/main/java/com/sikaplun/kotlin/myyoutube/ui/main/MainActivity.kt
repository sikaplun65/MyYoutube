package com.sikaplun.kotlin.myyoutube.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.kotlin.myyoutube.data.models.Category
import com.sikaplun.kotlin.myyoutube.data.models.CategoryList
import com.sikaplun.kotlin.myyoutube.data.models.Items
import com.sikaplun.kotlin.myyoutube.databinding.ActivityMainBinding
import com.sikaplun.kotlin.myyoutube.ui.adapter.CategoryAdapter
import com.sikaplun.kotlin.myyoutube.ui.adapter.MainActivityAdapter
import com.sikaplun.kotlin.myyoutube.ui.detail.SearchDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val videoAdapter by lazy { MainActivityAdapter() }
    private val categoryAdapter by lazy { CategoryAdapter() }
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListVideoRecyclerView()
        initCategoriesRecyclerView()
        initOnClickMainViewAdapter()
        initOnClickCategoryAdapter()
        initSearchButton()
        showFoundVideos()
    }

    private fun initOnClickCategoryAdapter() {
        categoryAdapter.setOnItemClickCallbackCategoryAdapter(object :
            CategoryAdapter.OnItemClickCallbackCategoryAdapter {
            override fun onItemClickedCategoryAdapter(data: Category) {
                Toast.makeText(this@MainActivity, "В разработке", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initOnClickMainViewAdapter() {
        videoAdapter.setOnItemClickCallbackMainActivityAdapter(object :
            MainActivityAdapter.OnItemClickCallbackMainActivityAdapter {
            override fun onItemClicked(data: Items) {
                Toast.makeText(this@MainActivity, "В разработке", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initSearchButton() {
        binding.searchImageButton.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchVideo: String) {
                    viewModel.setSearchVideo(searchVideo)
                    showLoading(true)
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

    private fun initListVideoRecyclerView() {
        binding.apply {
            videoRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            videoRecyclerView.setHasFixedSize(true)
            videoRecyclerView.adapter = videoAdapter
        }
    }

    private fun initCategoriesRecyclerView() {
        val categoryList = CategoryList(this)
        categoryAdapter.setCategoryAdapter(categoryList.getCategories())

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