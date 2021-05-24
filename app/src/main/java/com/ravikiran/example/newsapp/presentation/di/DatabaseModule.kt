package com.ravikiran.example.newsapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.ravikiran.example.newsapp.data.db.ArticlesDAO
import com.ravikiran.example.newsapp.data.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    // create room database using DI news database
    @Singleton
    @Provides
    fun providesNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(app, NewsDatabase::class.java, "latest_news_db").build()
    }

    // getting list of articals from room
    @Singleton
    @Provides
    fun providesArticleDao(database: NewsDatabase):ArticlesDAO{
        return database.getArticleDao()
    }
}