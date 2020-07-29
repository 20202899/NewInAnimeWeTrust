package com.carlos.silva.inanimewetrust.framework.repository

import com.carlos.silva.core.Utils
import com.carlos.silva.core.data.LoadAnimeDataSource
import com.carlos.silva.core.domain.Anime
import com.carlos.silva.core.domain.Episode
import com.carlos.silva.core.domain.Video
import com.carlos.silva.inanimewetrust.presentation.home.HomeAdapter
import com.carlos.silva.inanimewetrust.presentation.home.HomeItem
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class RemoteAnimeRepository : LoadAnimeDataSource {

    override suspend fun getHome() = withContext(Dispatchers.Default) {
        val doc = Jsoup.connect(RequestURL.MAIN_URL).get()
        val aniContainer = doc.select(".aniContainer")
        val epiSubContainer = doc.select(".epiSubContainer")
        val list = mutableListOf<Any>()

        aniContainer.forEach { element ->
            val animes = mutableListOf<Anime>()
            val title = element.select(".aniContainerTitulo").textNodes().last()
                .toString()
            animes.addAll(element.select(".aniItem").map { Anime.mount(it) })
            list.add(HomeItem(null, null, title, HomeAdapter.HEADER_TYPE))
            list.add(HomeItem(animes, null, null, HomeAdapter.ITEM_TYPE))
        }

        epiSubContainer.forEach { element ->
            val episodes = element.select(".epiItem").map { Episode.mount(it) }
                .toMutableList()
            list.add(HomeItem(null, null, "Disponível", HomeAdapter.HEADER_TYPE))
            list.add(HomeItem(null, episodes, null, HomeAdapter.EPISODE_TYPE))
        }

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

    override suspend fun loadEpisode(url: String?) = withContext(Dispatchers.Default) {
        val doc = Jsoup.connect(url).get()
        val scripts = doc.select("script")
        val script =
            scripts.find { it.toString().contains("sources:") }.toString()
        val type = object : TypeToken<MutableList<Video>>() {}.type
        return@withContext Utils.uncodedScriptText<MutableList<Video>>(
            script,
            type
        )
    }

}