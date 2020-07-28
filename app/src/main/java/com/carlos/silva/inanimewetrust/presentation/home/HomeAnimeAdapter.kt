package com.carlos.silva.inanimewetrust.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.inanimewetrust.R

class HomeAnimeAdapter(
    private val animes: MutableList<Anime>,
    private val type: Int = 0,
    private val click: (anime: Anime, index: Int) -> Unit
) :
    RecyclerView.Adapter<HomeAnimeAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder (
        LayoutInflater.from(parent.context).inflate(
            if (type == HomeAdapter.RECENT) {
                R.layout.anime_circle_item
            }else {
                R.layout.anime_item
            },
            parent,
            false
        )
    )

    override fun getItemCount() = animes.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val anime = animes[position]
        holder.itemView.setOnClickListener {
            click.invoke(anime, position)
        }
        holder.bind(anime)
    }

    inner class ItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.image)
        private val textView = itemView.findViewById<TextView>(R.id.title)
        fun bind(anime: Anime) {

            textView.text = anime.title

            Glide.with(itemView.context)
                .load(anime.imagePath)
                .into(imageView)
        }
    }

}