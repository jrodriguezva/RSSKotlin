package com.jrodiriguezva.rsskotlin.presentation.ui.rsslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jrodiriguezva.rsskotlin.R
import com.jrodiriguezva.rsskotlin.databinding.RsslistFragmentBinding
import com.jrodiriguezva.rsskotlin.domain.error.Failure
import com.jrodiriguezva.rsskotlin.domain.error.NoDataAvailableFailure
import com.jrodiriguezva.rsskotlin.presentation.base.BaseFragmentBinding
import com.jrodiriguezva.rsskotlin.presentation.navigation.Navigator
import com.jrodiriguezva.rsskotlin.presentation.ui.endless
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedView
import com.jrodiriguezva.rsskotlin.utils.extension.*
import kotlinx.android.synthetic.main.rsslist_fragment.*
import javax.inject.Inject

class FeedListFragment : BaseFragmentBinding<RsslistFragmentBinding>() {
    override fun layoutId() = R.layout.rsslist_fragment

    companion object {
        fun newInstance() = FeedListFragment()
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var feedsAdapter: FeedsAdapter

    private lateinit var listViewModel: FeedListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listViewModel = viewModel(viewModelFactory) {
            observe(feeds, ::renderFeeds)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showToolbar()
        setHasOptionsMenu(true)
        initializeView()
        loadFeeds()
    }


    private fun initializeView() {
        feedsList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        feedsList.adapter = feedsAdapter

        feedsList.itemAnimator = DefaultItemAnimator()
        feedsList.setHasFixedSize(true)
        feedsList.endless { listViewModel.loadFeeds() }

        feedsAdapter.clickListener = { feed, navigationExtras ->
            activity?.let { navigator.showFeedDetails(it, feed.id, navigationExtras) }
        }
    }

    private fun loadFeeds() {
        emptyView.invisible()
        feedsList.visible()
        showProgress()
        listViewModel.loadFeeds()
    }

    private fun renderFeeds(feeds: List<FeedView>?) {
        feedsAdapter.collection = feeds.orEmpty()
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is NoDataAvailableFailure -> renderFailure(R.string.not_data_available)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        feedsList.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.refresh, ::loadFeeds)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.action_search)
        val search = menuItem.actionView as SearchView
        searching(search)
        super.onPrepareOptionsMenu(menu)
    }

    private fun searching(search: SearchView) {
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                listViewModel.queryList(newText)
                return true
            }
        })
    }

}