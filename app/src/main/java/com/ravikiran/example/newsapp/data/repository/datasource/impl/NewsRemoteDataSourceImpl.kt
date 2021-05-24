package com.ravikiran.example.newsapp.data.repository.datasource.impl

import com.ravikiran.example.newsapp.data.api.NewsApiService
import com.ravikiran.example.newsapp.data.model.APIResponse
import com.ravikiran.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(private val newsApiService: NewsApiService) : NewsRemoteDataSource {

    // getting top news from rest api
    override suspend fun getTopNews(page: Int): Response<APIResponse> {
        return newsApiService.getTopHeadlines(page)
    }

    // getting search news from rest api
    override suspend fun getSearchedNews(page: Int, query: String): Response<APIResponse> {
        return newsApiService.getSearchedTopHeadlines(page = page, query = query)
    }


}