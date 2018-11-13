package com.jrodiriguezva.rsskotlin.data.datasource.local.feed

import androidx.room.*

@Dao
interface FeedDao {
    @Query("SELECT * FROM feed ORDER BY publishedAt DESC")
    fun findAllFeed(): List<FeedData>?

    @Query("SELECT * FROM feed WHERE id = :id")
    fun findFeedById(id: Long): FeedData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(map: List<FeedData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(feed: FeedData): Long

    @Transaction
    fun save(feeds: List<FeedData>): List<FeedData> {
        feeds.forEach {
            it.id = save(it)
        }
        return feeds
    }

    @Query("DELETE FROM feed")
    fun removeAll()
}