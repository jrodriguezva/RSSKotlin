package com.jrodiriguezva.rsskotlin.domain.interactor

import com.jrodiriguezva.rsskotlin.data.respository.FeedRepository
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFeeds
@Inject constructor(private val feedRepository: FeedRepository) : UseCase<List<Feed>, String?>() {

    override suspend fun run(params: String?) = feedRepository.getFeeds(params)

}