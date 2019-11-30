package com.jrodiriguezva.rsskotlin.utils.extension

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jrodiriguezva.rsskotlin.presentation.base.BaseActivity
import com.jrodiriguezva.rsskotlin.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.activity_layout.*

inline fun androidx.fragment.app.FragmentManager.inTransaction(func: androidx.fragment.app.FragmentTransaction.() -> androidx.fragment.app.FragmentTransaction) =
    beginTransaction().func().commit()

inline fun <reified T : ViewModel> androidx.fragment.app.Fragment.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

val BaseFragment.viewContainer: View get() = (activity as BaseActivity).fragmentContainer

val BaseFragment.appContext: Context get() = activity?.applicationContext!!

fun BaseFragment.close() = fragmentManager?.popBackStack()
