package com.carlos.silva.inanimewetrust.framework.db

import android.content.Context
import com.carlos.silva.core.data.AnimeDataSource
import com.carlos.silva.core.domain.Anime

class RoomAnimeDataSource (context: Context) : AnimeDataSource {

    val animeDao = InAnimeWeTrustDatabase.getInstance(context).getDao()

    override suspend fun add(anime: Anime) {
        animeDao.saveAnime(
            AnimeEntity(
                anime.videoId,
                anime.title,
                anime.imagePath,
                anime.link,
                anime.timer
            )
        )
    }

    override suspend fun remove(anime: Anime) {
        animeDao.deleteAnime(
            AnimeEntity(
                anime.videoId,
                anime.title,
                anime.imagePath,
                anime.link,
                anime.timer
            )
        )
    }

}