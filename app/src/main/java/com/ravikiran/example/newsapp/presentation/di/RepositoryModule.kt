package com.ravikiran.example.newsapp.presentation.di

import com.ravikiran.example.newsapp.data.repository.NewsRepositoryImpl
import com.ravikiran.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.ravikiran.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.ravikiran.example.newsapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}