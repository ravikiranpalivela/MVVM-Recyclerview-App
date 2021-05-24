package com.ravikiran.example.newsapp.data.repository

import com.ravikiran.example.newsapp.data.model.APIResponse
import com.ravikiran.example.newsapp.data.model.Article
import com.ravikiran.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.ravikiran.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.ravikiran.example.newsapp.data.util.Resource
import com.ravikiran.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun getTopNews(page: Int): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getTopNews(page))
    }

    override suspend fun getSearchedNews(page: Int, query: String): Resource<APIResponse> {
        return responseToResource(newsRemoteDataSource.getSearchedNews(page, query))
    }

    override suspend fun saveArticle(article: Article) {
        newsLocalDataSource.saveArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsLocalDataSource.deleteArticle(article)
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return newsLocalDataSource.getSavedArticles()
    }

    private fun responseToResource(response: Response<APIResponse>):Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(null, "Error: ${response.code().toString()}")
    }
}