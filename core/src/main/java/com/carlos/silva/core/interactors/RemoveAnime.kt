package com.carlos.silva.core.interactors

import com.carlos.silva.core.data.AnimeRepository
import com.carlos.silva.core.domain.Anime

class RemoveAnime (private val animeRepository: AnimeRepository) {
    suspend operator fun invoke(anime: Anime) = animeRepository.removeAnime(anime)
}