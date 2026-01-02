package com.ziad.modernnewsapp.data.remote

import com.ziad.modernnewsapp.data.remote.dto.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String = "technology",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String
    ): NewsResponse

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}