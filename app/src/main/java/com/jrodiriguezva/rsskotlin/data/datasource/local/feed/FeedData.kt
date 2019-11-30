package com.jrodiriguezva.rsskotlin.data.datasource.local.feed

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import java.util.Date

@Entity(tableName = "feed")
data class FeedData(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Long? = null,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "urlToImage")
    var urlToImage: String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: Date
) {
    companion object {
        fun convertToData(feed: Feed) = FeedData(
            id = feed.id,
            author = feed.author,
            publishedAt = feed.publishedAt,
            description = feed.description,
            title = feed.title,
            url = feed.url,
            urlToImage = feed.imageUrl
        )

        fun convertToData(feeds: List<Feed>) = feeds.map { FeedData.convertToData(it) }
    }

    fun convertToModel() = Feed(
        id = this.id,
        author = this.author,
        publishedAt = this.publishedAt,
        description = this.description,
        title = this.title,
        url = this.url,
        imageUrl = this.urlToImage
    )
}