package com.carlos.silva.core.interactors

import com.carlos.silva.core.data.AnimeRepository
import com.carlos.silva.core.domain.Episode

class GetEpisodes (val animeRepository: AnimeRepository) {
    suspend operator fun invoke(url: String): MutableList<Episode> = animeRepository.getEpisodes(url)
}