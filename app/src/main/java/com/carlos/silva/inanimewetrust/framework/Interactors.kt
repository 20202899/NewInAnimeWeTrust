package com.carlos.silva.inanimewetrust.framework

import com.carlos.silva.core.interactors.AddAnime
import com.carlos.silva.core.interactors.GetEpisodes
import com.carlos.silva.core.interactors.GetHome
import com.carlos.silva.core.interactors.RemoveAnime

data class Interactors (
    val addAnime: AddAnime,
    val removeAnime: RemoveAnime,
    val getHome: GetHome,
    val getEpisodes: GetEpisodes
)