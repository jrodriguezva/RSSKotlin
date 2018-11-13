package com.jrodiriguezva.rsskotlin.domain.repository

import com.jrodiriguezva.rsskotlin.domain.error.Failure
import com.jrodiriguezva.rsskotlin.domain.funtional.Either
import com.jrodiriguezva.rsskotlin.domain.model.Feed

interface Repository {

    fun getFeeds(query: String?): Either<Failure, List<Feed>>

    fun getFeed(feedId: Long): Either<Failure, Feed>
}