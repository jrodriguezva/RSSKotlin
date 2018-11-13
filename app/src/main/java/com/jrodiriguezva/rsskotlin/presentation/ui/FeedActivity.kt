package com.jrodiriguezva.rsskotlin.presentation.ui

import android.content.Context
import android.content.Intent
import com.jrodiriguezva.rsskotlin.R
import com.jrodiriguezva.rsskotlin.presentation.base.BaseFragmentActivity
import com.jrodiriguezva.rsskotlin.presentation.ui.rsslist.FeedListFragment

class FeedActivity : BaseFragmentActivity() {
    override fun getLayout() = R.layout.activity_layout

    companion object {
        fun callingIntent(context: Context) = Intent(context, FeedActivity::class.java)
    }

    override fun fragment() = FeedListFragment.newInstance()

}
