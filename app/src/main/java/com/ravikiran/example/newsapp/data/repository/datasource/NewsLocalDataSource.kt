package com.ravikiran.example.newsapp.data.repository.datasource

import com.ravikiran.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    // interface for artical info
    suspend fun saveArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getSavedArticles(): Flow<List<Article>>
}