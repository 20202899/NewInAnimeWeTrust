package com.carlos.silva.inanimewetrust.presentation.anime

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.carlos.silva.core.domain.Episode
import com.carlos.silva.inanimewetrust.R
import jp.wasabeef.glide.transformations.BlurTransformation

class AnimeAdapter(val items: List<AnimeItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == HEADER_TYPE) {
            HeaderViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.anime_header_item,
                    parent,
                    false
                )
            )
        } else {
            ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.episode_item,
                    parent,
                    false
                )
            )
        }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return if (items[position].type == HEADER_TYPE) {
            HEADER_TYPE
        } else {
            EPISODE_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when (holder) {
            is HeaderViewHolder -> {
                holder.bind(item)
            }
            is ItemViewHolder -> {
                holder.bind(item)
            }
        }
    }

    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.image)
        private val imageCenter = itemView.findViewById<ImageView>(R.id.image_center)
        fun bind(animeItem: AnimeItem) {
            animeItem.anime?.let {
                val context = itemView.context

                Glide.with(context)
                    .load(it.imagePath)
                    .into(imageCenter)

                Glide.with(context)
                    .load(it.imagePath)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
                    .into(image)
            }
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(android.R.id.text1)
        fun bind(animeItem: AnimeItem) {
            animeItem.episode?.let {
                textView.text = it.title
            }
        }
    }

    companion object {
        const val HEADER_TYPE = 0
        const val EPISODE_TYPE = 1
    }
}