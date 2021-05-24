package com.ravikiran.example.newsapp.presentation.di

import android.app.Application
import com.ravikiran.example.newsapp.domain.usecase.*
import com.ravikiran.example.newsapp.presentation.adapter.NewsAdapter
import com.ravikiran.example.newsapp.presentation.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Singleton
    @Provides
    fun providesMainViewModelFactory(
        getTopNewsUseCase: GetTopNewsUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase,
        getSavedArticlesUseCase: GetSavedArticlesUseCase,
        saveArticleUseCase: SaveArticleUseCase,
        deleteArticleUseCase: DeleteArticleUseCase,
        app: Application
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getTopNewsUseCase,
            getSearchedNewsUseCase,
            getSavedArticlesUseCase,
            saveArticleUseCase,
            deleteArticleUseCase,
            app
        )
    }

    @Provides
    fun provideNewNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}