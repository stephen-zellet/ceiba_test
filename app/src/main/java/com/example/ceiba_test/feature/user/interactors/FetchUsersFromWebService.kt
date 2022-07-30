package com.example.ceiba_test.feature.user.interactors

import com.example.ceiba_test.core.basedomain.IExceptionHandler
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.core.basedomain.UseCase
import com.example.ceiba_test.core.basedomain.UseCaseWithoutParams
import com.example.ceiba_test.feature.user.repository.UserRepository
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class FetchUsersFromWebService(
    var repository: UserRepository,
    exceptionHandler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UseCaseWithoutParams< Boolean>(exceptionHandler, dispatcher) {

    override suspend fun performAction():Flow<Result<Boolean>> = repository.fetchUsersFromWeb()
}