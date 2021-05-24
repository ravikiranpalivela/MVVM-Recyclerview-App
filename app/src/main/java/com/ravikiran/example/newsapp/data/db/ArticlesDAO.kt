package com.ravikiran.example.newsapp.data.db

import androidx.room.*
import com.ravikiran.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticlesDAO {

    // if conflicts occur replace existing one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    // get artical from room
    @Query("SELECT * FROM articles")
    fun getAllArticles() : Flow<List<Article>>

    // delete artical from room
    @Delete
    suspend fun delete(article: Article)
}