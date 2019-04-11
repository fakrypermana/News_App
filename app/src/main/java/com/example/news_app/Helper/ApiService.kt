package com.yogiw.news.Helper


import com.example.news_app.Dao.NewsDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything?domains=suara.com")
    fun requestNews(
            @Query("apiKey") apiKey: String
    ): Call<NewsDao>
}
