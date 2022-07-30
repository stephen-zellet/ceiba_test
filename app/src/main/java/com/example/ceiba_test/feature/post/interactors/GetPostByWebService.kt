package com.example.ceiba_test.feature.post.interactors

import com.example.ceiba_test.core.basedomain.IExceptionHandler
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.core.basedomain.UseCase
import com.example.ceiba_test.feature.post.domain.Post
import com.example.ceiba_test.feature.post.domain.PostRepository
import com.example.ceiba_test.feature.user.domain.User
import com.example.ceiba_test.feature.user.domain.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class GetPostByWebService(
    var repository: PostRepository,
    exceptionHandler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCase<Int, List<Post>>(exceptionHandler, dispatcher) {


    override suspend fun performAction(param: Int): Flow<Result<List<Post>>>
    = repository.getAllPostByIdUser(param)


}