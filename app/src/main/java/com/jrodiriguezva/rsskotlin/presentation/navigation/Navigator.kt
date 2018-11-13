package com.jrodiriguezva.rsskotlin.presentation.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.jrodiriguezva.rsskotlin.presentation.ui.FeedDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {

    fun showFeedDetails(activity: androidx.fragment.app.FragmentActivity, feedId: Long, navigationExtras: Extras) {
        val intent = FeedDetailActivity.callingIntent(activity, feedId)
        val sharedView = navigationExtras.transitionSharedElement as ImageView
        val activityOptions = ActivityOptionsCompat
            .makeSceneTransitionAnimation(activity, sharedView, sharedView.transitionName)
        activity.startActivity(intent, activityOptions.toBundle())
    }

    fun openFeed(context: Context, feedUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(feedUrl))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    class Extras(val transitionSharedElement: View)
}