package com.jrodiriguezva.rsskotlin.domain.interactor

import com.jrodiriguezva.rsskotlin.data.respository.FeedRepository
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFeedById
@Inject constructor(private val feedRepository: FeedRepository) : UseCase<Feed, Long>() {

    override suspend fun run(params: Long) = feedRepository.getFeed(params)

}