package com.carlos.silva.inanimewetrust.framework

import android.app.Application
import com.carlos.silva.core.data.AnimeRepository
import com.carlos.silva.core.interactors.*
import com.carlos.silva.inanimewetrust.framework.db.RoomAnimeDataSource
import com.carlos.silva.inanimewetrust.framework.repository.RemoteAnimeRepository

class InAnimeWeTrustApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val animeRepository = AnimeRepository(
            RoomAnimeDataSource(this),
            RemoteAnimeRepository()
        )
        InAnimeWeTrustViewModelFactory.inject(this, Interactors(
            AddAnime(animeRepository),
            RemoveAnime(animeRepository),
            GetHome(animeRepository),
            GetEpisodes(animeRepository),
            LoadEpisode(animeRepository)
        ))
    }
}