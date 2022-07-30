package com.example.ceiba_test.core.basedomain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class UseCase<in TParam, out TResult>(
    private val exceptionHandler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher
) {

    @ExperimentalCoroutinesApi
    @Suppress("TooGenericExceptionCaught")
    suspend operator fun invoke(param: TParam) =
        performAction(param)
            .catch { exception ->
                exceptionHandler.handle(exception)
                emit(Result.Failure(exception))
            }
            .flowOn(dispatcher)

    protected abstract suspend fun performAction(param: TParam): Flow<Result<TResult>>
}





abstract class UseCaseWithoutParams<out TResult>(
    private val exceptionHandler: IExceptionHandler,
    private val dispatcher: CoroutineDispatcher
) {

    @ExperimentalCoroutinesApi
    @Suppress("TooGenericExceptionCaught")
    suspend operator fun invoke() =
        performAction()
            .catch { exception ->
                exceptionHandler.handle(exception)
                emit(Result.Failure(exception))
            }
            .flowOn(dispatcher)

    protected abstract suspend fun performAction(): Flow<Result<TResult>>
}