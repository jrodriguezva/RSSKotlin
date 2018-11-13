package com.jrodiriguezva.rsskotlin.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedDetailViewModel
import com.jrodiriguezva.rsskotlin.presentation.ui.rsslist.FeedListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FeedListViewModel::class)
    abstract fun bindsFeedListViewModel(feedListViewModel: FeedListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedDetailViewModel::class)
    abstract fun bindsFeedDetailViewModel(feedDetailViewModel: FeedDetailViewModel): ViewModel

}