package com.carlos.silva.core.data

import com.carlos.silva.core.domain.Anime

interface AnimeDataSource {
    suspend fun add(anime: Anime)
    suspend fun remove(anime: Anime)
}