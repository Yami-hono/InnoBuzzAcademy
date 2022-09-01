package com.example.innobuzzacademy.network

import com.example.innobuzzacademy.database.Post
import com.example.repository.repository.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET("posts")
    suspend fun getPostList(): Response<List<Post>>
}