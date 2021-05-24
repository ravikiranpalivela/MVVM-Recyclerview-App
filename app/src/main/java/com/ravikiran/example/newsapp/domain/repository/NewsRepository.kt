package com.ravikiran.example.newsapp.domain.repository

import com.ravikiran.example.newsapp.data.model.APIResponse
import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    // rest api repository information
    suspend fun getTopNews(page: Int): Resource<APIResponse>

    suspend fun getSearchedNews(page: Int, query: String): Resource<APIResponse>

    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getSavedArticles(): Flow<List<Article>>
}