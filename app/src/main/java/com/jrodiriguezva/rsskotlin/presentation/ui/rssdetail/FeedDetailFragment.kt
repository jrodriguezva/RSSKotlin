package com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.jrodiriguezva.rsskotlin.R
import com.jrodiriguezva.rsskotlin.databinding.FragmentFeedDetailBinding
import com.jrodiriguezva.rsskotlin.domain.error.Failure
import com.jrodiriguezva.rsskotlin.domain.error.NoDataAvailableFailure
import com.jrodiriguezva.rsskotlin.presentation.base.BaseFragmentBinding
import com.jrodiriguezva.rsskotlin.utils.extension.*
import kotlinx.android.synthetic.main.fragment_feed_detail.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class FeedDetailFragment : BaseFragmentBinding<FragmentFeedDetailBinding>() {

    companion object {
        private const val FEED_ID = "idFeed"

        fun newInstance(feedId: Long): FeedDetailFragment {
            val feedDetailFragment = FeedDetailFragment()
            val arguments = Bundle()
            arguments.putLong(FEED_ID, feedId)
            feedDetailFragment.arguments = arguments

            return feedDetailFragment
        }
    }

    @Inject
    lateinit var feedDetailsAnimator: FeedDetailsAnimator

    private lateinit var feedDetailViewModel: FeedDetailViewModel

    override fun layoutId() = R.layout.fragment_feed_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { feedDetailsAnimator.postponeEnterTransition(it) }
        feedDetailViewModel = viewModel(viewModelFactory) {
            observe(liveDataFeed, ::renderDetails)
            failure(failure, ::handleFailure)
        }
    }

    override fun onStart() {
        super.onStart()
        showBackToolbar()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = feedDetailViewModel
        if (firstTimeCreated(savedInstanceState)) {
            arguments?.getLong(FEED_ID)?.let { feedDetailViewModel.loadFeedDetails(it) }
        }
    }

    override fun onBackPressed() {
        feedDetailsAnimator.fadeInvisible(scrollView, clFeedDetails)
        if (openUrl.isVisible())
            feedDetailsAnimator.scaleDownView(openUrl)
        else
            feedDetailsAnimator.cancelTransition(openUrl)
    }

    private fun renderDetails(feed: FeedView?) {
        feed?.let { it ->
            with(it) {
                activity?.let {
                    feedImage.loadUrlAndPostponeEnterTransition(imageUrl, it)
                    it.toolbar.title = title
                }
            }
        }
        feedDetailsAnimator.fadeVisible(scrollView, clFeedDetails)
        feedDetailsAnimator.scaleUpView(openUrl)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> {
                notify(R.string.failure_network_connection); close()
            }
            is Failure.ServerError -> {
                notify(R.string.failure_server_error); close()
            }
            is NoDataAvailableFailure -> {
                notify(R.string.failure_feed_non_existent); close()
            }
        }
    }


}
