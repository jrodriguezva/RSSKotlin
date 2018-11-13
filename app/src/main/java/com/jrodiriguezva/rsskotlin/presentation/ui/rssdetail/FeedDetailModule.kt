package com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FeedDetailModule {

    @ContributesAndroidInjector
    abstract fun provideFeedDetailFragment(): FeedDetailFragment

}
