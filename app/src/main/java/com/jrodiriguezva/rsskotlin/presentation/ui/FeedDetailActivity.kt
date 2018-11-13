package com.jrodiriguezva.rsskotlin.presentation.ui

import android.content.Context
import android.content.Intent
import com.jrodiriguezva.rsskotlin.R
import com.jrodiriguezva.rsskotlin.presentation.base.BaseFragmentActivity
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedDetailFragment

class FeedDetailActivity : BaseFragmentActivity() {
    override fun getLayout() = R.layout.activity_layout

    companion object {
        private const val INTENT_EXTRA_ID_FEED = "idFeed"

        fun callingIntent(context: Context, feedId: Long): Intent {
            val intent = Intent(context, FeedDetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_ID_FEED, feedId)
            return intent
        }
    }

    override fun fragment() = FeedDetailFragment.newInstance(intent.getLongExtra(INTENT_EXTRA_ID_FEED, -1))

}
