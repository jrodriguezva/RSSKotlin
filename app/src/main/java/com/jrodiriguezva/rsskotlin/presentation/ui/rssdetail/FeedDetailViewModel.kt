package com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail

import androidx.lifecycle.MutableLiveData
import android.view.View
import com.jrodiriguezva.rsskotlin.domain.interactor.GetFeedById
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import com.jrodiriguezva.rsskotlin.presentation.base.BaseViewModel
import javax.inject.Inject

class FeedDetailViewModel @Inject constructor(private val getFeedById: GetFeedById, val openUrl: OpenUrl) :
    BaseViewModel() {

    val feed = FeedView()
    val liveDataFeed = MutableLiveData<FeedView>()

    fun loadFeedDetails(feedId: Long) = getFeedById(feedId) { it.either(::handleFailure, ::handleResponse) }

    private fun handleResponse(feed: Feed) {
        this.feed.parseToData(feed)
        liveDataFeed.value = this.feed
    }

    fun onClickOpenUrl(view: View) {
        openUrl(OpenUrl.Params(feed.url))
    }
}