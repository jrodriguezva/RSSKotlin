package com.jrodiriguezva.rsskotlin.presentation.ui.rsslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jrodiriguezva.rsskotlin.databinding.ItemFeedBinding
import com.jrodiriguezva.rsskotlin.presentation.navigation.Navigator
import com.jrodiriguezva.rsskotlin.presentation.ui.rssdetail.FeedView
import kotlinx.android.synthetic.main.item_feed.view.*
import javax.inject.Inject
import kotlin.properties.Delegates


class FeedsAdapter
@Inject constructor() : RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {

    internal var collection: List<FeedView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (FeedView, Navigator.Extras) -> Unit = { _, _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemFeedBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(viewHolder: FeedsAdapter.ViewHolder, position: Int) =
        viewHolder.bind(collection[position], clickListener)

    override fun getItemCount() = collection.size

    class ViewHolder(private val itemFeedBinding: ItemFeedBinding) : RecyclerView.ViewHolder(itemFeedBinding.root) {
        fun bind(feedView: FeedView, clickListener: (FeedView, Navigator.Extras) -> Unit) {
            itemFeedBinding.feed = feedView
            itemView.setOnClickListener { clickListener(feedView, Navigator.Extras(itemView.feedImage)) }
            itemFeedBinding.executePendingBindings()
        }
    }
}