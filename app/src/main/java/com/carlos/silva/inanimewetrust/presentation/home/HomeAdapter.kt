package com.carlos.silva.inanimewetrust.presentation.home

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.inanimewetrust.R
import com.carlos.silva.inanimewetrust.presentation.views.EpisodeFragmentAdapter

class HomeAdapter(
    private val items: MutableList<HomeItem>,
    private val context: AppCompatActivity,
    onClickListener: (anime: Anime, indexPath: IndexPath) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickListener = onClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            HEADER_TYPE -> {
                HeaderViewHolder(
                    LayoutInflater.from(context).inflate(
                        android.R.layout.simple_list_item_1,
                        parent,
                        false
                    )
                )
            }
            EPISODE_TYPE -> {
                EpisodeViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.viewpager_item,
                        parent,
                        false
                    )
                )
            }
            else -> {
                ItemViewHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.horizontal_item,
                        parent,
                        false
                    )
                )
            }
        }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = when (items[position].type) {
        HEADER_TYPE -> {
            HEADER_TYPE
        }
        EPISODE_TYPE -> {
            EPISODE_TYPE
        }
        else -> {
            ITEM_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderViewHolder) {
            val item = items[position]
            holder.bind(item)
            return
        }

        if (holder is ItemViewHolder) {
            holder.bind(position)
            return
        }

        if (holder is EpisodeViewHolder) {
            holder.bind(position)
            return
        }
    }

    fun updateList(items: MutableList<HomeItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(android.R.id.text1)

        fun bind(homeItem: HomeItem) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                title.text = Html.fromHtml(homeItem.title, Html.FROM_HTML_MODE_COMPACT);
            } else {
                title.text = Html.fromHtml(homeItem.title);
            }
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val data = items[position]

            with(itemView as RecyclerView) {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter =
                    HomeAnimeAdapter(data.animes!!, position) { anime, index ->
                        clickListener.invoke(anime, IndexPath(position, index))
                    }
            }
        }
    }

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {
            val episodes = items[position]

            with(itemView as ViewPager2) {
                setPageTransformer(MarginPageTransformer(8))
                offscreenPageLimit = 3
                clipChildren = false
                clipToPadding = false
                adapter = EpisodeFragmentAdapter(this@HomeAdapter.context, episodes.episodes!!)
            }
        }
    }

    companion object {
        const val HEADER_TYPE = 0
        const val ITEM_TYPE = 1
        const val EPISODE_TYPE = 5

        const val TREND = 1
        const val RECENT = 3
        const val RELEASES = 5
    }

    data class IndexPath(val column: Int, val index: Int)

}