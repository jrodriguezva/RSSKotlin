package com.jrodiriguezva.rsskotlin.presentation.ui

import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import androidx.core.view.accessibility.AccessibilityEventCompat.TYPE_VIEW_ACCESSIBILITY_FOCUSED
import androidx.recyclerview.widget.*


class EndlessScroll(
    private val recyclerView: RecyclerView,
    val visibleThreshold: Int = 10,
    private val loadMore: () -> Unit
) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 1
    private var loading = true

    class AccessibilityDelegate(recyclerView: RecyclerView, var changeRow: () -> Unit) :
        RecyclerViewAccessibilityDelegate(recyclerView) {

        override fun onRequestSendAccessibilityEvent(
            host: ViewGroup?,
            child: View?,
            event: AccessibilityEvent?
        ): Boolean {
            event?.let {
                if (it.eventType == TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
                    changeRow()
                }
            }
            return super.onRequestSendAccessibilityEvent(host, child, event)
        }
    }

    init {
        recyclerView.setAccessibilityDelegateCompat(
            AccessibilityDelegate(
                recyclerView,
                ::changeRowByAccessibility
            )
        )
    }

    private fun changeRowByAccessibility() {
        if (this.recyclerView.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
            return
        }
        loading()
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (this.recyclerView.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
            return
        }
        loading()
    }

    private fun loading() {
        with(recyclerView) {
            val visibleItemCount = childCount
            val totalItemCount = layoutManager?.itemCount ?: 0
            val firstVisibleItem = when (layoutManager) {
                is StaggeredGridLayoutManager -> {
                    val lastVisibleItemPositions =
                        (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
                    // get maximum element within the list
                    getLastVisibleItem(lastVisibleItemPositions)
                }
                is GridLayoutManager -> {
                    (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                }
                is LinearLayoutManager -> {
                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                }
                else -> {
                    0
                }
            }

            if (loading) {
                if (totalItemCount >= previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                loadMore()
                loading = true
            }
        }
    }

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }
}


fun RecyclerView.endless(visibleThreshold: Int = 10, loadMore: () -> Unit) {
    this.addOnScrollListener(EndlessScroll(this, visibleThreshold, loadMore))
}