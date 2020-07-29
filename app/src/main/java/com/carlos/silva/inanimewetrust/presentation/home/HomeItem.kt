package com.carlos.silva.inanimewetrust.presentation.home

import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode
import java.io.Serializable

data class HomeItem(val animes: MutableList<Anime>? = null, val episodes: MutableList<Episode>? = null, val title: String? = null, val type: Int) : Serializable