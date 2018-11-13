package com.jrodiriguezva.rsskotlin.presentation.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jrodiriguezva.rsskotlin.di.module.Injectable


/**
 * Base Fragment class with helper methods for handling views and back button events.
 *
 * @see Fragment
 */
abstract class BaseFragmentBinding<B : ViewDataBinding> : BaseFragment(), Injectable {

    lateinit var binding: B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        return (binding as ViewDataBinding).root
    }

}