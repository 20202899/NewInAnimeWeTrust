package com.carlos.silva.core.data

import com.carlos.silva.core.domain.Episode
import com.carlos.silva.core.domain.Video

interface LoadAnimeDataSource {
    suspend fun getHome(): MutableList<Any>
    suspend fun getEpisodes(url: String): MutableList<Episode>
    suspend fun loadEpisode(url: String?): MutableList<Video>?
}