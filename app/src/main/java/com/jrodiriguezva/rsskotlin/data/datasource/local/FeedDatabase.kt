package com.jrodiriguezva.rsskotlin.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jrodiriguezva.rsskotlin.data.datasource.local.feed.FeedDao
import com.jrodiriguezva.rsskotlin.data.datasource.local.feed.FeedData

@Database(entities = [FeedData::class], version = 1)
@TypeConverters(Converters::class)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
}