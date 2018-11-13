package com.jrodiriguezva.rsskotlin.di.module

import androidx.room.Room
import android.content.Context
import com.jrodiriguezva.rsskotlin.data.datasource.local.FeedDatabase
import dagger.Module
import dagger.Provides

@Module
class DaoModule {
    companion object {
        private const val DATABASE_NAME = "FeedDatabase"
    }

    /**
     * Returns the database of the application.
     * @param context Context in which the application is running
     * @return the database of the application
     */
    @Provides
    fun provideFeedMeDatabase(context: Context): FeedDatabase {
        return Room.databaseBuilder(
            context.applicationContext, FeedDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration().build()
    }
}