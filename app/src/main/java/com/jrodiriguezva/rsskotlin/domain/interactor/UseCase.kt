package com.jrodiriguezva.rsskotlin.domain.interactor

import com.jrodiriguezva.rsskotlin.domain.error.Failure
import com.jrodiriguezva.rsskotlin.domain.funtional.Either
import kotlinx.coroutines.*

/**
 * Any use case in the application should implement this contract.
 * This class represents an execution unit for different use cases
 *
 * Each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    private lateinit var job: Deferred<Either<Failure, Type>>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        GlobalScope.launch(Dispatchers.Main) {
            job = async(Dispatchers.IO) { run(params) }
            onResult(job.await())
        }
    }

    class None
}