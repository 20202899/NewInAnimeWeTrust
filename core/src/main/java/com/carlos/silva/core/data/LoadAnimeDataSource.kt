package com.carlos.silva.core.data

import com.carlos.silva.core.domain.Episode

interface LoadAnimeDataSource {
    suspend fun getHome(): MutableList<Any>
    suspend fun getEpisodes(url: String): MutableList<Episode>
}