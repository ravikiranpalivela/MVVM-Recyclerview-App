package com.ravikiran.example.newsapp.domain.usecase

import com.ravikiran.example.newsapp.data.model.APIResponse
import com.ravikiran.example.newsapp.data.util.Resource
import com.ravikiran.example.newsapp.domain.repository.NewsRepository

class GetTopNewsUseCase(private val repository: NewsRepository) {

    //calling  async task for get top news artical

    suspend fun execute(page: Int) : Resource<APIResponse>{
        return repository.getTopNews(page)
    }
}