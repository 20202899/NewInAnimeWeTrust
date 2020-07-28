package com.carlos.silva.inanimewetrust.presentation.anime

import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode

data class AnimeItem(val episode: Episode? = null, val anime: Anime? = null, val type: Int)