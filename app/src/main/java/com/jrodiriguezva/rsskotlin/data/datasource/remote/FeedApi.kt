package com.jrodiriguezva.rsskotlin.data.datasource.remote

import com.jrodiriguezva.rsskotlin.data.datasource.remote.feed.FeedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FeedApi {
    companion object {
        private const val VERSION = "v2/"
        private const val EVERYTHING = VERSION + "everything"
        private const val API_KEY = "apiKey"
        private const val LANGUAGE = "language"
        private const val SORT_BY = "sortBy"
        private const val QUERY = "q"
    }

    @GET(EVERYTHING)
    fun getFeeds(
        @Query(QUERY) search: String? = "android",
        @Query(LANGUAGE) language: String? = "es",
        @Query(SORT_BY) sortBy: String? = "publishedAt",
        @Query(API_KEY) apiKey: String,
        @Query("page") page: Int
    ): Call<FeedResponse>

}
