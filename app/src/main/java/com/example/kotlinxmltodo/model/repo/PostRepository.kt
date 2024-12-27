package com.example.kotlinxmltodo.model.repo

import android.util.Log
import com.example.kotlinxmltodo.model.data.Post
import com.example.kotlinxmltodo.model.remote.retrofit.ApiClient
import com.example.kotlinxmltodo.model.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object PostRepository {

    private val apiService: ApiService = ApiClient.apiService

    fun getPosts(): Flow <List<Post>> = flow {
        try {
            val response = apiService.getPosts()
            Log.d("Dang", "Repo getPosts: $response")
            emit(response)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }
}