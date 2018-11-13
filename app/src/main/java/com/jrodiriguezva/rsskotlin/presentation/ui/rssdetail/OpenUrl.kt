package com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail

import android.content.Context
import com.jrodiriguezva.rsskotlin.domain.error.Failure
import com.jrodiriguezva.rsskotlin.domain.funtional.Either
import com.jrodiriguezva.rsskotlin.domain.interactor.UseCase
import com.jrodiriguezva.rsskotlin.presentation.navigation.Navigator
import javax.inject.Inject

class OpenUrl
@Inject constructor(
    private val context: Context,
    private val navigator: Navigator
) : UseCase<UseCase.None, OpenUrl.Params>() {

    override suspend fun run(params: Params): Either<Failure, None> {
        navigator.openFeed(context, params.url)
        return Either.Right(None())
    }

    data class Params(val url: String)
}