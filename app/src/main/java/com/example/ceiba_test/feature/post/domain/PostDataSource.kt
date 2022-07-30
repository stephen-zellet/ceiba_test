package com.example.ceiba_test.feature.post.domain

import com.example.ceiba_test.feature.post.framework.PostService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostDataSource @Inject constructor(
    var postService: PostService
) {
    suspend fun getAllPostByIdUser(idUser: Int) = postService.getAllPost(idUser)
}