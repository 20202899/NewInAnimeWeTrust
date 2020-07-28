package com.carlos.silva.inanimewetrust.framework.repository

import com.carlos.silva.core.Utils
import com.carlos.silva.core.data.LoadAnimeDataSource
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.lang.Exception

class RemoteAnimeRepository : LoadAnimeDataSource {
    override suspend fun getHome() = withContext(Dispatchers.Default) {
        val doc = Jsoup.connect(RequestURL.MAIN_URL).get()
        val aniContainer = doc.select(".aniContainer")
        val epiSubContainer = doc.select(".epiSubContainer")
        val list = mutableListOf<Any>()

        list.addAll(aniContainer.map { element ->
            element.select(".aniItem").map {
                Anime.mount(it)
            }
        })

        list.addAll(
            2,
            epiSubContainer.map { element ->
                element.select(".epiItem")
                    .map {
                        Episode.mount(it)
                    }
            }
        )

        list.add(0, "Tendência")
        list.add(2, "Recentes")
        list.add(4, "Disponível")
        list.add(6, "Lançamentos do Dia")

        return@withContext list
    }

    override suspend fun getEpisodes(url: String) = withContext(Dispatchers.Default) {
        val doc = Jsoup.connect(url).get()
        val boxAnimeSobreLinhas =
            doc.select(".boxAnimeSobreLinha").map { it.text() }
        val sinopse = doc.select("#sinopse2").first()?.text() ?: ""
        val capaAnime = doc.select("#capaAnime")
        val img = capaAnime.select("img")
        val capaLink = img.attr("src").toString()
        val pagAniListaContainer = doc.select(".pagAniListaContainer ")
        val epiSubContainer = pagAniListaContainer.select("a")
        val videoId = Utils.getDigitFromString(url)
        return@withContext epiSubContainer.map { Episode.mount(it) }.toMutableList()
    }
}