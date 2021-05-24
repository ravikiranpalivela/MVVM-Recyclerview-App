package com.ravikiran.example.newsapp.data.repository.datasource.impl

import com.ravikiran.example.newsapp.data.db.ArticlesDAO
import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(private val dao: ArticlesDAO) : NewsLocalDataSource {

    // save artical to room database using corouitne
    override suspend fun saveArticle(article: Article) {
        dao.insert(article)
    }

    // delete artical to room database using corouitne
    override suspend fun deleteArticle(article: Article) {
        dao.delete(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return dao.getAllArticles()
    }
}