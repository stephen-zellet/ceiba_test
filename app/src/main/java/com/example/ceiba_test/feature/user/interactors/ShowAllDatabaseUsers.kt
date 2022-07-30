package com.example.ceiba_test.feature.user.interactors

import com.example.ceiba_test.core.basedomain.IExceptionHandler
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.core.basedomain.UseCase
import com.example.ceiba_test.core.basedomain.UseCaseWithoutParams
import com.example.ceiba_test.feature.user.domain.User
import com.example.ceiba_test.feature.user.domain.UserEntity
import com.example.ceiba_test.feature.user.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ShowAllDatabaseUsers(
    private val repository: UserRepository,
    exceptionHandler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) :UseCaseWithoutParams<List<UserEntity>>(exceptionHandler, dispatcher) {

    override suspend fun performAction(): Flow<Result<List<UserEntity>>> = repository.getAllUserFromDb()
}