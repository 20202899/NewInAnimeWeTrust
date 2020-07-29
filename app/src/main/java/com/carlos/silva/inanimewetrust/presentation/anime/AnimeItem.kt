package com.carlos.silva.inanimewetrust.presentation.anime

import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode
import java.io.Serializable

data class AnimeItem(
    val episode: Episode? = null,
    val anime: Anime? = null,
    val type: Int
) : Serializable {
    companion object {
        const val EXTRAS = "anime"
    }
}