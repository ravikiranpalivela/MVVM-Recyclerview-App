package com.ravikiran.example.newsapp.domain.usecase

import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.domain.repository.NewsRepository

class SaveArticleUseCase(private val newsRepository: NewsRepository) {

    // getting articales from room
    suspend fun execute(article: Article) = newsRepository.saveArticle(article)
}