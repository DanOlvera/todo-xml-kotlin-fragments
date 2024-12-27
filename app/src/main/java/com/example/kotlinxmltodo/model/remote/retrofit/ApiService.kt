package com.example.kotlinxmltodo.model.remote.retrofit

import com.example.kotlinxmltodo.model.data.Post
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): List<Post>
}