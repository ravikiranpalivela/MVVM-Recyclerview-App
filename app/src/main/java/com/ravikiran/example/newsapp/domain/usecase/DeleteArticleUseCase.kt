package com.ravikiran.example.newsapp.domain.usecase

import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.domain.repository.NewsRepository

class DeleteArticleUseCase(private val newsRepository: NewsRepository) {

    // async task for delete artical
    suspend fun execute(article: Article) = newsRepository.deleteArticle(article)
}