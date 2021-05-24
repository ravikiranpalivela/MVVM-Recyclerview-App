package com.ravikiran.example.newsapp.data.api

import com.ravikiran.example.newsapp.BuildConfig
import com.ravikiran.example.newsapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    // getting top headlines from api
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("page")
        page: Int,
        @Query("country")
        country: String = "in",
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ) : Response<APIResponse>

    // getting searched headlines from api
    @GET("v2/top-headlines")
    suspend fun getSearchedTopHeadlines(
        @Query("q")
        query:String,
        @Query("page")
        page: Int,
        @Query("country")
        country: String = "in",
        @Query("apiKey")
        apiKey: String = BuildConfig.API_KEY
    ) : Response<APIResponse>

}