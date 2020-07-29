package com.carlos.silva.inanimewetrust.framework

import com.carlos.silva.core.interactors.*

data class Interactors (
    val addAnime: AddAnime,
    val removeAnime: RemoveAnime,
    val getHome: GetHome,
    val getEpisodes: GetEpisodes,
    val loadEpisode: LoadEpisode
)