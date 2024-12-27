package com.example.kotlinxmltodo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinxmltodo.model.data.Post
import com.example.kotlinxmltodo.model.repo.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    private val repository = PostRepository

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    private val _selectedPost = MutableStateFlow<Post?>(null)
    val selectedPost: StateFlow<Post?> = _selectedPost

    init {
        getPosts()
    }

    private fun getPosts() {
        viewModelScope.launch {
            try {
                repository.getPosts().collect { posts ->
                    Log.d("Dang", "VM getPosts: $posts")
                    _posts.value = posts
                 }
            } catch (e: Exception) {
                Log.d("Dang", "getPosts Error: ${e.message}")
            }
        }
    }

    fun selectPost(id: Int) {
        _selectedPost.value = _posts.value.find { it.id == id }
    }
}