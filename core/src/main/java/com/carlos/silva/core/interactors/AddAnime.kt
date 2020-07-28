package com.carlos.silva.core.interactors

import com.carlos.silva.core.data.AnimeRepository
import com.carlos.silva.core.domain.Anime

class AddAnime (val animeRepository: AnimeRepository) {
    suspend operator fun invoke (anime: Anime) = animeRepository.addAnime(anime)
}