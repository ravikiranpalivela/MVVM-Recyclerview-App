package com.ravikiran.example.newsapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.data.util.Resource
import com.ravikiran.example.newsapp.databinding.ActivityHomeBinding
import com.ravikiran.example.newsapp.presentation.adapter.NewsPaginationAdapter
import com.ravikiran.example.newsapp.presentation.viewmodel.MainViewModel
import com.ravikiran.example.newsapp.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private var layoutManager: LinearLayoutManager? = null
    private var page_number = 1
    private val item_count = 8
    private var isLoading = true
    private var pastVisibleItems = 0
    private var VisibleItemCount = 0
    private var TotalItemCount = 0
    private var previousTotal = 0
    private val view_thereshold = 8
    var newsList = mutableListOf<Article>()


    @Inject
    lateinit var vmFactory: MainViewModelFactory
    lateinit var mViewModel: MainViewModel
//    @Inject
     val newsAdapter = NewsPaginationAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager

        mViewModel = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

        initNewsList()

        // Scroll listerner
        binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                VisibleItemCount = layoutManager!!.childCount
                TotalItemCount = layoutManager!!.itemCount
                pastVisibleItems = layoutManager!!.findFirstVisibleItemPosition()
                if (dy > 0) {
                    if (isLoading) {
                        if (TotalItemCount > previousTotal) {
                            isLoading = false
                            previousTotal = TotalItemCount
                        }
                    }
                    if (!isLoading && TotalItemCount - VisibleItemCount <= pastVisibleItems + view_thereshold) {
                        page_number++
                        viewNewsData()
                        isLoading = true
                    }
                }
            }
        })

        binding.swipeRefreshLayout.setOnRefreshListener(OnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            page_number = 1
            viewNewsData()
        })



        initNewsList()
        viewNewsData()

    }

    private fun initNewsList() {
        newsAdapter.setOnClickCallback(::onNewsArticleClicked)
        binding.recyclerview.apply {
//            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = newsAdapter
//            hasFixedSize()
        }
    }

    private fun viewNewsData() {

        if(page_number == 1)
        {
            newsList.clear()
            newsAdapter.setNewsList(newsList, this)
        }
        mViewModel.getTopNews(page_number)
        mViewModel.topNews.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    Log.i("taggy", "Loading...")
                    onLoadingState(true)
                }
                is Resource.Error -> {
                    Log.i("taggy", "Error ${it.message}")
                    onLoadingState(false)
                    it.message?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Success -> {
                    onLoadingState(false)
                    it.data?.articles.let { list ->

                        newsList.addAll(list!!)

                        newsAdapter.setNewsList(newsList, this)

//                        newsAdapter.addNews(list!!)
                        Log.i("taggy", "Success. ${newsList.size}")

                    }
                }
            }
        })
    }

    private fun onLoadingState(isLoading: Boolean) {
        var visibility = View.VISIBLE
        if (!isLoading)
            visibility = View.GONE
        binding.progressBar.visibility = visibility
    }

    private fun onNewsArticleClicked(article: Article){
//        Toast.makeText(activity, article.title, Toast.LENGTH_SHORT).show()
        val bundle =  Bundle().apply {
            putSerializable("article", article)
        }
    }

}