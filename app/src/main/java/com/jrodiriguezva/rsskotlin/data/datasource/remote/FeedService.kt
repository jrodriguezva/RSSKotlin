package com.jrodiriguezva.rsskotlin.data.datasource.remote

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FeedService
@Inject constructor(retrofit: Retrofit) : FeedApi {
    private val feedApi by lazy { retrofit.create(FeedApi::class.java) }

    override fun getFeeds(search: String?, language: String?, sortBy: String?, apiKey: String, page: Int) =
        feedApi.getFeeds(search, language, sortBy, apiKey, page)
}