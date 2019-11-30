package com.jrodiriguezva.rsskotlin.data.respository

import com.jrodiriguezva.rsskotlin.BuildConfig
import com.jrodiriguezva.rsskotlin.data.datasource.local.FeedDatabase
import com.jrodiriguezva.rsskotlin.data.datasource.local.feed.FeedData.Companion.convertToData
import com.jrodiriguezva.rsskotlin.data.datasource.remote.FeedService
import com.jrodiriguezva.rsskotlin.domain.error.Failure
import com.jrodiriguezva.rsskotlin.domain.error.NoDataAvailableFailure
import com.jrodiriguezva.rsskotlin.domain.funtional.Either
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import com.jrodiriguezva.rsskotlin.domain.repository.Repository
import com.jrodiriguezva.rsskotlin.utils.NetworkHandler
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val database: FeedDatabase,
    private val service: FeedService
) : Repository {
    companion object {
        private const val FIRST_PAGE = 1
    }

    private var page = FIRST_PAGE

    override fun getFeeds(query: String?): Either<Failure, List<Feed>> {
        val feedDao = database.feedDao()
        return when (networkHandler.isConnected) {
            true -> {
                val execute = service.getFeeds(apiKey = BuildConfig.API_KEY, page = page).execute()
                val response = ApiResponse.create(execute)
                when (response) {
                    is ApiResponse.ApiEmptyResponse -> Either.Left(Failure.ServerError)
                    is ApiResponse.ApiSuccessResponse -> {
                        val map = response.body.articles?.map { it.convertToModel() }
                        map?.let { mapSafe ->
                            if (page++ == FIRST_PAGE) feedDao.removeAll()
                            Either.Right(feedDao.save(convertToData(mapSafe)).map { it.convertToModel() }.sortedBy { it.publishedAt })
                        } ?: kotlin.run { Either.Left(Failure.ServerError) }

                    }
                    is ApiResponse.ApiErrorResponse -> Either.Left(Failure.ServerError)
                }
            }
            false -> {
                page = FIRST_PAGE
                val feeds = feedDao.findAllFeed()?.map { it.convertToModel() }
                feeds?.let { Either.Right(it) } ?: kotlin.run { Either.Left(NoDataAvailableFailure()) }
            }
        }
    }

    override fun getFeed(feedId: Long): Either<Failure, Feed> {
        val feed = database.feedDao().findFeedById(feedId)?.convertToModel()
        return feed?.let { Either.Right(it) } ?: kotlin.run { Either.Left(NoDataAvailableFailure()) }
    }
}
