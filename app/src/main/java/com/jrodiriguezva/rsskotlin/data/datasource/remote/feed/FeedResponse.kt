package com.jrodiriguezva.rsskotlin.data.datasource.remote.feed

import com.squareup.moshi.Json

data class FeedResponse(
    @Json(name = "status")
    var status: String? = null,
    @Json(name = "totalResults")
    var totalResults: Int? = null,
    @Json(name = "articles")
    var articles: List<Article>? = null

)