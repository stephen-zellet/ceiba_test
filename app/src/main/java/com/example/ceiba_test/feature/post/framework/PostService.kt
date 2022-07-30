package com.example.ceiba_test.feature.post.framework

import com.example.ceiba_test.feature.post.domain.Post
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    companion object{
        const val BASE_URL="https://jsonplaceholder.typicode.com/"
        const val ENDPOINT ="posts/"
    }

    @GET(ENDPOINT)
    suspend fun getAllPost(@Query("userId")userId:Int):List<Post>
}