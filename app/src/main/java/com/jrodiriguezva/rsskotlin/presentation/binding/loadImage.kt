package com.jrodiriguezva.rsskotlin.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.jrodiriguezva.rsskotlin.R
import com.jrodiriguezva.rsskotlin.utils.extension.loadFromUrl

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) =
    url?.let { if (it.isNotBlank()) this.loadFromUrl(url, R.drawable.ic_broken_image) }