package com.example.ceiba_test.feature.user.repository

import kotlinx.coroutines.flow.Flow
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.core.exception.ErrorInsertingData
import com.example.ceiba_test.feature.user.domain.User
import com.example.ceiba_test.feature.user.domain.UserEntity
import com.example.ceiba_test.feature.user.repository.datasource.local.UserLocalDataSource
import com.example.ceiba_test.feature.user.repository.datasource.remote.UserRemoteDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import java.lang.Exception
import javax.inject.Inject

class UserRepository @Inject constructor(
    var userRemoteDataSource: UserRemoteDataSource,
    var userLocalDataSource: UserLocalDataSource
) {


    suspend fun fetchUsersFromWeb(): Flow<Result<Boolean>> {
        val list: List<User>?
        try {
            list = userRemoteDataSource.getAll()
            val result = userLocalDataSource.insertAllMovies(list.map {
                UserEntity(
                    id_web = it.id,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    email = it.email
                )
            })

            if (result.isNullOrEmpty()) return flowOf(Result.Failure(ErrorInsertingData("No se pudo almacenar en la base de datos")))

        } catch (e: Exception) {
            return flowOf(Result.Failure(e.cause))
        }

        return flowOf(Result.Success(list.isNullOrEmpty()))
    }

     fun getAllUserFromDb(): Flow<Result<List<UserEntity>>> {
        val users: List<UserEntity>
        return try {
            users = userLocalDataSource.findAllUsers()
            flowOf(Result.Success(users))
        } catch (e: Exception) {
            flowOf(Result.Failure(e))
        }


    }

     fun dbIsEmpty(): Flow<Result<Boolean>> {
        val total = userLocalDataSource.getTotalUserInDb()
        return flowOf(Result.Success(total < 1))
    }

}
