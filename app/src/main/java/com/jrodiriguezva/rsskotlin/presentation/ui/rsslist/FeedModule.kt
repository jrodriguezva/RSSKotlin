package com.jrodiriguezva.rsskotlin.presentation.ui.rsslist

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedModule {

    @ContributesAndroidInjector
    abstract fun provideRSSListFragment(): FeedListFragment

}
