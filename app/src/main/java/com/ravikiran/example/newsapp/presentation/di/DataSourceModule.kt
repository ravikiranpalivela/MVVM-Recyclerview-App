package com.ravikiran.example.newsapp.presentation.di

import com.ravikiran.example.newsapp.data.api.NewsApiService
import com.ravikiran.example.newsapp.data.db.ArticlesDAO
import com.ravikiran.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.ravikiran.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.ravikiran.example.newsapp.data.repository.datasource.impl.NewsLocalDataSourceImpl
import com.ravikiran.example.newsapp.data.repository.datasource.impl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsApiService)
    }

    @Singleton
    @Provides
    fun provideNewsLocalDataSource(articlesDAO: ArticlesDAO):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(articlesDAO)
    }
}