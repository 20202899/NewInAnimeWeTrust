package com.carlos.silva.core.interactors

import com.carlos.silva.core.data.AnimeRepository

class LoadEpisode (val animeRepository: AnimeRepository) {
    suspend operator fun invoke(url: String?) = animeRepository.loadEpisode(url)
}