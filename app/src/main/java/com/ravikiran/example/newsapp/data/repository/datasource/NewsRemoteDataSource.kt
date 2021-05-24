package com.ravikiran.example.newsapp.data.repository.datasource

import com.ravikiran.example.newsapp.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {

    // rest api request page no
    suspend fun getTopNews(page: Int): Response<APIResponse>

    suspend fun getSearchedNews(page: Int, query: String): Response<APIResponse>
}