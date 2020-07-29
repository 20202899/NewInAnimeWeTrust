package com.carlos.silva.core.data

import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode
import com.carlos.silva.core.domain.Video

class AnimeRepository (
    val animeDataSource: AnimeDataSource,
    val loadAnimeDataSource: LoadAnimeDataSource
) {
    suspend fun addAnime (anime: Anime) = animeDataSource.add(anime)
    suspend fun removeAnime (anime: Anime) = animeDataSource.remove(anime)
    suspend fun getHome(): MutableList<Any> = loadAnimeDataSource.getHome()
    suspend fun getEpisodes(url: String): MutableList<Episode> = loadAnimeDataSource.getEpisodes(url)
    suspend fun loadEpisode(url: String?): MutableList<Video>? = loadAnimeDataSource.loadEpisode(url)
}