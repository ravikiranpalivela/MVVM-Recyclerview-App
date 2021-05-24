package com.ravikiran.example.newsapp.domain.usecase

import com.ravikiran.example.newsapp.data.model.APIResponse
import com.ravikiran.example.newsapp.data.util.Resource
import com.ravikiran.example.newsapp.domain.repository.NewsRepository

class GetSearchedNewsUseCase(private val repository: NewsRepository) {

    // calling rest api for async task for get search artical

    suspend fun execute(page: Int, query: String) : Resource<APIResponse> {
        return repository.getSearchedNews(page, query)
    }
}