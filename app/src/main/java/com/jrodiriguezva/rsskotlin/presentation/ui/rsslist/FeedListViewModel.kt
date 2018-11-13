package com.jrodiriguezva.rsskotlin.presentation.ui.rsslist

import androidx.lifecycle.MutableLiveData
import com.jrodiriguezva.rsskotlin.domain.interactor.GetFeeds
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import com.jrodiriguezva.rsskotlin.presentation.base.BaseViewModel
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedView
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedView.Companion.convertToData
import javax.inject.Inject

class FeedListViewModel @Inject constructor(private val getFeeds: GetFeeds) : BaseViewModel() {

    var feedsList = ArrayList<FeedView>()
    var query = ""
    var feeds: MutableLiveData<List<FeedView>> = MutableLiveData()

    fun loadFeeds(query: String? = null) {
        if (this.query.isBlank()) getFeeds(query) { it.either(::handleFailure, ::handleResult) }
    }

    private fun handleResult(feeds: List<Feed>) {
        feedsList.addAll(convertToData(feeds))
        this.feeds.value = feedsList
    }

    fun queryList(query: String) {
        this.query = query
        this.feeds.value = feedsList.filter { it.title.contains(query) }
    }
}