package com.carlos.silva.core.interactors

import com.carlos.silva.core.data.AnimeRepository

class GetHome (val animeRepository: AnimeRepository) {
    suspend operator fun invoke(): MutableList<Any> = animeRepository.getHome()
}