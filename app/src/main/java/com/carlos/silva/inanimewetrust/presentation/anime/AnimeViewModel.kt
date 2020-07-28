package com.carlos.silva.inanimewetrust.presentation.anime

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode
import com.carlos.silva.inanimewetrust.framework.InAnimeWeTrustViewModel
import com.carlos.silva.inanimewetrust.framework.Interactors
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class AnimeViewModel(application: Application,
                     private val interactors: Interactors
) : InAnimeWeTrustViewModel(application, interactors) {

    val animeLiveData = MutableLiveData<Anime>()
    val episodesLiveData = MutableLiveData<List<AnimeItem>>()

    fun getEpisodes (anime: Anime) = viewModelScope.launch {
        val episodes = async { interactors.getEpisodes(anime.link) }.await()
        episodesLiveData.value = mutableListOf<AnimeItem>().apply {
            add(AnimeItem(null, animeLiveData.value, AnimeAdapter.HEADER_TYPE))
            addAll(episodes.map { AnimeItem(it, null, AnimeAdapter.EPISODE_TYPE) })
        }
    }

    fun selectAnime(anime: Anime) {
        animeLiveData.value = anime
    }
}