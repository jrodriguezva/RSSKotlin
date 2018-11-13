package com.jrodiriguezva.rsskotlin.utils.extension


import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.bumptech.glide.request.transition.Transition


fun View.cancelTransition() {
    transitionName = null
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)


fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun ImageView.loadFromUrl(url: String, @DrawableRes placeholder: Int) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .error(Glide.with(this.context.applicationContext).load(placeholder))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

fun ImageView.loadUrlAndPostponeEnterTransition(url: String, activity: androidx.fragment.app.FragmentActivity) {
    val target: Target<Drawable> = ImageViewBaseTarget(
        this,
        activity
    )
    Glide.with(context.applicationContext).load(url).into(target)
}

private class ImageViewBaseTarget(var imageView: ImageView?, var activity: androidx.fragment.app.FragmentActivity?) :
    Target<Drawable> {
    var requestNew: Request? = null

    override fun onLoadStarted(placeholder: Drawable?) {
    }

    override fun getRequest() = requestNew

    override fun onStop() {
    }

    override fun setRequest(request: Request?) {
        requestNew = request
    }

    override fun onLoadCleared(placeholder: Drawable?) {
    }

    override fun onStart() {
    }

    override fun onDestroy() {
    }

    override fun removeCallback(cb: SizeReadyCallback) {
        imageView = null
        activity = null
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        imageView?.setImageDrawable(resource)
        activity?.supportStartPostponedEnterTransition()
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        activity?.supportStartPostponedEnterTransition()
    }

    override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)
}