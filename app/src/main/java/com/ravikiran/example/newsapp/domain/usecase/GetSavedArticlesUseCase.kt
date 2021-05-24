package com.ravikiran.example.newsapp.domain.usecase

import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedArticlesUseCase(private val newsRepository: NewsRepository) {

    // getting async task for get articals

    fun execute():Flow<List<Article>> = newsRepository.getSavedArticles()
}