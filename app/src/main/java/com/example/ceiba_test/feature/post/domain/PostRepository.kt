package com.example.ceiba_test.feature.post.domain

import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import com.example.ceiba_test.core.basedomain.Result
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import java.lang.reflect.Executable
import java.net.UnknownHostException

class PostRepository @Inject constructor(
    var postDataSource:PostDataSource
){


    suspend fun getAllPostByIdUser(idUser: Int):Flow<Result<List<Post>>> {

        var lista:List<Post>?
        return try {
            lista= postDataSource.getAllPostByIdUser(idUser)
            flowOf(Result.fromNullable(lista))
        } catch (e:Exception){
            flowOf(Result.Failure(e.cause))
        }
    }

}