package com.example.ceiba_test.feature.user.interactors

import com.example.ceiba_test.core.basedomain.IExceptionHandler
import com.example.ceiba_test.core.basedomain.Result
import com.example.ceiba_test.core.basedomain.UseCase
import com.example.ceiba_test.core.basedomain.UseCaseWithoutParams
import com.example.ceiba_test.feature.user.domain.User
import com.example.ceiba_test.feature.user.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ValidateIfRequireFetch(
    private val repository: UserRepository,
    exceptionHandler: IExceptionHandler,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) :UseCaseWithoutParams<Boolean>(exceptionHandler, dispatcher) {

    override suspend fun performAction()  = repository.dbIsEmpty()
}