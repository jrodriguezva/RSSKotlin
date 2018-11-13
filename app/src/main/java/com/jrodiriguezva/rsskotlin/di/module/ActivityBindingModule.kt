package com.jrodiriguezva.rsskotlin.di.module

import com.jrodiriguezva.rsskotlin.presentation.ui.FeedActivity
import com.jrodiriguezva.rsskotlin.presentation.ui.FeedDetailActivity
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedDetailModule
import com.jrodiriguezva.rsskotlin.presentation.ui.rsslist.FeedModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.jrodiriguezva.rsskotlin.di.scoped.ActivityScoped

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FeedModule::class])
    abstract fun bindFeedActivity(): FeedActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [FeedDetailModule::class])
    abstract fun bindFeedDetailActivity(): FeedDetailActivity
}