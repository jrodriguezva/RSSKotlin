package com.jrodiriguezva.rsskotlin.data.datasource.remote.feed

import com.jrodiriguezva.rsskotlin.domain.model.Feed
import com.jrodiriguezva.rsskotlin.utils.extension.empty
import com.squareup.moshi.Json
import java.util.*

data class Article(
    @Json(name = "source")
    var source: Source? = null,
    @Json(name = "author")
    private var author: String? = null,
    @Json(name = "title")
    var title: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "url")
    var url: String? = null,
    @Json(name = "urlToImage")
    var urlToImage: String? = null,
    @Json(name = "publishedAt")
    var publishedAt: Date? = null,
    @Json(name = "content")
    var content: String? = null
) {
    companion object {
        fun convertToData(feed: Feed) = Article(
            author = feed.author,
            publishedAt = feed.publishedAt,
            description = feed.description,
            title = feed.title,
            url = feed.url,
            urlToImage = feed.imageUrl
        )

    }

    fun convertToModel() = Feed(
        author = this.author ?: String.empty(),
        publishedAt = this.publishedAt ?: Date(),
        description = this.description ?: String.empty(),
        title = this.title ?: String.empty(),
        url = this.url ?: String.empty(),
        imageUrl = this.urlToImage ?: String.empty()
    )
}