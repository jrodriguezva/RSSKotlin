package com.jrodiriguezva.rsskotlin.presentation.base

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.View
import com.jrodiriguezva.rsskotlin.di.module.Injectable
import com.jrodiriguezva.rsskotlin.utils.extension.viewContainer
import kotlinx.android.synthetic.main.activity_layout.*
import javax.inject.Inject

/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragment : androidx.fragment.app.Fragment(), Injectable {

    abstract fun layoutId(): Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    internal fun firstTimeCreated(savedInstanceState: Bundle?) = savedInstanceState == null

    fun hideToolbar() = (activity as? BaseActivity)?.hideToolbar()

    fun showToolbar() = (activity as? BaseActivity)?.showToolbar()

    fun showBackToolbar() {
        (activity as? BaseActivity)?.showBackToolbar()
        setHasOptionsMenu(true)
    }

    open fun onBackPressed() {}

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) { if (this is BaseActivity) this.progress.visibility = viewStatus }


    fun setToolbarTitle(title: String) {
        (activity as? BaseFragmentActivity)?.title = title
    }

    internal open fun notifyWithAction(@StringRes message: Int, @StringRes actionText: Int, action: () -> Any) {
        val snackBar = Snackbar.make(viewContainer, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction(actionText) { action() }
        snackBar.show()
    }

    internal fun notify(@StringRes message: Int) =
        Snackbar.make(viewContainer, message, Snackbar.LENGTH_SHORT).show()


}