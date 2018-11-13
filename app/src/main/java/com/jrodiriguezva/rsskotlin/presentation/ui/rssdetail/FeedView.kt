package com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.jrodiriguezva.rsskotlin.BR
import com.jrodiriguezva.rsskotlin.domain.model.Feed
import com.jrodiriguezva.rsskotlin.utils.extension.toString

class FeedView(
    id: Long = -1,
    title: String = "",
    description: String = "",
    notes: String = "",
    url: String = "",
    imageUrl: String = ""
) : BaseObservable() {

    @Bindable
    var title: String = title
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var id: Long = id
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }

    @Bindable
    var description: String = description
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @Bindable
    var notes: String = notes
        set(value) {
            field = value
            notifyPropertyChanged(BR.notes)
        }

    @Bindable
    var url: String = url
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }

    @Bindable
    var imageUrl: String = imageUrl
        set(value) {
            field = value
            notifyPropertyChanged(BR.imageUrl)
        }

    fun parseToData(feed: Feed) {
        this.id = feed.id ?: -1
        this.notes = "${feed.publishedAt.toString("dd MMMM yyyy")} ${feed.author}"
        this.description = feed.description
        this.title = feed.title
        this.url = feed.url
        this.imageUrl = feed.imageUrl
    }

    companion object {
        fun convertToData(feed: Feed) = FeedView(
            feed.id ?: -1,
            feed.title,
            feed.description,
            "${feed.publishedAt.toString("dd MMMM yyyy")} ${feed.author}",
            feed.url,
            feed.imageUrl
        )

        fun convertToData(feed: List<Feed>) = feed.map { convertToData(it) }
    }

}